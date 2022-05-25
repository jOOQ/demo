package org.jooq.demo.java;

import org.jooq.DSLContext;
import org.jooq.ExecuteListener;
import org.jooq.VisitListener;
import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.tables.daos.ActorDao;
import org.jooq.demo.java.db.tables.pojos.Actor;
import org.jooq.impl.DSL;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.demo.java.db.Tables.ACTOR;
import static org.jooq.impl.DSL.using;

public class Demo10SPIs extends AbstractDemo {

    @Test
    public void executeListener() {
        title("The ExecuteListener SPI allows for intercepting the execution lifecycle");

        DSLContext c = ctx.configuration().derive(
            ExecuteListener
                .onRenderEnd(e -> e.sql("/* some telemetry comment */ " + e.sql()))
                .onExecuteStart(e -> println("Executing: " + e.sql()))
                .onRecordEnd(e -> println("Fetched record: " + e.record().formatJSON()))
        ).dsl();

        c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))
            .fetch();
    }

    @Test
    public void visitListener() {
        title("The VisitListener SPI allows for intercepting the SQL rendering process");

        DSLContext c = ctx.configuration().derive(
            VisitListener.onVisitStart(vc -> println("Visiting: " + using(ctx.family()).render(vc.queryPart())))
        ).dsl();

        c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
         .from(ACTOR)
         .where(ACTOR.ACTOR_ID.lt(4L))
         .fetch();
    }

    // There are many more SPIs, check out Configuration::derive methods!
}
