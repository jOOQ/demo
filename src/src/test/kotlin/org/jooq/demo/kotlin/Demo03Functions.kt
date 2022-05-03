package org.jooq.demo.kotlin

import org.jooq.Condition
import org.jooq.Field
import org.jooq.demo.java.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.jooq.impl.DSL
import org.jooq.impl.DSL.*
import org.junit.Test
import org.jooq.kotlin.*

class Demo03Functions : AbstractDemo() {

    @Test
    fun testSimpleFunctions() {
        title("A huge amount of SQL functions and expressions are available in jOOQ")
        ctx
            .select(
                ACTOR.FIRST_NAME.concat(ACTOR.LAST_NAME),

                // It ain't much but it's honest work (a bit of operator overloading)
                length(ACTOR.FIRST_NAME) + length(ACTOR.LAST_NAME)
            )
            .from(ACTOR)
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch()
    }

    @Test
    fun testPlainSQLTemplates() {
        title("Whenever jOOQ does not support a vendor specific function, you can add support yourself")
        ctx.select(ACTOR.FIRST_NAME, difference(ACTOR.FIRST_NAME, "JOHN"))
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.ilike("%A%"))
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch()
    }

    private fun difference(f1: Field<String?>, f2: String?): Field<String?> {
        return difference(f1, DSL.`val`(f2))
    }

    private fun difference(f1: Field<String?>, f2: Field<String?>): Field<String?> {
        return field("difference({0}, {1})", f1.dataType, f1, f2)
    }

    private fun <F: Field<String?>> F.ilike(field: String?): Condition {
        return condition("{0} ilike {1}", this, field)
    }

    @Test
    fun aggregateFunctions() {
        title("A lot of aggregate functions are available too")
        ctx.select(
                ACTOR.FIRST_NAME,
                ACTOR.LAST_NAME,
                count().`as`("number of films"),
                count().filterWhere(FILM.TITLE.like("%A%")).`as`("number of films containing 'A' in the title"),
                max(FILM.LENGTH).`as`("longest film"),
                min(FILM.LENGTH).`as`("shortest film")
            )
            .from(ACTOR)
            .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .join(FILM).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
            .groupBy(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch()
    }

    @Test
    fun windowFunctions() {
        title("A lot of window functions are available too")
        val w = name("w").`as`(partitionBy(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME))
        ctx.select(
                rowNumber().over(w.orderBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)),
                ACTOR.FIRST_NAME,
                ACTOR.LAST_NAME,
                FILM.TITLE,
                count().over(w).`as`("number of films"),
                count().filterWhere(FILM.TITLE.like("%A%")).over(w)
                    .`as`("number of films containing 'A' in the title"),
                max(FILM.LENGTH).over(w).`as`("longest film"),
                min(FILM.LENGTH).over(w).`as`("shortest film")
            )
            .from(ACTOR)
            .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .join(FILM).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
            .window(w)
            .orderBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .limit(5)
            .fetch()
    }
}