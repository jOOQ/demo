package org.jooq.demo.kotlin

import org.jooq.AggregateFunction
import org.jooq.Field
import org.jooq.ParseContext
import org.jooq.ParseListener
import org.jooq.demo.java.AbstractDemo
import org.jooq.impl.DSL
import org.jooq.impl.ParserException
import org.junit.Test
import java.sql.SQLException

class Demo07Parser : AbstractDemo() {

    @Test
    fun parser() {
        title("jOOQ can also parse SQL strings to jOOQ expression trees")
        val select = ctx.parser().parseSelect("select instr('abcd', 'bc'), instr('a a a a a', 'a', 3) from dual")
        println(select)
        select!!.fetch()
    }

    @Test
    fun parsingConnection() {
        title("You can use jOOQ behind the scenes to parse your legacy SQL statements in order to benefit a migration")
        ctx.parsingConnection().use { c ->
            c.prepareStatement("select level from dual connect by level <= ?").use { s ->
                s.setInt(1, 10)
                s.executeQuery().use { rs -> while (rs.next()) println(rs.getInt(1)) }
            }
        }
    }

    @Test
    fun parseListener() {
        title("The jOOQ parser might not support all of your vendor specific syntax. In some cases, you can simply extend it")
        try {
            ctx.parser().parseSelect("select approx_avg(length) from film")!!.fetch()
        }
        catch (e: ParserException) {
            e.printStackTrace()
        }

        title("Handle a few additional functions per dialect")
        ctx.configuration().set(ParseListener.onParseField { c1 ->
            if (c1.parseFunctionNameIf("APPROX_SUM"))
                c1.parseParenthesised { c2 -> DSL.sum(c2.parseField() as Field<out Number>) }
            else if (c1.parseFunctionNameIf("APPROX_AVG"))
                c1.parseParenthesised { c2 -> DSL.avg(c2.parseField() as Field<out Number>) }
            else
                null
        })
        ctx.parser().parseSelect("select approx_avg(length) from film")!!.fetch()
    }
}