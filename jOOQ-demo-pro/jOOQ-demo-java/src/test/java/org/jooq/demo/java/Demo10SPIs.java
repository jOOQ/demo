package org.jooq.demo.java;

import org.jooq.DSLContext;
import org.jooq.ExecuteListener;
import org.jooq.RecordListener;
import org.jooq.VisitListener;
import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.tables.daos.ActorDao;
import org.jooq.demo.java.db.tables.pojos.Actor;
import org.jooq.demo.java.db.tables.records.ActorRecord;
import org.jooq.impl.DSL;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-building/dsl-context/custom-execute-listeners/
        // https://www.jooq.org/doc/latest/manual/sql-execution/execute-listeners/
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

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-building/queryparts/custom-sql-transformation/
    }

    @Test
    public void recordListener() {
        title("The RecordListener SPI allows for intercepting all UpdatableRecord operations");

        // Assume some sound algorithm to generate sequences for IDs in the client
        AtomicLong sequence = new AtomicLong(201);

        DSLContext c = ctx.configuration().derive(
            RecordListener.onInsertStart(rc -> {
                if (rc.record().get(ACTOR.ACTOR_ID) == null)
                    rc.record().set(ACTOR.ACTOR_ID, sequence.getAndIncrement());
            })
        ).dsl();

        // No IntegrityConstraintViolationException due to a missing ID!
        ActorRecord a1 = c.newRecord(ACTOR);
        a1.setFirstName("John");
        a1.setLastName("Doe");
        a1.insert();

        ActorRecord a2 = c.newRecord(ACTOR);
        a2.setFirstName("Jane");
        a2.setLastName("Smith");
        a2.insert();

        println("Next sequence value will be: " + sequence.get());

        // More information:
        // https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/crud-record-listener/
    }

    // There are many more SPIs, check out Configuration::derive methods!

    @After
    public void teardown() throws SQLException {
        cleanup(ACTOR, ACTOR.ACTOR_ID);
        super.teardown();
    }
}
