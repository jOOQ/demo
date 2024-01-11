package org.jooq.demo.skala

import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables._
import org.junit.{After, Test}
import reactor.core.publisher.Flux


class Demo13Reactive extends AbstractDemo {

  @Test
  def reactiveQuerying(): Unit = {
    // If you configure jOOQ with an R2DBC connection as in this demo, then you can use jOOQ's reactive APIs via
    // its various Publisher implementations. For example, a ResultQuery<R> is a Publisher<R>, and can be used with
    // any RS compliant streaming library, such as Reactor, below:

    case class Actor(firstName: String, lastName: String)

    Flux
      .from(ctx
        .select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .from(ACTOR)
        .orderBy(ACTOR.ACTOR_ID)
        .limit(5))
      .map(r => Actor(r.value1(), r.value2()))
      .collectList
      .block
      .forEach(println(_))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/fetching/reactive-fetching/
  }

  @Test
  def reactiveTransactions(): Unit = {
    Flux.from(ctx

      // Just like synchronous, JDBC based transactions, reactive transactions commit by default, and rollback
      // on error. Nested transactions using SAVEPOINT are supported by default. See this blog for details:
      // https://blog.jooq.org/nested-transactions-in-jooq/
      .transactionPublisher(c => Flux
        .from(c.dsl
          .insertInto(ACTOR)
          .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
          .values(201L, "A", "A"))

        // Within the transactional scope, the above record is visible, and we can log it
        .thenMany(c.dsl
          .selectFrom(ACTOR)
          .where(ACTOR.ACTOR_ID.eq(201L)))
        .log

        // This should produces a constraint violation exception, rolling back the transaction
        .thenMany(c.dsl
          .insertInto(ACTOR)
          .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
          .values(201L, "A", "A"))))

      // Outside of the scope, we have committed or rollbacked the transaction, so on error, we can see the
      // Rollback reason:
      .collectList
      .doOnError { _.printStackTrace() }
      .onErrorReturn(java.util.List.of())

      // This record is visible only if the transaction has been committed:
      .thenMany(ctx
        .select(ACTOR.ACTOR_ID)
        .from(ACTOR)
        .where(ACTOR.ACTOR_ID.eq(201L)))
      .collectList
      .block
      .forEach(println(_))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/transaction-management/
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}