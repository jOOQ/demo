package org.jooq.demo.skala

import org.jooq.Field
import org.jooq.WindowDefinition
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.impl.DSL
import org.junit.Test
import org.jooq.demo.skala.db.Tables._
import org.jooq.impl.DSL._
import org.jooq.scalaextensions.Conversions._


class Demo03Functions extends AbstractDemo {

  @Test
  def testSimpleFunctions(): Unit = {
    title("A huge amount of SQL functions and expressions are available in jOOQ")
    ctx
      .select(
        ACTOR.FIRST_NAME || ACTOR.LAST_NAME, 
        length(ACTOR.FIRST_NAME) + length(ACTOR.LAST_NAME))
      .from(ACTOR)
      .orderBy(ACTOR.FIRST_NAME)
      .limit(5)
      .fetch
  }

  @Test 
  def testPlainSQLTemplates(): Unit = {
    title("Whenever jOOQ does not support a vendor specific function, you can add support yourself")
    ctx.select(ACTOR.FIRST_NAME, difference(ACTOR.FIRST_NAME, "JOHN"))
      .from(ACTOR)
      .orderBy(ACTOR.FIRST_NAME)
      .limit(5)
      .fetch
  }

  private def difference(f1: Field[String], f2: String): Field[String] = difference(f1, value(f2))
  private def difference(f1: Field[String], f2: Field[String]): Field[String] = field("difference({0}, {1})", f1.getDataType, f1, f2)

  @Test
  def aggregateFunctions(): Unit = {
    title("A lot of aggregate functions are available too")
    ctx
      .select(
        ACTOR.FIRST_NAME,
        ACTOR.LAST_NAME,
        count as "number of films",
        count.filterWhere(FILM.TITLE like "%A%") as "number of films containing 'A' in the title",
        max(FILM.LENGTH) as "longest film",
        min(FILM.LENGTH) as "shortest film")
      .from(ACTOR)
      .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
      .join(FILM).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
      .groupBy(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .orderBy(ACTOR.FIRST_NAME)
      .limit(5)
      .fetch
  }

  @Test
  def windowFunctions(): Unit = {
    title("A lot of window functions are available too")
    val w = name("w") as partitionBy(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
    ctx
      .select(
        rowNumber.over(w.orderBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)),
        ACTOR.FIRST_NAME,
        ACTOR.LAST_NAME,
        FILM.TITLE,
        count.over(w) as "number of films",
        count.filterWhere(FILM.TITLE.like("%A%")).over(w) as "number of films containing 'A' in the title",
        max(FILM.LENGTH).over(w) as "longest film",
        min(FILM.LENGTH).over(w) as "shortest film")
      .from(ACTOR)
      .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
      .join(FILM).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
      .window(w)
      .orderBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .limit(5)
      .fetch
  }
}
