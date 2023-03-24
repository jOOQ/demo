package org.jooq.demo.skala

import org.jooq.DSLContext
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.junit.Test
import org.jooq.demo.skala.db.Tables._
import org.jooq.impl.DSL.count
import org.jooq.impl.DSL.inline


class Demo16Diagnostics extends AbstractDemo {

  @Test
  def testDiagnosticsNPlusOne(): Unit = {
    val c = ctx.configuration.derive(ctx.diagnosticsConnection).dsl

    // This test runs an outer query and repeatedly executes the same inner query
    // Using the DiagnosticsConnection, we get access to log output for this problem
    // Look for diagnostics log output for:
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-repeated-statements/
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-consecutive-aggregation/ (commercial only feature)
    for (actor <- c
      .select(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .orderBy(ACTOR.ACTOR_ID)
      .limit(10)
    ) {
      val (actorId: Long, firstName, lastName) = actor
      val count = c
        .selectCount()
        .from(FILM_ACTOR)
        .where(FILM_ACTOR.ACTOR_ID.equal(actorId))
        .fetchSingleInto(classOf[Int])
      println("Actor : " + firstName + " " + lastName + " with " + count + " films.")
    }
  }

  @Test
  def testConcatenationInPredicates(): Unit = {
    val c = ctx.configuration.derive(ctx.diagnosticsConnection).dsl

    // This test runs a query that makes use of concatenation in a predicate
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-concatenation-in-predicates/ (commercial only feature)
    c.selectFrom(ACTOR)
      .where(ACTOR.FIRST_NAME.concat(" ").concat(ACTOR.LAST_NAME).eq("JOE SWANK"))
      .fetch
  }

  @Test
  def testPossiblyWrongExpression(): Unit = {
    val c = ctx.configuration.derive(ctx.diagnosticsConnection).dsl

    // This test runs a query that makes use of a possibly wrong expression.
    // MOD(x, 2) = 1 doesn't work for negative numbers. Use MOD(x, 2) != 0, instead
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-possibly-wrong-expressions/ (commercial only feature)
    c.selectFrom(ACTOR)
      .where(ACTOR.ACTOR_ID.mod(inline(2L)).eq(inline(1L)))
      .fetch
  }

  @Test
  def testNullCondition(): Unit = {
    val c = ctx.configuration.derive(ctx.diagnosticsConnection).dsl

    // This test runs a query that may be subtly wrong due to a NULL condition,
    // but perhaps, the error isn't noticed immediately
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-null-condition/ (commercial only feature)
    c.selectFrom(ACTOR)
      .where(ACTOR.ACTOR_ID.eq(1L))
      .and(ACTOR.LAST_NAME.ne(inline(null.asInstanceOf[String])))
      .fetch
  }

  @Test
  def testTrivialCondition(): Unit = {
    val c = ctx.configuration.derive(ctx.diagnosticsConnection).dsl

    // This test runs a query that may be subtly wrong due to a trivial condition,
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-trivial-condition/ (commercial only feature)
    c.select(ACTOR.ACTOR_ID, count)
      .from(ACTOR)
      .join(FILM_ACTOR)
      // The JOIN predicate is wrong obviously, but it's an easy to make mistake
      .on(ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
      .groupBy(ACTOR.ACTOR_ID)
      .fetch
  }

  @Test
  def testTransformPatterns(): Unit = {
    val c = ctx.configuration.derive(ctx.diagnosticsConnection).dsl

    // This test runs a query that may be rewritten for stylistic or correctness reasons
    // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-transform-patterns/ (commercial only feature)
    c.select(ACTOR.ACTOR_ID)
      .from(ACTOR)

      // This should be ACTOR_ID >= 1
      .where (ACTOR.ACTOR_ID.eq(inline(1L)))
      .or(ACTOR.ACTOR_ID.gt(inline(1L)))
      .fetch
  }
}
