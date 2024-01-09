package org.jooq.demo.kotlin

import org.jooq.Condition
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.jooq.impl.DSL
import org.jooq.impl.DSL.noCondition
import org.junit.Test

class Demo02DynamicSQL : AbstractDemo() {
    @Test
    fun testDynamicSQL() {
        title("Every jOOQ query is a dynamic SQL query. You just don't see it")
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.`in`(1L, 2L, 3L))
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch()

        title("The above and the below are equivalent")
        val select = listOf(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        val from = ACTOR
        val where = ACTOR.ACTOR_ID.`in`(1L, 2L, 3L)
        val orderBy = listOf(ACTOR.FIRST_NAME)
        val limit = DSL.`val`(5)

        ctx.select(select)
            .from(from)
            .where(where)
            .orderBy(orderBy)
            .limit(limit)
            .fetch()

        title("Any 'static' query part can be replaced by an expression, function call, etc.")
        val ids = listOf(1, 2, 3)
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(
                if (ids.isEmpty())
                    noCondition()
                else
                    ACTOR.ACTOR_ID.`in`(ids.map { it.toLong() }.map { DSL.value(it) }.toList())
            )
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch()

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql/
        // - https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql/no-condition/
    }

    @Test
    fun generateQueryParts() {
        println(reduceCondition(listOf()))
        println(reduceCondition(listOf(1)))
        println(reduceCondition(listOf(1, 2, 3)))
    }

    private fun reduceCondition(ids: List<Int>): Condition {
        // For some operators like AND and OR, there exists an identity (TRUE for AND, and FALSE for OR), but often
        // a pseudo-identity that completely omits an unnecessary clause is more useful. For this, we're using the
        // DSL.noCondition(), which behaves like the identity of AND or OR, respectively, and doesn't generate a WHERE
        // clause by itself.

        title("List: $ids")
        return ids
            .map { it.toLong() }
            .map { ACTOR.ACTOR_ID.eq(it) }
            .fold(noCondition()) { c1: Condition, c2: Condition -> c1.or(c2) }

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql/no-condition/
        // - https://www.jooq.org/doc/latest/manual/sql-building/conditional-expressions/true-false-no-condition/
    }
}