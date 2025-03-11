package org.jooq.demo.java

import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.impl.DSL._
import org.junit.Test

import scala.collection.convert.ImplicitConversions._

class Demo02DynamicSQL extends AbstractDemo {

  @Test
  def testDynamicSQL(): Unit = {
    title("Every jOOQ query is a dynamic SQL query. You just don't see it")
    ctx
      .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID.in(1, 2, 3))
      .orderBy(ACTOR.FIRST_NAME)
      .limit(5)
      .fetch

    title("The above and the below are equivalent")
    val select = List(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
    val from = ACTOR
    val where = ACTOR.ACTOR_ID.in(1, 2, 3)
    val orderBy = List(ACTOR.FIRST_NAME)
    val limit = value(5)
    ctx.select(select)
      .from(from)
      .where(where)
      .orderBy(orderBy)
      .limit(limit)
      .fetch

    title("Any 'static' query part can be replaced by an expression, function call, etc.")
    val ids = List(1, 2, 3)
    ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(
        if (ids.isEmpty)
          noCondition
        else
          ACTOR.ACTOR_ID.in(ids.map(_.asInstanceOf[Long]).map(value)))
      .orderBy(ACTOR.FIRST_NAME)
      .limit(5)
      .fetch

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql/
    // - https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql/no-condition/
  }

  @Test
  def generateQueryParts(): Unit = {
    println(reduceCondition(List()))
    println(reduceCondition(List(1)))
    println(reduceCondition(List(1, 2, 3)))
  }

  private def reduceCondition(ids: List[Int]) = {
    // For some operators like AND and OR, there exists an identity (TRUE for AND, and FALSE for OR), but often
    // a pseudo-identity that completely omits an unnecessary clause is more useful. For this, we're using the
    // DSL.noCondition(), which behaves like the identity of AND or OR, respectively, and doesn't generate a WHERE
    // clause by itself.

    title("List: " + ids)
    ids.map(_.asInstanceOf[Long]).map(ACTOR.ACTOR_ID.eq(_)).fold(noCondition) { _.or(_) }

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql/no-condition/
    // - https://www.jooq.org/doc/latest/manual/sql-building/conditional-expressions/true-false-no-condition/
  }
}
