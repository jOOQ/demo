package org.jooq.demo.java;

import org.jetbrains.annotations.NotNull;
import org.jooq.*;
import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.Public;
import org.jooq.impl.DSL;
import org.jooq.impl.QOM;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.jooq.demo.java.db.Tables.ACTOR;
import static org.jooq.impl.DSL.*;

public class Demo11QueryObjectModel extends AbstractDemo {

    // As of jOOQ 3.17, these features are *EXPERIMENTAL*!

    @Test
    public void qom() {
        title("The query object model (QOM) API allows for accessing most query parts");

        var select = ctx
            .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L));

        println("SELECT: " + select.$select());
        println("FROM  : " + select.$from());
        println("WHERE : " + select.$where());

        title("You can also alter a property of a query, to create a new query (QOM operations are immutable):");
        println(
            select.$select(Stream.concat(Stream.of(ACTOR.ACTOR_ID), select.$select().stream()).toList())
                .$orderBy(List.of(ACTOR.ACTOR_ID.asc()))
        );

        title("The old query is untouched:");
        println(select);
    }

    @Test
    public void traversal() {
        title("The query object model (QOM) can be traversed easily");

        // QOM traversal is a commercial only feature.

























    }

    @Test
    public void replacement() {
        title("The query object model (QOM) can be transformed easily");

        // QOM replacement is a commercial only feature.


























    }























}
