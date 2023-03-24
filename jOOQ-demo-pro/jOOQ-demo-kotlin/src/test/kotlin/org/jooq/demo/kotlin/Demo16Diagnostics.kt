package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.jooq.impl.DSL
import org.junit.Test


class Demo16Diagnostics : AbstractDemo() {

    @Test
    fun testDiagnosticsNPlusOne() {
        val c = ctx.configuration().derive(ctx.diagnosticsConnection()).dsl()

        // This test runs an outer query and repeatedly executes the same inner query
        // Using the DiagnosticsConnection, we get access to log output for this problem
        // Look for diagnostics log output for:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-repeated-statements/
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-consecutive-aggregation/ (commercial only feature)
        for ((actorId, firstName, lastName) in c
            .select(
                ACTOR.ACTOR_ID,
                ACTOR.FIRST_NAME,
                ACTOR.LAST_NAME
            )
            .from(ACTOR)
            .orderBy(ACTOR.ACTOR_ID)
            .limit(10)
        ) {
            val count: Int = c
                .selectCount()
                .from(FILM_ACTOR)
                .where(FILM_ACTOR.ACTOR_ID.eq(actorId))
                .fetchSingleInto(Int::class.javaPrimitiveType)

            println("Actor : $firstName $lastName with $count films.")
        }
    }

    @Test
    fun testConcatenationInPredicates() {
        val c = ctx.configuration().derive(ctx.diagnosticsConnection()).dsl()

        // This test runs a query that makes use of concatenation in a predicate
        // Look for diagnostics log output for:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-concatenation-in-predicates/ (commercial only feature)
        c.selectFrom(ACTOR)
            .where(
                ACTOR.FIRST_NAME.concat(" ")
                    .concat(ACTOR.LAST_NAME).eq("JOE SWANK")
            )
            .fetch()
    }

    @Test
    fun testPossiblyWrongExpression() {
        val c = ctx.configuration().derive(ctx.diagnosticsConnection()).dsl()

        // This test runs a query that makes use of a possibly wrong expression.
        // MOD(x, 2) = 1 doesn't work for negative numbers. Use MOD(x, 2) != 0, instead
        // Look for diagnostics log output for:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-possibly-wrong-expressions/ (commercial only feature)
        c.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.mod(DSL.inline(2L)).eq(DSL.inline(1L)))
            .fetch()
    }

    @Test
    fun testNullCondition() {
        val c = ctx.configuration().derive(ctx.diagnosticsConnection()).dsl()

        // This test runs a query that may be subtly wrong due to a NULL condition,
        // but perhaps, the error isn't noticed immediately
        // Look for diagnostics log output for:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-null-condition/ (commercial only feature)
        c.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(1L))
            .and(ACTOR.LAST_NAME.ne(DSL.inline(null as String?)))
            .fetch()
    }

    @Test
    fun testTrivialCondition() {
        val c = ctx.configuration().derive(ctx.diagnosticsConnection()).dsl()

        // This test runs a query that may be subtly wrong due to a trivial condition,
        // but perhaps, the error isn't noticed immediately
        // Look for diagnostics log output for:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-trivial-condition/ (commercial only feature)
        c.select(ACTOR.ACTOR_ID, DSL.count())
            .from(ACTOR)
            .join(FILM_ACTOR)

            // The JOIN predicate is wrong obviously, but it's an easy to make mistake
            .on(ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
            .groupBy(ACTOR.ACTOR_ID)
            .fetch()
    }

    @Test
    fun testTransformPatterns() {
        val c = ctx.configuration().derive(ctx.diagnosticsConnection()).dsl()

        // This test runs a query that may be rewritten for stylistic or correctness reasons
        // Look for diagnostics log output for:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/diagnostics/diagnostics-transform-patterns/ (commercial only feature)
        c.select(ACTOR.ACTOR_ID)
            .from(ACTOR)

            // This should be ACTOR_ID >= 1
            .where(
                ACTOR.ACTOR_ID.eq(DSL.inline(1L))
                    .or(ACTOR.ACTOR_ID.gt(DSL.inline(1L)))
            )
            .fetch()
    }
}
