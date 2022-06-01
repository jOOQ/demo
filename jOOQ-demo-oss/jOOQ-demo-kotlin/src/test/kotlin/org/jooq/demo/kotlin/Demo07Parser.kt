package org.jooq.demo.kotlin

import org.jooq.Field
import org.jooq.MetaProvider
import org.jooq.ParseListener
import org.jooq.conf.ParseWithMetaLookups
import org.jooq.conf.Settings
import org.jooq.demo.AbstractDemo
import org.jooq.impl.DSL
import org.jooq.impl.ParserException
import org.junit.Test

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
    fun parseMetaLookups() {
        title("The parser can validate your schema, too. There are different types of meta data sources")
        val meta = ctx.meta(
            """
            create table author (
              author_id int not null primary key, 
              first_name text not null, 
              last_name text not null
            );
            create table book (
              book_id int not null primary key,
              author_id int not null references author, 
              title text not null
            );
            """
        )

        val c = ctx
            .configuration()
            .derive(MetaProvider { meta })
            .deriveSettings { s -> s.withParseWithMetaLookups(ParseWithMetaLookups.THROW_ON_FAILURE) }
            .dsl()

        title("Check the projection produced by the parsed query")
        println(c.parser().parseSelect("select * from book")!!.select)

        try {
            c.parser().parseSelect("select id from book")
        }
        catch (e: ParserException) {
            title("Parse errors now include meta data lookup errors")
            e.printStackTrace()
        }
    }

    @Test
    fun parseListener() {
        // Feature available in the commercial editions only

//        title("The jOOQ parser might not support all of your vendor specific syntax. In some cases, you can simply extend it")
//        try {
//            ctx.parser().parseSelect("select approx_avg(length) from film")!!.fetch()
//        }
//        catch (e: ParserException) {
//            e.printStackTrace()
//        }
//
//        title("Handle a few additional functions per dialect")
//        ctx.configuration().set(ParseListener.onParseField { c1 ->
//            if (c1.parseFunctionNameIf("APPROX_SUM"))
//                c1.parseParenthesised { c2 -> DSL.sum(c2.parseField() as Field<out Number>) }
//            else if (c1.parseFunctionNameIf("APPROX_AVG"))
//                c1.parseParenthesised { c2 -> DSL.avg(c2.parseField() as Field<out Number>) }
//            else
//                null
//        })
//        ctx.parser().parseSelect("select approx_avg(length) from film")!!.fetch()
//
    }
}