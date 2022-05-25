package org.jooq.demo.kotlin

import org.jooq.Configuration
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.junit.After
import org.junit.Test

class Demo05Batch : AbstractDemo() {

    @Test
    fun batchUpdatableRecords() {
        title("A set of updatable records can be conveniently batch stored, inserted, updated")
        val a1 = ctx.newRecord(ACTOR)
        val a2 = ctx.newRecord(ACTOR)

        a1.actorId = 201L
        a1.firstName = "John"
        a1.lastName = "Doe"

        a2.actorId = 202L
        a2.firstName = "Jane"
        a2.lastName = "Smith"

        ctx.batchStore(a1, a2).execute()
    }

    @Test
    fun batchedConnection() {
        title("Just collect all JDBC statements, and delay them until appropriate to run them in a batch")
        ctx.batched { c: Configuration ->

            // Don't use ctx here, but the nested configuration
            title("This doesn't actually store the record yet, it just caches the query for later storage")
            c.dsl().insertInto(ACTOR)
                .columns(
                    ACTOR.ACTOR_ID,
                    ACTOR.FIRST_NAME,
                    ACTOR.LAST_NAME
                )
                .values(201L, "Jon", "Doe")
                .execute()

            title("Again, nothing is stored just yet")
            c.dsl().insertInto(ACTOR)
                .columns(
                    ACTOR.ACTOR_ID,
                    ACTOR.FIRST_NAME,
                    ACTOR.LAST_NAME
                )
                .values(202L, "Jane", "Smith")
                .execute()

            title("When a different query string is encountered, then we 'flush' the batch for the query to produce correct results")
            c.dsl().select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.gt(200L))
                .fetch()
        }
    }

    @Test
    fun batchManually() {
        ctx.batch(
                ctx.insertInto(ACTOR)
                    .columns(
                        ACTOR.ACTOR_ID,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME
                    )
                    // Pass a few dummy values here
                    .values(null as Long?, null, null)
            )
            .bind(201L, "Jon", "Doe")
            .bind(202L, "Jane", "Smith")
            .execute()
    }

    @After
    override fun teardown() {
        cleanup()
        super.teardown()
    }
}