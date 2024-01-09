package org.jooq.demo.kotlin

import org.jooq.*
import org.jooq.Records.mapping
import org.jooq.conf.RenderImplicitJoinType
import org.jooq.conf.Settings
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

        // Try removing type inference to see what r really is
    }

    @Test
    fun consumeLargeResults() {
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
    }

    @Test
    fun typeSafetyActiveRecords() {
        title("The resulting records can be nominally typed, too")
        val actor = ctx.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(1L))
            .fetchSingle()
        println("Resulting actor: ${actor.firstName} ${actor.lastName}")

        // More on these UpdatableRecords later
    }

    @Test
    fun typeSafetyWithUnions() {
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
    }

    @Test
    fun typeSafetyWithInPredicate() {
        title("A lot of predicate expressions also type safe")
        val r1 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.like("A%"))
            .and(ACTOR.ACTOR_ID.`in`(select(FILM_ACTOR.ACTOR_ID).from(FILM_ACTOR)))
            .fetch()

        title("This also works for type safe row value expressions!")
        val r2 = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.FIRST_NAME.like("A%"))
            .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME).`in`(
                select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME).from(CUSTOMER)
            ))
            .fetch()
    }

    @Test
    fun standardisationLimit() {
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
        title("Can't get JOIN syntax order wrong")
        val result = ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .join(FILM_ACTOR)
            .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
            .where(ACTOR.FIRST_NAME.like("A%"))
            .fetch()

        // Try reordering the operations, or replacing ON by WHERE
    }

    @Test
    fun typeSafetyAliasing() {
        title("Table aliases also provide column type safety")
        val a = ACTOR.`as`("a")
        val fa = FILM_ACTOR.`as`("fa")
        val result = ctx.select(a.FIRST_NAME, a.LAST_NAME)
            .from(a)
            .join(fa)
            .on(a.ACTOR_ID.eq(fa.ACTOR_ID))
            .where(a.FIRST_NAME.like("A%"))
            .fetch()

        // Try reordering the operations, or replacing ON by WHERE
    }

    @Test
    fun typeSafetyUnqualifiedColumnReferences() {
        title("Some kotlin specific fun with with()")
        with(ACTOR) {
            val result = ctx
                .select(FIRST_NAME, LAST_NAME)
                .from(ACTOR)
                .where(FIRST_NAME.like("A%"))
                .fetch()
        }
    }

    @Test
    fun implicitToOneJoins() {
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
    }

    @Test
    fun implicitToManyJoins() {
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

        title("... or enable implicit to-many joins if you are OK with the 'interesting' semantics.")
        ctx.configuration()
            .deriveSettings { s -> s.withRenderImplicitJoinToManyType(RenderImplicitJoinType.LEFT_JOIN) }
            .dsl()
            .select(
                CUSTOMER.FIRST_NAME,
                CUSTOMER.LAST_NAME,

                // Now, the to-many path can be implicitly joined. Beware that this may produce very unexpected
                // cartesian products (just like any JOIN, of course), which may be hard to debug because of the
                // implicitness!
                countDistinct(CUSTOMER.rental().inventory().FILM_ID).`as`("distinct film rentals")
            )
            .from(CUSTOMER)
            .groupBy(CUSTOMER.CUSTOMER_ID)
            .orderBy(inline(3).desc())
            .limit(5)
            .fetch()
    }

    @Test
    fun nestedRecords() {
        title("Need all columns of those active records?")
        val (customer, country) = ctx.select(CUSTOMER, CUSTOMER.address.city.country)
            .from(CUSTOMER)
            .orderBy(1, 2)
            .limit(1)
            .fetchSingle()
        println("Customer ${customer.firstName} ${customer.lastName} from ${country.formatJSON()}")

        // Though beware. While this is convenient, it's also likely inefficient as you're projecting too many columns
    }

    @Test
    fun nestedRowValuesWithAdHocConverters() {
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
    }

    @Test
    fun deeplyNestedRowValuesWithAdHocConverters() {
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
    }

    @Test
    fun nestingToManyRelationships() {
        title("The envy of all other ORMs: MULTISET!")
        val r: Result<Record3<
                String?,
                Result<Record2<String?, LocalDateTime?>>,
                Result<Record1<String?>>
                >> = ctx
            .select(
                FILM.TITLE,
                multiset(
                    select(
                        FILM.actor.FIRST_NAME,
                        FILM.actor.LAST_UPDATE
                    )

                    // Implicit path correlation is very powerful!
                    // https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-path-correlation/
                    .from(FILM.actor)
                ),
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
    }

    @Test
    fun nestingToManyRelationshipsWithAdHocConverters() {
        title("MULTISET combined with ad-hoc converters and nested rows! 🤩")
        data class Name(val firstName: String?, val lastName: String?)
        data class Actor(val name: Name)
        data class Category(val name: String?)
        data class Film(val title: String?, val actors: List<Actor>, val categories: List<Category>)

        val result: List<Film> = ctx
            .select(
                FILM.TITLE,
                multiset(
                    select(
                        row(
                            FILM_ACTOR.actor.FIRST_NAME,
                            FILM_ACTOR.actor.LAST_NAME
                        ).mapping(::Name))
                    .from(FILM_ACTOR)
                    .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                ).mapping(::Actor),
                multiset(
                    select(FILM_CATEGORY.category().NAME)
                    .from(FILM_CATEGORY)
                    .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                ).mapping(::Category)
            )
            .from(FILM)
            .orderBy(FILM.TITLE)
            .limit(5)
            .fetch(mapping(::Film))

        for (film in result) {
            println("Film ${film.title} with categories ${film.categories} and actors ${film.actors}")
        }

        // Try modifying the records and see what needs to be done to get the query to compile again
    }

    @Test
    fun nestingToManyRelationshipsAsMaps() {
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