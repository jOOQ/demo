package org.jooq.demo.java;

import org.jooq.Field;
import org.jooq.WindowDefinition;
import org.jooq.demo.AbstractDemo;
import org.jooq.impl.DSL;
import org.junit.Test;

import static org.jooq.demo.java.db.Tables.*;
import static org.jooq.impl.DSL.*;

public class Demo03Functions extends AbstractDemo {

    @Test
    public void testSimpleFunctions() {
        title("A huge amount of SQL functions and expressions are available in jOOQ");
        ctx.select(ACTOR.FIRST_NAME.concat(ACTOR.LAST_NAME), DSL.length(ACTOR.FIRST_NAME).plus(DSL.length(ACTOR.LAST_NAME)))
            .from(ACTOR)
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch();
    }

    @Test
    public void testPlainSQLTemplates() {
        title("Whenever jOOQ does not support a vendor specific function, you can add support yourself");
        ctx.select(ACTOR.FIRST_NAME, difference(ACTOR.FIRST_NAME, "JOHN"))
            .from(ACTOR)
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch();
    }

    private Field<String> difference(Field<String> f1, String f2) {
        return difference(f1, val(f2));
    }

    private Field<String> difference(Field<String> f1, Field<String> f2) {
        return field("difference({0}, {1})", f1.getDataType(), f1, f2);
    }

    @Test
    public void aggregateFunctions() {
        title("A lot of aggregate functions are available too");
        ctx.select(
               ACTOR.FIRST_NAME,
               ACTOR.LAST_NAME,
               count().as("number of films"),
               count().filterWhere(FILM.TITLE.like("%A%")).as("number of films containing 'A' in the title"),
               max(FILM.LENGTH).as("longest film"),
               min(FILM.LENGTH).as("shortest film"))
           .from(ACTOR)
           .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
           .join(FILM).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
           .groupBy(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
           .orderBy(ACTOR.FIRST_NAME)
           .limit(5)
           .fetch();
    }

    @Test
    public void windowFunctions() {
        title("A lot of window functions are available too");
        WindowDefinition w = name("w").as(partitionBy(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME));

        ctx.select(
               rowNumber().over(w.orderBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)),
               ACTOR.FIRST_NAME,
               ACTOR.LAST_NAME,
               FILM.TITLE,
               count().over(w).as("number of films"),
               count().filterWhere(FILM.TITLE.like("%A%")).over(w).as("number of films containing 'A' in the title"),
               max(FILM.LENGTH).over(w).as("longest film"),
               min(FILM.LENGTH).over(w).as("shortest film"))
           .from(ACTOR)
           .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
           .join(FILM).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
           .window(w)
           .orderBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
           .limit(5)
           .fetch();
    }
}
