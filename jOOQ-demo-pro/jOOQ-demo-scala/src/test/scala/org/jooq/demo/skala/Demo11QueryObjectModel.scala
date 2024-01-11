package org.jooq.demo.skala

import org.jooq._
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.impl.DSL._
import org.jooq.impl.QOM
import org.junit.Test

import java.util
import java.util.function.Function
import java.util.stream.Collectors.{groupingBy, toList}
import scala.collection.convert.ImplicitConversions._


class Demo11QueryObjectModel extends AbstractDemo {

  // As of jOOQ 3.17, these features are *EXPERIMENTAL*!

  @Test
  def qom(): Unit = {
    title("The query object model (QOM) API allows for accessing most query parts")
    val select = ctx
      .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID.lt(4L))

    println("SELECT: " + select.$select)
    println("FROM  : " + select.$from)
    println("WHERE : " + select.$where)

    title("You can also alter a property of a query, to create a new query (QOM operations are immutable):")
    println(select
      .$select(List(ACTOR.ACTOR_ID) ++ select.$select)
      .$orderBy(List(ACTOR.ACTOR_ID.asc)))

    title("The old query is untouched:")
    println(select)

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/model-api/
  }

  @Test
  def traversal(): Unit = {
    title("The query object model (QOM) can be traversed easily")
    // This is a commercial only feature. Check out the commercial demo for details

    /* [pro] */
    val select = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID.lt(4L))

    println(s"All column expressions: ${select.$traverse(Traversers.findingAll(_.isInstanceOf[Field[_]]))}")
    println(s"All bind values: ${select.$traverse(Traversers.findingAll(_.isInstanceOf[Param[_]]))}")

    title("Any JDK Collector can be turned into a Traverser, too, e.g. collecting to a list")
    select
      .$traverse(Traversers.collecting(toList[QueryPart]))
      .forEach(println(_))

    title("Or grouping query parts by type")
    val f: Function[QueryPart, Class[_]] = _.getClass
    select
      .$traverse(Traversers.collecting(groupingBy(f, toList[QueryPart])))
      .forEach((t, parts: util.List[QueryPart]) => {
        println("")
        println("Type: " + t)
        println("Parts:")
        parts.forEach(part => println("  " + part))
      })
    /* [/pro] */

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/model-api/model-api-traversal/
  }

  @Test 
  def replacement(): Unit = {
    title("The query object model (QOM) can be transformed easily")

    // This is a commercial only feature. Check out the commercial demo for details
    /* [pro] */
    val select1 = ctx
      .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID.lt(4L))
    
    title("Replacing bind values")
    println(select1.$replace((p: QueryPart) =>
      if (p.isInstanceOf[Param[_]])
        value(5)
      else
        p
    ))

    title("Inverting the < predicate")
    println(select1.$replace((p: QueryPart) => p match {
      case lt: QOM.Lt[_] => lt.$converse
      case _ => p
    }))

    title("Appending a predicate")
    println(select1.$replace(appendSecurityCheck))

    title("Appending a predicate even to subqueries")
    val select2 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID.lt(
        select(max(ACTOR.ACTOR_ID))
          .from(ACTOR)
      ))
    println(select2.$replace(appendSecurityCheck))
    /* [/pro] */

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-building/model-api/model-api-replacement/
  }

  /* [pro] */
  private def appendSecurityCheck: Function[QueryPart, QueryPart] = p => {
    val c = condition("security_check()")

    // Beware of performance and infinite recursions, though!
    p match {
      case s: Select[_] =>
        // Append the predicate if there is no predicate
        if (s.$where == null)
          s.$where(c)

        // If there's already a predicate, check if the predicate contains the predicate already (don't recurse into subqueries)
        else if (!s.$where.$traverse(Traversers.recursing(!_.isInstanceOf[Select[_]], Traversers.containing(c))))
          s.$where(and(s.$where, c))
        else
          p
      case _ => p
    }
  }
  /* [/pro] */
}
