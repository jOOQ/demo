package org.jooq.demo.skala

import org.jooq._
import org.jooq.conf.{ParseWithMetaLookups, Settings}
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.impl.DSL
import org.jooq.impl.ParserException
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import scala.util.Using


class Demo07Parser extends AbstractDemo {

  @Test
  def parser(): Unit = {
    title("jOOQ can also parse SQL strings to jOOQ expression trees")
    val select = ctx.parser.parseSelect("select instr('abcd', 'bc'), instr('a a a a a', 'a', 3) from dual")
    println(select)
    select.fetch
  }

  @Test
  def parsingConnection(): Unit = {
    title("You can use jOOQ behind the scenes to parse your legacy SQL statements in order to benefit a migration")
    // Replace your existing JDBC Connection by this one transparently, and suddenly, most of your
    // SQL queries written for dialect A now work on any jOOQ dialect
    Using(ctx.parsingConnection()) { c =>
      Using(c.prepareStatement("select level from dual connect by level <= ?")) { s =>
        s.setInt(1, 10)

        Using(s.executeQuery()) { rs =>
          while (rs.next)
            println(rs.getInt(1))
        }
      }
    }
  }

  @Test
  def parseMetaLookups(): Unit = {
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
             """)

    val c = ctx.configuration
      .derive(() => meta)
      .deriveSettings(_.withParseWithMetaLookups(ParseWithMetaLookups.THROW_ON_FAILURE))
      .dsl

    title("Check the projection produced by the parsed query")
    println(c.parser.parseSelect("select * from book").getSelect)

    try c.parser.parseSelect("select id from book")
    catch {
      case e: ParserException =>
        title("Parse errors now include meta data lookup errors")
        e.printStackTrace()
    }
  }

  @Test
  def parseListener(): Unit = {
    // Feature available in the commercial editions only

//    title("The jOOQ parser might not support all of your vendor specific syntax. In some cases, you can simply extend it")
//    try ctx.parser.parseSelect("select approx_avg(length) from film").fetch
//    catch {
//      case e: ParserException =>
//        e.printStackTrace()
//    }
//
//    title("Handle a few additional functions per dialect")
//    ctx.configuration.set(ParseListener.onParseField(c1 =>
//      if (c1.parseFunctionNameIf("APPROX_SUM"))
//        c1.parseParenthesised(c2 => DSL.sum(c2.parseField.asInstanceOf[Field[Number]]))
//      else if (c1.parseFunctionNameIf("APPROX_AVG"))
//        c1.parseParenthesised(c2 => DSL.avg(c2.parseField.asInstanceOf[Field[Number]]))
//      // TODO: The rest
//      else
//        null
//    ))
//    ctx.parser.parseSelect("select approx_avg(length) from film").fetch
//
  }
}
