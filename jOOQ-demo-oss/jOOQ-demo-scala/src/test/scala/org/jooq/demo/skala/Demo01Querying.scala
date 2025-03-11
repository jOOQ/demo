package org.jooq.demo.skala

import org.jooq.Records.{intoMap, mapping}
import org.jooq.conf.RenderImplicitJoinType
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo.*
import org.jooq.demo.skala.db.Tables.*
import org.jooq.demo.skala.db.tables.Actor
import org.jooq.impl.DSL
import org.jooq.impl.DSL.*
import org.jooq.impl.SQLDataType.LOCALDATE
import org.jooq.scalaextensions.Conversions.*
import org.jooq.{Field, JSONFormat, Record1, Record2, Record3, Records, Result, XMLFormat}
import org.junit.Test

import java.math.BigDecimal
import java.time.LocalDate
import java.util
import scala.collection.convert.ImplicitConversions.*
import scala.util.Using


/**
 * This class shows various examples related to querying.
 */
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
    // More information here: https://blog.jooq.org/a-hidden-jooq-gem-foreach-loop-over-resultquery/
  }

  @Test
  def consumeLargeResults(): Unit = {
    // When working with large results, the imperative style Cursor or the functional style Stream
    // API can be used. In both cases, remember to treat these objects as resources, e.g. using the
    // Using function.

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

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/lazy-fetching/
    // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/lazy-fetching-with-streams/
  }

  @Test
  def typeSafetyUpdatableRecords(): Unit = {
    // UpdatableRecords implement a 1:1 mapping relationship with the underlying table, and offer
    // - Constructors
    // - getters and setters
    // - Simple CRUD functionality (more about this later)

    title("The resulting records can be nominally typed, too")
    val actor = ctx.selectFrom(ACTOR)
      .where(ACTOR.ACTOR_ID === 1L)
      .fetchSingle
    println("Resulting actor: %s %s".formatted(actor.getFirstName, actor.getLastName))

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/
  }

  @Test
  def typeSafetyWithUnions(): Unit = {
    // UNION and other set operations are completely type safe with jOOQ. The degree and data types of the
    // two subqueries must match, or the code won't compile!

    title("UNION / INTERSECT / EXCEPT are also type safe")
    val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .union(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
        .from(CUSTOMER)
        .where(CUSTOMER.FIRST_NAME like "A%"))
      .fetch

    // Try adding / removing projected columns, or changing data types

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/set-operations/set-operation-union/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/set-operations/set-operation-intersect/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/set-operations/set-operation-except/
  }

  @Test
  def typeSafetyWithInPredicate(): Unit = {
    // A lot of predicates, including the IN predicate, are also type safe, meaning the both operands have to
    // expose the same data type, for example Field<Long> can only be compared to Field<Long> or Select<Record1<Long>>
    // as seen below:

    title("A lot of predicate expressions also type safe")
    val r1 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .and(ACTOR.ACTOR_ID.in(
        select(FILM_ACTOR.ACTOR_ID)
        .from(FILM_ACTOR)))
      .fetch

    // This also works for row value expressions where a (String, String) tuple can only be compared with other
    // (String, String) tuples (e.g. Select<Record2<String, String>>):
    title("This also works for type safe row value expressions!")
    val r2 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.FIRST_NAME like "A%")
      .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME).in(
        select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
          .from(CUSTOMER)))
      .fetch

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/conditional-expressions/in-predicate/
    // - https://www.jooq.org/doc/latest/manual/sql-building/conditional-expressions/in-predicate-degree-n/
  }

  @Test
  def standardisationLimit(): Unit = {
    // LIMIT .. OFFSET, or OFFSET .. FETCH, or TOP .. START AT, or SKIP .. NEXT, is one of the most diverse
    // syntaxes in SQL, having been standardised only in SQL:2008. For jOOQ, there exists only a single API
    // that can be translated to all the alternative syntaxes. Check out the debug log to see what's being
    // generated by jOOQ:

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
    // jOOQ's DSL API follows a BNF grammar representation of the SQL language, allowing only correct order of
    // keywords and clauses. E.g. a JOIN operator requires an ON clause, which can be seen below. Try reordering
    // the operations, or replacing ON by WHERE, and the query won't compile anymore:

    title("Can't get JOIN syntax order wrong")
    val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .join(FILM_ACTOR)
      .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
      .where(ACTOR.FIRST_NAME like "A%")
      .fetch

    // More information here:
    // - https://blog.jooq.org/the-java-fluent-api-designer-crash-course/
  }

  @Test
  def typeSafetyAliasing(): Unit = {
    // When using the code generator, aliasing is type safe, meaning that an aliased table is of the same type
    // as the original table, exposing the same API, including type safe column access:

    title("Table aliases also provide column type safety")
    val a = ACTOR as "a"
    val fa = FILM_ACTOR as "fa"
    val result = ctx.select(a.FIRST_NAME, a.LAST_NAME)
      .from(a)
      .join(fa)
      .on(a.ACTOR_ID === fa.ACTOR_ID)
      .where(a.FIRST_NAME like "A%")
      .fetch

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/table-expressions/aliased-tables/
    // - https://blog.jooq.org/why-you-should-use-jooq-with-code-generation/

  }

  @Test
  def implicitToOneJoins(): Unit = {
    // A great feature of many ORMs is implicit path joins, where foreign keys can be navigated in the form
    // of paths from child to parent table, accessing the parent table's columns without explicitly writing
    // down the JOIN. The below query joins ADDRESS, CITY, and COUNTRY to CUSTOMER implicitly. Check out the
    // DEBUG log to see what query jOOQ is generating for this:

    title("No need to spell out trivial to-one joins")
    ctx.select(
        CUSTOMER.FIRST_NAME,
        CUSTOMER.LAST_NAME,
        CUSTOMER.address.city.country.COUNTRY_)
      .from(CUSTOMER)
      .orderBy(1, 2)
      .limit(5)
      .fetch

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-join/
    // - https://blog.jooq.org/why-you-should-use-jooq-with-code-generation/
  }

  @Test
  def implicitToManyJoins(): Unit = {
    // Navigating foreign keys from parent to children is possible as well. By default, all such paths must be
    // declared explicitly in the FROM clause:

    title("No need to spell out to-many joins either. Either use explicit to-many joins...")
    ctx.select(
        CUSTOMER.FIRST_NAME,
        CUSTOMER.LAST_NAME,
        countDistinct(CUSTOMER.rental.inventory.FILM_ID).as("distinct film rentals"))
      .from(CUSTOMER)
      .leftJoin(CUSTOMER.rental.inventory)
      .groupBy(CUSTOMER.CUSTOMER_ID)
      .orderBy(DSL.inline(3).desc)
      .limit(5)
      .fetch

    // If you can live with the quirkiness of implicit to-many join paths, then this feature can be enabled with
    // a Settings. Now, implicit to-many join paths work just like implicit to-one join paths, with the exception
    // that now, a seemingly scalar expression can produce cartesian products in your query!
    // This may be hard to debug because of the implicitness, so use this only sparingly!
    title("... or enable implicit to-many joins if you are OK with the 'interesting' semantics.")
    ctx.configuration
      .deriveSettings(s => s.withRenderImplicitJoinToManyType(RenderImplicitJoinType.LEFT_JOIN))
      .dsl
      .select(
        CUSTOMER.FIRST_NAME,
        CUSTOMER.LAST_NAME,
        // Now, the to-many path can be implicitly joined. Beware that this may produce very unexpected
        // cartesian products (just like any JOIN, of course), which may be hard to debug because of the
        // implicitness!
        countDistinct(CUSTOMER.rental.inventory.FILM_ID).as("distinct film rentals"))
      .from(CUSTOMER)
      .groupBy(CUSTOMER.CUSTOMER_ID)
      .orderBy(DSL.inline(3).desc)
      .limit(5)
      .fetch

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-to-many-join/
    // - https://blog.jooq.org/why-you-should-use-jooq-with-code-generation/
  }

  @Test
  def implicitPathCorrelation(): Unit = {
    // Correlated subqueries are frequent in SQL. For example, find all actors without any films:

    title("Ordinary correlated subquery")
    ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(notExists(
        selectOne
          .from(FILM_ACTOR)
          .where(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))))
      .fetch

    // Spelling out the correlation predicate FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID) is equally tedious (and error
    // prone) as spelling out a JOIN predicate. After all, this is an ANTI JOIN, so it works in a similar way. With
    // jOOQ, you can use paths again to implicitly correlate a subquery as follows, by starting declaring a path
    // in the subquery's FROM clause, starting again from ACTOR, which is declared in the outer scope:

    title("Implicitly path-correlated subquery")
    ctx
      .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(notExists(
        selectOne.from(ACTOR.filmActor)))
      .fetch

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-path-correlation/
  }

  @Test
  def nestedRecords(): Unit = {
    // In jOOQ, a Table expression is also a SelectField, meaning that you can project any table and retrieve the
    // corresponding UpdatableRecord (including the getters, setters, etc.) from it. Beware that while this is
    // very convenient, it's also likely inefficient as you're projecting too many columns.

    title("Need all columns of those active records?")
    val r = ctx
      .select(CUSTOMER, CUSTOMER.address.city.country)
      .from(CUSTOMER)
      .orderBy(1, 2)
      .limit(1)
      .fetchSingle
    println("Customer %s %s from %s".formatted(r.value1.getFirstName, r.value1.getLastName, r.value2.getCountry))

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/select-clause/select-clause-table/
  }

  @Test
  def nestedRowValuesWithAdHocConverters(): Unit = {
    // When working with DTOs, especially immutable ones, then ad-hoc conversion of scalar fields can be quite
    // useful. Call Field.convertFrom() on any Field expression to attach a Converter function to a Field for
    // all read operations ("from" means reading "from" the database, as opposed to writing "to" the database).
    // The below query applies the following conversion:
    //
    // - Record3<String, String, String>    The original jOOQ record
    // - Record3<String, String, Country>   The ad-hoc converter applied to the COUNTRY field
    // - Customer                           The DTO containing record data
    //
    // Try adding / removing fields from the SELECT clause and observe how the whole statement no longer compiles:

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
      .fetch(mapping(Customer(_, _, _)))

    r.forEach(println(_))

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/ad-hoc-converter/
  }

  @Test
  def deeplyNestedRowValuesWithAdHocConverters(): Unit = {
    // Ad-hoc converters can also apply to ROW value expressions, in case of which arbitrary levels of nesting
    // in a single SELECT become super simple. The following query again really projects:
    //
    // - Record1<Record3<String, String, Record1<String>>>    // This is the original nesting structure
    // - Record1<Record3<String, String, Country>>            // The inner-most nested record is mapped to Country
    // - Record1<Customer>                                    // The middle nested record is mapped to Customer
    //
    // There's no limit to the amount of type safe nesting and mapping you can achieve with jOOQ.

    case class Country(name: String)
    case class Customer(firstName: String, lastName: String, country: Country)

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

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/nested-records/
  }

  @Test
  def nestingToManyRelationships(): Unit = {
    // Nesting is also possible for collections using the standard SQL MULTISET operator, which jOOQ can emulate
    // on a variety of RDBMS using SQL/XML or SQL/JSON. The following query projects:
    //
    // - Film titles
    // - A collection of actors per film
    // - A collection of categories per film
    //
    // In other words, two nested child collections for a parent table, all directly written in SQL, without any
    // join deduplication or N+1 problems!

    title("The envy of all other ORMs: MULTISET!")
    val r = ctx
      .select(
        FILM.TITLE,

        // Implicit path correlation is again very powerful!
        multiset(select(FILM.actor.FIRST_NAME, FILM.actor.LAST_NAME).from(FILM.actor)),
        multiset(select(FILM.category.NAME).from(FILM.category))
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

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/multiset-value-constructor/
    // - https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/aggregate-functions/multiset-agg-function/
  }

  @Test
  def nestingToManyRelationshipsWithAdHocConverters(): Unit = {
    // And now, combine the MULTISET feature with ad-hoc conversion, and look at the following, completely type
    // safe query that nests the two collections into DTOs!

    title("MULTISET combined with ad-hoc converters and nested rows! 🤩")
    case class Name(firstName: String, lastName: String)
    case class Actor(name: Name)
    case class Category(name: String)
    case class Film(title: String, actors: util.List[Actor], categories: util.List[Category])

    // TODO: The amount of type witnesses required in the Scala version of this method are unwieldy
    //       I currently do not know if this is a limitation of the Scala / Java interop or me misunderstanding which
    //       implicit conversions should be used here... Please feel free to suggest improvements!
    val result = ctx
      .select(
        FILM.TITLE,
        multiset(
          select(row(FILM.actor.FIRST_NAME, FILM.actor.LAST_NAME).mapping(Name(_, _)))
            .from(FILM.actor)).mapping(Actor),
        multiset(
          select(FILM.category.NAME)
            .from(FILM.category)).mapping(Category))
      .from(FILM)
      .orderBy(FILM.TITLE)
      .limit(5)
      .fetch(mapping(Film(_, _, _)))

    result.forEach { film =>
      println("Film %s with categories %s and actors %s ".formatted(film.title, film.categories, film.actors))
    }

    // Try modifying the records and see what needs to be done to get the query to compile again

    // More information:
    // - https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/multiset-value-constructor/
    // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/ad-hoc-converter/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-path-correlation/

  }

  @Test
  def nestingToManyRelationshipsAsMaps(): Unit = {
    // Nested collections don't have to be nested as lists. Any other type of collection is possible. In fact, you
    // can provide any type of JDK Collector to produce a result from a nested collection in any form!

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
      .fetch(mapping(Film(_, _)))

    result.forEach { (film: Film) =>
      println("")
      println("Film %s with revenue: ".formatted(film.title))
      film.revenue.forEach((d: LocalDate, r: BigDecimal) => println("  %s: %s".formatted(d, r)))
    }
  }
}
