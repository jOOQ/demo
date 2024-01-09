package org.jooq.demo.java;

import org.jooq.*;
import org.jooq.conf.RenderImplicitJoinType;
import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.tables.Actor;
import org.jooq.demo.java.db.tables.FilmActor;
import org.jooq.demo.java.db.tables.records.ActorRecord;
import org.jooq.demo.java.db.Tables;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.jooq.Records.intoMap;
import static org.jooq.Records.mapping;
import static org.jooq.demo.java.db.Tables.*;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.DATE;
import static org.jooq.impl.SQLDataType.LOCALDATE;

public class Demo01Querying extends AbstractDemo {

    @Test
    public void fetchDual() {
        title("A simple SELECT 1 FROM DUAL (if needed)");
        println(ctx.selectOne().fetch());

        // Turn on debug logging to see contents
    }

    @Test
    public void typeSafetySimpleQuery() {
        title("A simple type safe query");
        Result<Record2<String, String>> r =
            ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
               .from(ACTOR)
               .where(ACTOR.LAST_NAME.like("A%"))
               .orderBy(ACTOR.FIRST_NAME.asc())
               .fetch();

        // Try playing around with data types above:
        // - Use auto-completion to access columns from the table
        // - Adding / removing columns from the projection
        // - Changing the LIKE predicate's argument to an int
    }

    @Test
    public void consumeRecordsForEach() {
        title("ResultQuery<R> extends Iterable<R>, which means that we can iterate queries!");
        for (var r : ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(5L))
        ) {
            println("Actor: %s %s".formatted(r.value1(), r.value2()));
        }

        title("This also means we can call Iterable::forEach on it");
        ctx.select(FILM.FILM_ID, FILM.TITLE)
           .from(FILM)
           .limit(5)
           .forEach(r -> println("Film %s: %s".formatted(r.value1(), r.value2())));

