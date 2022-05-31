package org.jooq.demo.java;

import org.jooq.demo.AbstractDemo;
import org.junit.After;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.Records.mapping;
import static org.jooq.demo.java.db.Tables.ACTOR;

public class Demo13Reactive extends AbstractDemo {

    @Test
    public void reactiveQuerying() {
        record Actor(String firstName, String lastName) {}

        Flux.from(ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .orderBy(ACTOR.ACTOR_ID)
                .limit(5))
            .map(mapping(Actor::new))
            .collectList()
            .block()
            .forEach(System.out::println);
    }

    @Test
    public void reactiveTransactions() {
        Flux.from(ctx

            // Just like synchronous, JDBC based transactions, reactive transactions commit by default, and rollback
            // on error. Nested transactions using SAVEPOINT are supported by default. See this blog for details:
            // https://blog.jooq.org/nested-transactions-in-jooq/
            .transactionPublisher(c -> Flux
                .from(c.dsl()
                       .insertInto(ACTOR)
                       .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                       .values(201L, "A", "A"))

                // Within the transactional scope, the above record is visible, and we can log it
                .thenMany(c.dsl()
                           .selectFrom(ACTOR)
                           .where(ACTOR.ACTOR_ID.eq(201L)))
                .log()

                // This should produces a constraint violation exception, rolling back the transaction
                .thenMany(c.dsl()
                           .insertInto(ACTOR)
                           .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                           .values(201L, "A", "A"))))

            // Outside of the scope, we have committed or rollbacked the transaction, so on error, we can see the
            // Rollback reason:
            .collectList()
            .doOnError(Throwable::printStackTrace)
            .onErrorReturn(List.of())

            // This record is visible only if the transaction has been committed:
            .thenMany(ctx.select(ACTOR.ACTOR_ID).from(ACTOR).where(ACTOR.ACTOR_ID.eq(201L)))
            .collectList()
            .block()
            .forEach(System.out::println);
    }

    @After
    public void teardown() throws SQLException {
        cleanup(ACTOR, ACTOR.ACTOR_ID);
        super.teardown();
    }
}
