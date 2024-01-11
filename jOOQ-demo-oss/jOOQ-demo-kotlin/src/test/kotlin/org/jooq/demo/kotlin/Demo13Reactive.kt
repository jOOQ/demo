package org.jooq.demo.kotlin

import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.runBlocking
import org.jooq.Configuration
import org.jooq.Records.mapping
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.records.ActorRecord
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.jooq.kotlin.coroutines.transactionCoroutine
import org.junit.After
import org.junit.Test
import reactor.core.publisher.Flux


class Demo13Reactive : AbstractDemo() {

    @Test
    fun reactiveQuerying() {
        // If you configure jOOQ with an R2DBC connection as in this demo, then you can use jOOQ's reactive APIs via
        // its various Publisher implementations. For example, a ResultQuery<R> is a Publisher<R>, and can be used with
        // any RS compliant streaming library, such as Reactor, below:

        data class Actor(val firstName: String?, val lastName: String?)

        Flux.from(ctx
                .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .orderBy(ACTOR.ACTOR_ID)
                .limit(5))
            .map(mapping(::Actor))
            .toIterable()
            .forEach { println(it) }

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-execution/fetching/reactive-fetching/
    }

    @Test
    fun reactiveTransactions() {
        Flux.from(ctx

            // Just like synchronous, JDBC based transactions, reactive transactions commit by default, and rollback
            // on error. Nested transactions using SAVEPOINT are supported by default. See this blog for details:
            // https://blog.jooq.org/nested-transactions-in-jooq/
            .transactionPublisher { c -> Flux
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
                    .values(201L, "A", "A"))
                })

            // Outside of the scope, we have committed or rollbacked the transaction, so on error, we can see the
            // Rollback reason:
            .collectList()
            .doOnError(Throwable::printStackTrace)
            .onErrorReturn(listOf())

            // This record is visible only if the transaction has been committed:
            .thenMany(ctx
                .select(ACTOR.ACTOR_ID)
                .from(ACTOR)
                .where(ACTOR.ACTOR_ID.eq(201L)))
            .toIterable()
            .forEach { println(it) }

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-execution/transaction-management/
    }

    @Test
    fun coroutines() {
        val actor: ActorRecord? = runBlocking {
            findActor(1)
        }

        println(actor)
    }

    suspend fun findActor(id: Long): ActorRecord? {
        return ctx
            .selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(id))

            // Turn any reactive streams Publisher<T> into a suspension result using the
            // kotlinx-coroutines-reactive extensions
            .awaitFirstOrNull()
    }

    @Test
    fun coroutinesWithTransactions() {
        val actor: ActorRecord = runBlocking {
            insertActorTransaction()
        }

        println(actor)
    }

    suspend fun insertActorTransaction(): ActorRecord {
        return ctx.transactionCoroutine(::insertActor)
    }

    suspend fun insertActor(c: Configuration): ActorRecord = c.dsl()
        .insertInto(ACTOR)
        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .values(201L, "A", "A")
        .returning()
        .awaitFirst()

    @After
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }
}
