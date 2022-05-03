package org.jooq.demo;

import org.jooq.demo.db.tables.records.ActorRecord;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;

import static org.jooq.demo.db.Tables.ACTOR;

public class Demo05Batch extends AbstractDemo {

    @Test
    public void batchUpdatableRecords() {
        title("A set of updatable records can be conveniently batch stored, inserted, updated");
        ActorRecord a1 = ctx.newRecord(ACTOR);
        ActorRecord a2 = ctx.newRecord(ACTOR);

        a1.setActorId(201L);
        a1.setFirstName("John");
        a1.setLastName("Doe");

        a2.setActorId(202L);
        a2.setFirstName("Jane");
        a2.setLastName("Smith");

        ctx.batchStore(a1, a2).execute();
    }

    @Test
    public void batchedConnection() {
        title("Just collect all JDBC statements, and delay them until appropriate to run them in a batch");

        ctx.batched(c -> {
            // Don't use ctx here, but the nested configuration

            title("This doesn't actually store the record yet, it just caches the query for later storage");
            c.dsl().insertInto(ACTOR)
                .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .values(201L, "Jon", "Doe")
                .execute();

            title("Again, nothing is stored just yet");
            c.dsl().insertInto(ACTOR)
                .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .values(202L, "Jane", "Smith")
                .execute();

            title("When a different query string is encountered, then we 'flush' the batch for the query to produce correct results");
            c.dsl().select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.gt(200L))
                .fetch();
        });
    }

    @Test
    public void batchManually() {
        ctx.batch(
            ctx.insertInto(ACTOR)
                .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)

                // Pass a few dummy values here
                .values((Long) null, null, null)
            )
            .bind(201L, "Jon", "Doe")
            .bind(202L, "Jane", "Smith")
            .execute();
    }

    @After
    public void teardown() throws SQLException {
        cleanup();
        super.teardown();
    }
}
