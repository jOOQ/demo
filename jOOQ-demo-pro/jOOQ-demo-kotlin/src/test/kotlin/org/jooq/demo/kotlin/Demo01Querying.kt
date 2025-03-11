package org.jooq.demo.kotlin

import org.jooq.*
import org.jooq.Records.mapping
import org.jooq.conf.RenderImplicitJoinType
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.jooq.impl.DSL.*
import org.jooq.impl.SQLDataType.LOCALDATE
import org.jooq.kotlin.intoMap
import org.jooq.kotlin.mapping
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * This class shows various examples related to querying.
 */
class Demo01Querying : AbstractDemo() {

    @Test
    fun fetchDual() {
        title("A simple SELECT 1 FROM DUAL (if needed)")
        println(ctx.selectOne().fetch())

        // Turn on debug logging to see contents
    }

    @Test
    fun typeSafetySimpleQuery() {
        title("A simple type safe query")

        val r = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_UPDATE)
            .from(ACTOR)
            .where(ACTOR.LAST_NAME.like("A%"))
            .orderBy(ACTOR.FIRST_NAME.asc())
            .fetch()

        // Try playing around with data types above:
        // - Use auto-completion to access columns from the table
        // - Adding / removing columns from the projection
        // - Changing the LIKE predicate's argument to an int
    }

    @Test
    fun consumeRecordsForEach() {
        title("ResultQuery<R> extends Iterable<R>, which means that we can iterate queries!")

        // We can use jOOQ's kotlin support on Record[N] types, which offer component[N] methods to
        // help type safely destructure records until degree 22.
        for ((firstName, lastName) in ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(5L))
        ) {
            println("Actor: $firstName $lastName")
        }

        title("This also means we can call Iterable::forEach on it")
        ctx.select(FILM.FILM_ID, FILM.TITLE)
            .from(FILM)
            .limit(5)
            .forEach { (filmId, title) -> println("Film $filmId: $title") }

        // More information here: https://blog.jooq.org/a-hidden-jooq-gem-foreach-loop-over-resultquery/
    }

    @Test
    fun consumeLargeResults() {
        // When working with large results, the imperative style Cursor or the functional style Stream
        // API can be used. In both cases, remember to treat these objects as resources, e.g. using the
        // use extension function.

        title("Imperative consumption of large results using Cursor, keeping an open ResultSet behind the scenes")
        ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(5L))
            .fetchLazy().use { c ->
                for ((firstName, lastName) in c)
                    println("Actor: $firstName $lastName")
            }

        title("Functional consumption of large results using Stream, keeping an open ResultSet behind the scenes")
        ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(5L))
            .fetchStream().use { s ->
                s.forEach { (firstName, lastName) ->
                    println("Actor: $firstName $lastName")
                }
            }

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/lazy-fetching/
        // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/lazy-fetching-with-streams/
    }

    @Test
    fun typeSafetyUpdatableRecords() {
        // UpdatableRecords implement a 1:1 mapping relationship with the underlying table, and offer
        // - Constructors
        // - getters and setters
        // - Simple CRUD functionality (more about this later)

        title("The resulting records can be nominally typed, too")
        val actor = ctx.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(1L))
            .fetchSingle()
        println("Resulting actor: ${actor.firstName} ${actor.lastName}")

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/
    }

    @Test
    fun typeSafetyWithUnions() {
        // UNION and other set operations are completely type safe with jOOQ. The degree and data types of the
        // two subqueries must match, or the code won't compile!

        title("UNION / INTERSECT / EXCEPT are also type safe")
        val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.like("A%"))
            .unionAll(
                select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.like("A%"))
            )
            .fetch()

        // Try adding / removing projected columns, or changing data types

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/set-operations/set-operation-union/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/set-operations/set-operation-intersect/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/set-operations/set-operation-except/
    }

    @Test
    fun typeSafetyWithInPredicate() {
        // A lot of predicates, including the IN predicate, are also type safe, meaning the both operands have to
        // expose the same data type, for example Field<Long> can only be compared to Field<Long> or Select<Record1<Long>>
        // as seen below:

        title("A lot of predicate expressions also type safe")
        val r1 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.like("A%"))
            .and(ACTOR.ACTOR_ID.`in`(select(FILM_ACTOR.ACTOR_ID).from(FILM_ACTOR)))
            .fetch()

        // This also works for row value expressions where a (String, String) tuple can only be compared with other
        // (String, String) tuples (e.g. Select<Record2<String, String>>):
        title("This also works for type safe row value expressions!")
        val r2 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.like("A%"))
            .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME).`in`(
                select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME).from(CUSTOMER)
            ))
            .fetch()

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/conditional-expressions/in-predicate/
        // - https://www.jooq.org/doc/latest/manual/sql-building/conditional-expressions/in-predicate-degree-n/
    }

    @Test
    fun standardisationLimit() {
        // LIMIT .. OFFSET, or OFFSET .. FETCH, or TOP .. START AT, or SKIP .. NEXT, is one of the most diverse
        // syntaxes in SQL, having been standardised only in SQL:2008. For jOOQ, there exists only a single API
        // that can be translated to all the alternative syntaxes. Check out the debug log to see what's being
        // generated by jOOQ:

        title("LIMIT .. OFFSET works in (almost) all dialects")
        val r1 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.like("A%"))
            .orderBy(ACTOR.ACTOR_ID)
            .limit(10)
            .offset(10)
            .fetch()
    }

    @Test
    fun typeSafetySyntaxChecking() {
        // jOOQ's DSL API follows a BNF grammar representation of the SQL language, allowing only correct order of
        // keywords and clauses. E.g. a JOIN operator requires an ON clause, which can be seen below. Try reordering
        // the operations, or replacing ON by WHERE, and the query won't compile anymore:

        title("Can't get JOIN syntax order wrong")
        val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .join(FILM_ACTOR)
            .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .where(ACTOR.FIRST_NAME.like("A%"))
            .fetch()

        // More information here:
        // - https://blog.jooq.org/the-java-fluent-api-designer-crash-course/
    }

    @Test
    fun typeSafetyAliasing() {
        // When using the code generator, aliasing is type safe, meaning that an aliased table is of the same type
        // as the original table, exposing the same API, including type safe column access:

        title("Table aliases also provide column type safety")
        val a = ACTOR.`as`("a")
        val fa = FILM_ACTOR.`as`("fa")
        val result = ctx.select(a.FIRST_NAME, a.LAST_NAME)
            .from(a)
            .join(fa)
            .on(a.ACTOR_ID.eq(fa.ACTOR_ID))
            .where(a.FIRST_NAME.like("A%"))
            .fetch()

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/table-expressions/aliased-tables/
        // - https://blog.jooq.org/why-you-should-use-jooq-with-code-generation/
    }

    @Test
    fun typeSafetyUnqualifiedColumnReferences() {
        // The below isn't jOOQ specific, but occasionally, it can be useful to do these things with jOOQ API as well
        // to abbreviate SQL statements by putting a table in scope with "with."

        title("Some kotlin specific fun with with()")
        with(ACTOR) {
            val result = ctx
                .select(FIRST_NAME, LAST_NAME)
                .from(ACTOR)
                .where(FIRST_NAME.like("A%"))
                .fetch()

            // Beware that jOOQ's API is vast, and you now have implicit access to it, e.g.:
            name
            unqualifiedName
            references
        }
    }

    @Test
    fun implicitToOneJoins() {
        // A great feature of many ORMs is implicit path joins, where foreign keys can be navigated in the form
        // of paths from child to parent table, accessing the parent table's columns without explicitly writing
        // down the JOIN. The below query joins ADDRESS, CITY, and COUNTRY to CUSTOMER implicitly. Check out the
        // DEBUG log to see what query jOOQ is generating for this:

        title("No need to spell out trivial to-one joins")
        ctx.select(
            CUSTOMER.FIRST_NAME,
            CUSTOMER.LAST_NAME,
            CUSTOMER.address.city.country.COUNTRY_
        )
            .from(CUSTOMER)
            .orderBy(1, 2)
            .limit(5)
            .fetch()

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-join/
        // - https://blog.jooq.org/why-you-should-use-jooq-with-code-generation/
    }

    @Test
    fun implicitToManyJoins() {
        // Navigating foreign keys from parent to children is possible as well. By default, all such paths must be
        // declared explicitly in the FROM clause:

        title("No need to spell out to-many joins either. Either use explicit to-many joins...")
        ctx.select(
            CUSTOMER.FIRST_NAME,
            CUSTOMER.LAST_NAME,
            countDistinct(CUSTOMER.rental().inventory().FILM_ID).`as`("distinct film rentals")
        )
            .from(CUSTOMER)

            // Add an explicit path join for the to-many path
            .leftJoin(CUSTOMER.rental().inventory())
            .groupBy(CUSTOMER.CUSTOMER_ID)
            .orderBy(inline(3).desc())
            .limit(5)
            .fetch()

        // If you can live with the quirkiness of implicit to-many join paths, then this feature can be enabled with
        // a Settings. Now, implicit to-many join paths work just like implicit to-one join paths, with the exception
        // that now, a seemingly scalar expression can produce cartesian products in your query!
        // This may be hard to debug because of the implicitness, so use this only sparingly!

        title("... or enable implicit to-many joins if you are OK with the 'interesting' semantics.")
        ctx.configuration()
            .deriveSettings { s -> s.withRenderImplicitJoinToManyType(RenderImplicitJoinType.LEFT_JOIN) }
            .dsl()
            .select(
                CUSTOMER.FIRST_NAME,
                CUSTOMER.LAST_NAME,
                countDistinct(CUSTOMER.rental().inventory().FILM_ID).`as`("distinct film rentals")
            )
            .from(CUSTOMER)
            .groupBy(CUSTOMER.CUSTOMER_ID)
            .orderBy(inline(3).desc())
            .limit(5)
            .fetch()

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-to-many-join/
        // - https://blog.jooq.org/why-you-should-use-jooq-with-code-generation/
    }

    @Test
    fun implicitPathCorrelation() {
        // Correlated subqueries are frequent in SQL. For example, find all actors without any films:
        title("Ordinary correlated subquery")
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(notExists(
                selectOne()
                .from(FILM_ACTOR)
                .where(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
            ))
            .fetch()

        // Spelling out the correlation predicate FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID) is equally tedious (and error
        // prone) as spelling out a JOIN predicate. After all, this is an ANTI JOIN, so it works in a similar way. With
        // jOOQ, you can use paths again to implicitly correlate a subquery as follows, by starting declaring a path
        // in the subquery's FROM clause, starting again from ACTOR, which is declared in the outer scope:
        title("Implicitly path-correlated subquery")
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(notExists(selectOne().from(ACTOR.filmActor)))
            .fetch()

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-path-correlation/
    }

    @Test
    fun nestedRecords() {
        // In jOOQ, a Table expression is also a SelectField, meaning that you can project any table and retrieve the
        // corresponding UpdatableRecord (including the getters, setters, etc.) from it. Beware that while this is
        // very convenient, it's also likely inefficient as you're projecting too many columns.

        title("Need all columns of those active records?")
        val (customer, country) = ctx.select(CUSTOMER, CUSTOMER.address.city.country)
            .from(CUSTOMER)
            .orderBy(1, 2)
            .limit(1)
            .fetchSingle()
        println("Customer ${customer.firstName} ${customer.lastName} from ${country.formatJSON()}")

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/select-clause/select-clause-table/
    }

    @Test
    fun nestedRowValuesWithAdHocConverters() {
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

        data class Country(val name: String?)
        data class Customer(val firstName: String?, val lastName: String?, val country: Country)

        title("Nesting is particularly useful when using ad-hoc converters")
        val r = ctx
            .select(
                CUSTOMER.FIRST_NAME,
                CUSTOMER.LAST_NAME,
                CUSTOMER.address.city.country.COUNTRY_.convertFrom(::Country)
            )
            .from(CUSTOMER)
            .orderBy(1, 2)
            .limit(5)
            .fetch(mapping(::Customer))
        r.forEach { t: Customer? -> println(t) }

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/ad-hoc-converter/
    }

    @Test
    fun deeplyNestedRowValuesWithAdHocConverters() {
        // Ad-hoc converters can also apply to ROW value expressions, in case of which arbitrary levels of nesting
        // in a single SELECT become super simple. The following query again really projects:
        //
        // - Record1<Record3<String, String, Record1<String>>>    // This is the original nesting structure
        // - Record1<Record3<String, String, Country>>            // The inner-most nested record is mapped to Country
        // - Record1<Customer>                                    // The middle nested record is mapped to Customer
        //
        // There's no limit to the amount of type safe nesting and mapping you can achieve with jOOQ.

        data class Country(val name: String?)
        data class Customer(val firstName: String?, val lastName: String?, val country: Country)

        title("Nesting is particularly useful when using ad-hoc converters")
        val r = ctx
            .select(row(
                    CUSTOMER.FIRST_NAME,
                    CUSTOMER.LAST_NAME,
                    row(CUSTOMER.address.city.country.COUNTRY_).mapping(::Country)
                ).mapping(::Customer))
            .from(CUSTOMER)
            .orderBy(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
            .limit(5)
            .forEach { (customer) -> println(customer) }

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/nested-records/
    }

    @Test
    fun nestingToManyRelationships() {
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
        val r: Result<Record3<
                String?,
                Result<Record2<String?, LocalDateTime?>>,
                Result<Record1<String?>>
                >> = ctx
            .select(
                FILM.TITLE,

                // Implicit path correlation is again very powerful!
                multiset(select(FILM.actor.FIRST_NAME, FILM.actor.LAST_UPDATE).from(FILM.actor)),
                multiset(select(FILM.category.NAME).from(FILM.category))
            )
            .from(FILM)
            .orderBy(FILM.TITLE)
            .limit(5)
            .fetch()

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
    fun nestingToManyRelationshipsWithAdHocConverters() {
        // And now, combine the MULTISET feature with ad-hoc conversion, and look at the following, completely type
        // safe query that nests the two collections into DTOs!
        title("MULTISET combined with ad-hoc converters and nested rows! 🤩")
        data class Name(val firstName: String?, val lastName: String?)
        data class Actor(val name: Name)
        data class Category(val name: String?)
        data class Film(val title: String?, val actors: List<Actor>, val categories: List<Category>)

        with(FILM) {
        val result: List<Film> = ctx
            .select(
                FILM.TITLE,
                multiset(
                    select(row(FILM.actor.FIRST_NAME, FILM.actor.LAST_NAME).mapping(::Name))
                    .from(FILM)
                ).mapping(::Actor),
                multiset(
                    select(FILM.category.NAME)
                    .from(FILM)
                ).mapping(::Category)
            )
            .from(FILM)
            .orderBy(FILM.TITLE)
            .limit(5)
            .fetch(mapping(::Film))
        }

        for (film in result) {
            println("Film ${film.title} with categories ${film.categories} and actors ${film.actors}")
        }

        // Try modifying the records and see what needs to be done to get the query to compile again

        // More information:
        // - https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/multiset-value-constructor/
        // - https://www.jooq.org/doc/latest/manual/sql-execution/fetching/ad-hoc-converter/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-path-correlation/
    }

    @Test
    fun nestingToManyRelationshipsAsMaps() {
        // Nested collections don't have to be nested as lists. Any other type of collection is possible. In fact, you
        // can provide any type of JDK Collector to produce a result from a nested collection in any form!

        // The kotlin version clearly wins here, given the extension function Field<Result<Record2<K, V>>>.intoMap()
        // can be applied to any nested collection with a key/value pair.

        title("Arbitrary nested data structures are possible")
        data class Film(val title: String?, val revenue: Map<LocalDate, BigDecimal>)

        val result: List<Film> = ctx
            .select(
                FILM.TITLE,
                multiset(
                    select(PAYMENT.PAYMENT_DATE.cast(LOCALDATE), sum(PAYMENT.AMOUNT))
                    .from(PAYMENT)
                    .where(PAYMENT.rental.inventory.FILM_ID.eq(FILM.FILM_ID))
                    .groupBy(PAYMENT.PAYMENT_DATE.cast(LOCALDATE))
                    .orderBy(PAYMENT.PAYMENT_DATE.cast(LOCALDATE))
                ).intoMap()
            )
            .from(FILM)
            .orderBy(FILM.TITLE)
            .fetch(mapping(::Film))

        for (film in result) {
            println("")
            println("Film ${film.title} with revenue: ")
            film.revenue.forEach { (d, r) -> println("  $d: $r") }
        }
    }
}