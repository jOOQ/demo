package org.jooq.demo.kotlin

import org.jooq.*
import org.jooq.demo.AbstractDemo
import org.jooq.demo.java.db.Tables
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.jooq.impl.DSL.*
import org.jooq.impl.QOM
import org.junit.Test
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.stream.Collectors
import java.util.stream.Collectors.groupingBy
import java.util.stream.Collectors.toList

class Demo11QueryObjectModel : AbstractDemo() {

    // As of jOOQ 3.17, these features are *EXPERIMENTAL*!
    @Test
    fun qom() {
        title("The query object model (QOM) API allows for accessing most query parts")
        val select = ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))

        println("SELECT: " + select.`$select`())
        println("FROM  : " + select.`$from`())
        println("WHERE : " + select.`$where`())

        title("You can also alter a property of a query, to create a new query (QOM operations are immutable):")
        println(
            select.`$select`(listOf(ACTOR.ACTOR_ID) + select.`$select`())
                .`$orderBy`(listOf(ACTOR.ACTOR_ID.asc()))
        )

        title("The old query is untouched:")
        println(select)
    }


    @Test
    fun traversal() {
        title("The query object model (QOM) can be traversed easily")
        val select = ctx
            .select(Tables.ACTOR.FIRST_NAME, Tables.ACTOR.LAST_NAME)
            .from(Tables.ACTOR)
            .where(Tables.ACTOR.ACTOR_ID.lt(4L))

        println("All column expressions: " + select.`$traverse`(Traversers.findingAll { p -> p is Field<*> }))
        println("All bind values: " + select.`$traverse`(Traversers.findingAll { p -> p is Param<*> }))

        title("Any JDK Collector can be turned into a Traverser, too, e.g. collecting to a list")
        select.`$traverse`(Traversers.collecting(toList())).forEach { println(it) }

        title("Or grouping query parts by type")
        select.`$traverse`(Traversers.collecting(groupingBy( { it.javaClass }, toList()))).forEach { (type, parts) ->
            println("")
            println("Type: $type")
            println("Parts:")
            parts.forEach { println("  $it") }
        }
    }

    @Test
    fun replacement() {
        title("The query object model (QOM) can be transformed easily");
        var select1 = ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))

        title("Replacing bind values")
        println(select1.`$replace` { p -> if (p is Param<*>) value(5) else p })

        title("Inverting the < predicate")
        println(select1.`$replace`{ p -> if (p is QOM.Lt<*>) p.`$converse`() else p })

        title("Appending a predicate");
        println(select1.`$replace`(appendSecurityCheck()))

        title("Appending a predicate even to subqueries")
        var select2 = ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(
                select(max(ACTOR.ACTOR_ID)).from(ACTOR))
            )
        println(select2.`$replace`(appendSecurityCheck()))
    }

    private fun appendSecurityCheck(): (p: QueryPart) -> QueryPart {
        return { p ->
            val c = condition("security_check()")

            // Beware of performance and infinite recursions, though!
            if (p is Select<*>)

                // Append the predicate if there is no predicate
                if (p.`$where`() == null)
                    p.`$where`(c)

                // If there's already a predicate, check if the predicate contains the predicate already (don't recurse into subqueries)
                else if (!p.`$where`()!!.`$traverse`(Traversers.recursing({ q -> !(q is Select<*>)}, Traversers.containing(c)) ))
                    p.`$where`(and(p.`$where`(), c))

                else
                    p
            else
                p
        }
    }

}