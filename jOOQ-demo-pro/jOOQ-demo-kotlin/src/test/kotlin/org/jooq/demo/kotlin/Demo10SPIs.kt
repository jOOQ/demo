package org.jooq.demo.kotlin

import org.jooq.*
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.records.ActorRecord
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.jooq.impl.DSL
import org.junit.After
import org.junit.Test
import java.util.concurrent.atomic.AtomicLong

class Demo10SPIs : AbstractDemo() {

    @Test
    fun executeListener() {
        title("The ExecuteListener SPI allows for intercepting the execution lifecycle")
        val c = ctx.configuration().derive(
            ExecuteListener
                .onRenderEnd { e -> e.sql("/* some telemetry comment */ " + e.sql()) }
                .onExecuteStart { e -> println("Executing: " + e.sql()) }
                .onRecordEnd { e -> println("Fetched record: " + e.record()!!.formatJSON()) }
        ).dsl()

        c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))
            .fetch()

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-building/dsl-context/custom-execute-listeners/
        // https://www.jooq.org/doc/latest/manual/sql-execution/execute-listeners/
    }

    @Test
    fun visitListener() {
        title("The VisitListener SPI allows for intercepting the SQL rendering process")
        val c = ctx.configuration().derive(
            VisitListener.onVisitStart { vc -> println("Visiting: " + DSL.using(ctx.family()).render(vc.queryPart())) }
        ).dsl()

        c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))
            .fetch()

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-building/queryparts/custom-sql-transformation/
    }


    @Test
    fun recordListener() {
        title("The RecordListener SPI allows for intercepting all UpdatableRecord operations")

        // Assume some sound algorithm to generate sequences for IDs in the client
        val sequence = AtomicLong(201)
        val c = ctx.configuration().derive(
            RecordListener.onInsertStart { rc ->
                if (rc.record().get(ACTOR.ACTOR_ID) == null)
                    rc.record().set(ACTOR.ACTOR_ID, sequence.getAndIncrement())
            }
        ).dsl()

        // No IntegrityConstraintViolationException due to a missing ID!
        val a1 = c.newRecord(ACTOR)
        a1.firstName = "John"
        a1.lastName = "Doe"
        a1.insert()

        val a2 = c.newRecord(ACTOR)
        a2.firstName = "Jane"
        a2.lastName = "Smith"
        a2.insert()

        println("Next sequence value will be: " + sequence.get())

        // More information:
        // https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/crud-record-listener/
    }

    // There are many more SPIs, check out Configuration::derive methods!

    @After
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }
}