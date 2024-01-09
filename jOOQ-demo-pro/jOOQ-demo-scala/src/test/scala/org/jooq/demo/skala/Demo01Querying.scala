package org.jooq.demo.skala

import org.jooq.Records.intoMap
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables._
import org.jooq.demo.skala.db.tables.Actor
import org.jooq.impl.DSL._
import org.jooq.impl.SQLDataType.LOCALDATE
import org.jooq.scalaextensions.Conversions._
import org.jooq.{Field, JSONFormat, Record1, Record2, Record3, Result, XMLFormat}
import org.junit.Test

import java.math.BigDecimal
import java.time.LocalDate
import java.util
import scala.collection.convert.ImplicitConversions._
import scala.util.Using


class Demo01Querying extends AbstractDemo {

  @Test
  def fetchDual(): Unit = {
    title("A simple SELECT 1 FROM DUAL (if needed)")
    println(ctx.selectOne.fetch)
    // Turn on debug logging to see contents
  }

  @Test
  def typeSafetySimpleQuery(): Unit = {
    title("A simple type safe query")
    val r = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.LAST_NAME like "A%")
      .orderBy(ACTOR.FIRST_NAME.asc).fetch

    // Try playing around with data types above:
    // - Use auto-completion to access columns from the table
    // - Adding / removing columns from the projection
    // - Changing the LIKE predicate's argument to an int
  }

  @Test
  def consumeRecordsForEach(): Unit = {
    title("ResultQuery<R> extends Iterable<R>, which means that we can iterate queries!")
    for (r <- ctx.select(FILM.FILM_ID, FILM.TITLE)
      .from(FILM)
      .limit(5))
      println("Film %s: %s".formatted(r.value1, r.value2))

    title("This also means we can call Iterable::forEach on it")
    ctx.select(FILM.FILM_ID, FILM.TITLE)
      .from(FILM)
      .limit(5)
      .forEach(r => println("Film %s: %s".formatted(r.value1, r.value2)))
    // Try removing type inference to see what r really is
  }

  @Test
  def consumeLargeResults(): Unit = {
    title("Imperative consumption of large results using Cursor, keeping an open ResultSet behind the scenes")
    Using(ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .from(ACTOR)
        .where(ACTOR.ACTOR_ID < 5L)
        .fetchLazy) { c =>
      for (r <- c)
        println("Actor: %s %s".formatted(r.value1, r.value2))
    }

    title("Functional consumption of large results using Stream, keeping an open ResultSet behind the scenes")
    Using(ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID < 5L)
      .fetchStream) { s =>
      s.forEach(r => println("Actor: %s %s".formatted(r.value1, r.value2)))
    }
  }

  @Test
  def typeSafetyActiveRecords(): Unit = {
    title("The resulting records can be nominally typed, too")
    val actor = ctx.selectFrom(ACTOR)
      .where(ACTOR.ACTOR_ID === 1L)
      .fetchSingle
    println("Resulting actor: %s %s".formatted(actor.getFirstName, actor.getLastName))

    // More on these UpdatableRecords later
  }

  @Test
  def typeSafetyWithUnions(): Unit = {
    title("UNION / INTERSECT / EXCEPT are also type safe")
    val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .union(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
        .from(CUSTOMER)
        .where(CUSTOMER.FIRST_NAME like "A%"))
      .fetch
    // Try adding / removing projected columns, or changing data types
  }

  @Test
  def typeSafetyWithInPredicate(): Unit = {
    title("A lot of predicate expressions also type safe")
    val r1 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .and(ACTOR.ACTOR_ID.in(
        select(FILM_ACTOR.ACTOR_ID)
        .from(FILM_ACTOR)))
      .fetch

    title("This also works for type safe row value expressions!")
    val r2 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME).in(
        select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
          .from(CUSTOMER)))
      .fetch
  }

  @Test
  def standardisationLimit(): Unit = {
    title("LIMIT .. OFFSET works in (almost) all dialects")
    val r1 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .orderBy(ACTOR.ACTOR_ID)
      .limit(10)
      .offset(10)
      .fetch
  }

  @Test
  def typeSafetySyntaxChecking(): Unit = {
    title("Can't get JOIN syntax order wrong")
    val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .join(FILM_ACTOR)
      .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
      .where(ACTOR.FIRST_NAME like "A%")
      .fetch
    // Try reordering the operations, or replacing ON by WHERE
  }

  @Test
  def typeSafetyAliasing(): Unit = {
    title("Table aliases also provide column type safety")
    val a = ACTOR as "a"
    val fa = FILM_ACTOR as "fa"
    val result = ctx.select(a.FIRST_NAME, a.LAST_NAME)
      .from(a)
      .join(fa)
      .on(a.ACTOR_ID === fa.ACTOR_ID)
      .where(a.FIRST_NAME like "A%")
      .fetch
  }

  @Test
  def implicitToOneJoins(): Unit = {
    title("No need to spell out trivial to-one joins")
    ctx.select(
        CUSTOMER.FIRST_NAME,
        CUSTOMER.LAST_NAME,
        CUSTOMER.address.city.country.COUNTRY_)
      .from(CUSTOMER)
      .orderBy(1, 2)
      .limit(5)
      .fetch
  }

  @Test
  def nestedRecords(): Unit = {
    title("Need all columns of those active records?")
    val r = ctx
      .select(CUSTOMER, CUSTOMER.address.city.country)
      .from(CUSTOMER)
      .orderBy(1, 2)
      .limit(1)
      .fetchSingle
    println("Customer %s %s from %s".formatted(r.value1.getFirstName, r.value1.getLastName, r.value2.getCountry))
    // Though beware. While this is convenient, it's also likely inefficient as you're projecting too many columns
  }

  @Test
  def nestedRowValuesWithAdHocConverters(): Unit = {
    case class Country(name: String)
    case class Customer(firstName: String, lastName: String, country: Country)

    title("Nesting is particularly useful when using ad-hoc converters")
    val r = ctx
      .select(
        CUSTOMER.FIRST_NAME,
        CUSTOMER.LAST_NAME,
        CUSTOMER.address.city.country.COUNTRY_.convertFrom(Country(_)))
      .from(CUSTOMER)
      .orderBy(1, 2)
      .limit(5)
      .fetch(Customer)

    r.forEach(println(_))
  }

  @Test
  def deeplyNestedRowValuesWithAdHocConverters(): Unit = {
    case class Country(val name: String)
    case class Customer(val firstName: String, val lastName: String, val country: Country)

    title("Nesting is particularly useful when using ad-hoc converters")
    val r = ctx
      .select(
        row(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME,
          row(CUSTOMER.address.city.country.COUNTRY_).mapping(Country(_))
        ).mapping(Customer(_, _, _)))
      .from(CUSTOMER)
      .orderBy(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
      .limit(5)
      .fetch(_.value1)
    r.forEach(println(_))
  }

  @Test
  def nestingToManyRelationships(): Unit = {
    title("The envy of all other ORMs: MULTISET!")
    val r = ctx
      .select(
        FILM.TITLE,
        multiset(
          select(FILM_ACTOR.actor.FIRST_NAME, FILM_ACTOR.actor.LAST_NAME)
            .from(FILM_ACTOR)
            .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))),
        multiset(
          select(FILM_CATEGORY.category.NAME)
            .from(FILM_CATEGORY)
            .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID)))
      )
      .from(FILM)
      .orderBy(FILM.TITLE)
      .limit(5)
      .fetch

    // By the way, any jOOQ Result and Record can be exported as CSV, XML, JSON, Text, etc.
    title("Formatted as JSON")
    println(r.formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.format(true).header(false)))

    title("Formatted as XML")
    println(r.formatXML(XMLFormat.DEFAULT_FOR_RESULTS.format(true).header(false)))
  }

  @Test
  def nestingToManyRelationshipsWithAdHocConverters(): Unit = {
    title("MULTISET combined with ad-hoc converters and nested rows! 🤩")
    case class Name(firstName: String, lastName: String)
    case class Actor(name: Name)
    case class Category(name: String)
    case class Film(title: String, actors: util.List[Actor], categories: util.List[Category])

    // TODO: The amount of type witnesses required in the Scala version of this method are unwieldy
    //       I currently do not know if this is a limitation of the Scala / Java interop or me misunderstanding which
    //       implicit conversions should be used here... Please feel free to suggest improvements!
    val result: util.List[Film] = ctx
      .select(
        FILM.TITLE,
        multiset(
          select(row(FILM_ACTOR.actor.FIRST_NAME, FILM_ACTOR.actor.LAST_NAME).mapping(Name(_, _)))
            .from(FILM_ACTOR)
            .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))).mapping(Actor),
        multiset(
          select(FILM_CATEGORY.category.NAME)
            .from(FILM_CATEGORY)
            .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))).mapping(Category))
      .from(FILM)
      .orderBy(FILM.TITLE)
      .limit(5)
      .fetch(Film)

    result.forEach { film =>
      println("Film %s with categories %s and actors %s ".formatted(film.title, film.categories, film.actors))
    }
    // Try modifying the records and see what needs to be done to get the query to compile again
  }

  @Test
  def nestingToManyRelationshipsAsMaps(): Unit = {
    title("Arbitrary nested data structures are possible")
    case class Film(title: String, revenue: util.Map[LocalDate, BigDecimal])

    val result = ctx
      .select(
        FILM.TITLE,
        multiset(
          select(PAYMENT.PAYMENT_DATE.cast(LOCALDATE), sum(PAYMENT.AMOUNT))
            .from(PAYMENT)
            .where(PAYMENT.rental.inventory.FILM_ID.eq(FILM.FILM_ID))
            .groupBy(PAYMENT.PAYMENT_DATE.cast(LOCALDATE))
            .orderBy(PAYMENT.PAYMENT_DATE.cast(LOCALDATE))).intoMap())
      .from(FILM)
      .orderBy(FILM.TITLE)
      .fetch(Film)

    result.forEach { film: Film =>
      println("")
      println("Film %s with revenue: ".formatted(film.title))
      film.revenue.forEach((d: LocalDate, r: BigDecimal) => println("  %s: %s".formatted(d, r)))
    }
  }
}
