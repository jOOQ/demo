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
  }

  @Test
  def traversal(): Unit = {
    title("The query object model (QOM) can be traversed easily")
    // This is a commercial only feature. Check out the commercial demo for details

























  }

  @Test 
  def replacement(): Unit = {
    title("The query object model (QOM) can be transformed easily")

    // This is a commercial only feature. Check out the commercial demo for details
































  }





















}