        // Try removing type inference to see what r really is
    }

    @Test
    public void consumeLargeResults() {
        title("Imperative consumption of large results using Cursor, keeping an open ResultSet behind the scenes");
        try (Cursor<Record2<String, String>> c = ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(5L))
            .fetchLazy()
        ) {
            for (Record2<String, String> r : c)
                println("Actor: %s %s".formatted(r.value1(), r.value2()));
        }

        title("Functional consumption of large results using Stream, keeping an open ResultSet behind the scenes");
        try (Stream<Record2<String, String>> s = ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(5L))
            .fetchStream()
        ) {
            s.forEach(r -> println("Actor: %s %s".formatted(r.value1(), r.value2())));
        }
    }

    @Test
    public void typeSafetyActiveRecords() {
        title("The resulting records can be nominally typed, too");
        ActorRecord actor =
        ctx.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(1L))
            .fetchSingle();

        println("Resulting actor: %s %s".formatted(actor.getFirstName(), actor.getLastName()));
        // More on these UpdatableRecords later
    }

    @Test
    public void typeSafetyWithUnions() {
        title("UNION / INTERSECT / EXCEPT are also type safe");
        var result =
            ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
               .from(ACTOR)
               .where(ACTOR.FIRST_NAME.like("A%"))
               .union(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                    .from(CUSTOMER)
                    .where(CUSTOMER.FIRST_NAME.like("A%")))
               .fetch();

        // Try adding / removing projected columns, or changing data types
    }

    @Test
    public void typeSafetyWithInPredicate() {
        title("A lot of predicate expressions also type safe");
        var r1 =
            ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.FIRST_NAME.like("A%"))
                .and(ACTOR.ACTOR_ID.in(select(FILM_ACTOR.ACTOR_ID).from(FILM_ACTOR)))
                .fetch();

        title("This also works for type safe row value expressions!");
        var r2 =
            ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
               .from(ACTOR)
               .where(ACTOR.FIRST_NAME.like("A%"))
               .and(row(ACTOR.FIRST_NAME, ACTOR.LAST_NAME).in(select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME).from(CUSTOMER)))
               .fetch();
    }

    @Test
    public void standardisationLimit() {
        title("LIMIT .. OFFSET works in (almost) all dialects");
        var r1 =
            ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.FIRST_NAME.like("A%"))
                .orderBy(ACTOR.ACTOR_ID)
                .limit(10)
                .offset(10)
                .fetch();
    }

    @Test
    public void typeSafetySyntaxChecking() {
        title("Can't get JOIN syntax order wrong");
        var result =
            ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
               .from(ACTOR)
               .join(FILM_ACTOR)
               .on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
               .where(ACTOR.FIRST_NAME.like("A%"))
               .fetch();

        // Try reordering the operations, or replacing ON by WHERE
    }

    @Test
    public void typeSafetyAliasing() {
        title("Table aliases also provide column type safety");

        Actor a = ACTOR.as("a");
        FilmActor fa = FILM_ACTOR.as("fa");

        var result =
            ctx.select(a.FIRST_NAME, a.LAST_NAME)
               .from(a)
               .join(fa)
               .on(a.ACTOR_ID.eq(fa.ACTOR_ID))
               .where(a.FIRST_NAME.like("A%"))
               .fetch();

        // Try reordering the operations, or replacing ON by WHERE
    }

    @Test
    public void implicitToOneJoins() {
        title("No need to spell out trivial to-one joins");
        ctx.select(
                CUSTOMER.FIRST_NAME,
                CUSTOMER.LAST_NAME,
                CUSTOMER.address().city().country().COUNTRY_)
            .from(CUSTOMER)
            .orderBy(1, 2)
            .limit(5)
            .fetch();
    }

    @Test
    public void implicitToManyJoins() {
        title("No need to spell out to-many joins either. Either use explicit to-many joins...");
        ctx.select(
                CUSTOMER.FIRST_NAME,
                CUSTOMER.LAST_NAME,
                countDistinct(CUSTOMER.rental().inventory().FILM_ID).as("distinct film rentals"))
            .from(CUSTOMER)

            // Add an explicit path join for the to-many path
            .leftJoin(CUSTOMER.rental().inventory())
            .groupBy(CUSTOMER.CUSTOMER_ID)
            .orderBy(inline(3).desc())
            .limit(5)
            .fetch();

        title("... or enable implicit to-many joins if you are OK with the 'interesting' semantics.");
        ctx.configuration()
            .deriveSettings(s -> s.withRenderImplicitJoinToManyType(RenderImplicitJoinType.LEFT_JOIN))
            .dsl()
            .select(
                CUSTOMER.FIRST_NAME,
                CUSTOMER.LAST_NAME,

                // Now, the to-many path can be implicitly joined. Beware that this may produce very unexpected
                // cartesian products (just like any JOIN, of course), which may be hard to debug because of the
                // implicitness!
                countDistinct(CUSTOMER.rental().inventory().FILM_ID).as("distinct film rentals"))
            .from(CUSTOMER)
            .groupBy(CUSTOMER.CUSTOMER_ID)
            .orderBy(inline(3).desc())
            .limit(5)
            .fetch();
    }

    // There's a bug here
    @Test(expected = Throwable.class)
    public void nestedRecords() {
        title("Need all columns of those active records?");

        var r =
        ctx.select(CUSTOMER, CUSTOMER.address().city().country())
           .from(CUSTOMER)
           .orderBy(1, 2)
           .limit(1)
           .fetchSingle();

        println("Customer %s %s from %s".formatted(r.value1().getFirstName(), r.value1().getLastName(), r.value2().getCountry()));

        // Though beware. While this is convenient, it's also likely inefficient as you're projecting too many columns
    }

    @Test
    public void nestedRowValuesWithAdHocConverters() {
        record Country(String name) {}
        record Customer(String firstName, String lastName, Country country) {}

        title("Nesting is particularly useful when using ad-hoc converters");
        List<Customer> r =
            ctx.select(
                    CUSTOMER.FIRST_NAME,
                    CUSTOMER.LAST_NAME,
                    CUSTOMER.address().city().country().COUNTRY_.convertFrom(Country::new))
               .from(CUSTOMER)
               .orderBy(1, 2)
               .limit(5)
               .fetch(mapping(Customer::new));

        r.forEach(Demo01Querying::println);
    }

    @Test
    public void deeplyNestedRowValuesWithAdHocConverters() {
        record Country(String name) {}
        record Customer(String firstName, String lastName, Country country) {}

        title("Nesting is particularly useful when using ad-hoc converters");
        List<Customer> r =
            ctx.select(DSL.row(
                   CUSTOMER.FIRST_NAME,
                   CUSTOMER.LAST_NAME,
                   row(CUSTOMER.address().city().country().COUNTRY_).mapping(Country::new)
               ).mapping(Customer::new))
               .from(CUSTOMER)
               .orderBy(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
               .limit(5)
               .fetch(Record1::value1);

        r.forEach(Demo01Querying::println);
    }

    @Test
    public void nestingToManyRelationships() {
        title("The envy of all other ORMs: MULTISET!");
        Result<Record3<String, Result<Record2<String, String>>, Result<Record1<String>>>> r =
        ctx.select(
                FILM.TITLE,
                multiset(
                    select(
                        FILM.actor().FIRST_NAME,
                        FILM.actor().LAST_NAME)

                    // Implicit path correlation is very powerful!
                    // https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/implicit-path-correlation/
                    .from(FILM.actor())
                ),
                multiset(select(FILM.category().NAME).from(FILM.category()))
           )
            .from(FILM)
            .orderBy(FILM.TITLE)
            .limit(5)
            .fetch();

        // By the way, any jOOQ Result and Record can be exported as CSV, XML, JSON, Text, etc.
        title("Formatted as JSON");
        println(r.formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.format(true).header(false)));

        title("Formatted as XML");
        println(r.formatXML(XMLFormat.DEFAULT_FOR_RESULTS.format(true).header(false)));
    }

    @Test
    public void nestingToManyRelationshipsWithAdHocConverters() {
        title("MULTISET combined with ad-hoc converters and nested rows! 🤩");

        record Name(String firstName, String lastName) {}
        record Actor(Name name) {}
        record Category(String name) {}
        record Film(String title, List<Actor> actors, List<Category> categories) {}

        var result =
            ctx.select(
                   FILM.TITLE,
                   multiset(
                       select(
                           row(
                               FILM_ACTOR.actor().FIRST_NAME,
                               FILM_ACTOR.actor().LAST_NAME
                           ).mapping(Name::new))
                       .from(FILM_ACTOR)
                       .where(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                   ).convertFrom(r -> r.map(mapping(Actor::new))),
                   multiset(
                       select(FILM_CATEGORY.category().NAME)
                           .from(FILM_CATEGORY)
                           .where(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                   ).convertFrom(r -> r.map(mapping(Category::new)))
               )
               .from(FILM)
               .orderBy(FILM.TITLE)
               .limit(5)
               .fetch(mapping(Film::new));

        for (Film film : result) {
            println("Film %s with categories %s and actors %s ".formatted(film.title, film.categories, film.actors));
        }

        // Try modifying the records and see what needs to be done to get the query to compile again
    }

    @Test
    public void nestingToManyRelationshipsAsMaps() {
        title("Arbitrary nested data structures are possible");

        record Film(String title, Map<LocalDate, BigDecimal> revenue) {}
        List<Film> result =
            ctx.select(
                FILM.TITLE,
                multiset(
                    select(PAYMENT.PAYMENT_DATE.cast(LOCALDATE), sum(PAYMENT.AMOUNT))
                    .from(PAYMENT)
                    .where(PAYMENT.rental().inventory().FILM_ID.eq(FILM.FILM_ID))
                    .groupBy(PAYMENT.PAYMENT_DATE.cast(LOCALDATE))
                    .orderBy(PAYMENT.PAYMENT_DATE.cast(LOCALDATE))
                ).convertFrom(r -> r.collect(intoMap()))
            )
            .from(FILM)
            .orderBy(FILM.TITLE)
            .fetch(mapping(Film::new));

        for (Film film : result) {
            println("");
            println("Film %s with revenue: ".formatted(film.title));

            film.revenue.forEach((d, r) -> println("  %s: %s".formatted(d, r)));
        }
    }
}
