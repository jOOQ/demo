package org.jooq.demo.skala

import org.jooq.Configuration
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.scalaextensions.Conversions._
import org.junit.{After, Test}

import java.sql.Connection


class Demo06Transactions extends AbstractDemo {

  @Test
  def transactionCommit(): Unit = {
    title("jOOQ transactions are explicit using lambdas")
    ctx.transaction(c => {
      // Don't use ctx here, but the nested configuration
      c.dsl.insertInto(ACTOR)
        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .values(201L, "Jon", "Doe")
        .execute
      // If this completes normally, we commit
    })

    title("The committed actor is available outside of the transaction")
    ctx.fetch(ACTOR, ACTOR.ACTOR_ID > 200L)
  }

  @Test def transactionRollback(): Unit = {
    title("Rollbacks are implicit when a transactional scope is exited with an exception")
    try ctx.transaction(c => {
      c.dsl.insertInto(ACTOR)
        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .values(201L, "Jon", "Doe")
        .execute

      title("This transaction has successfully inserted a record")
      c.dsl.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(201L))

      // But now we rollback
      throw new RuntimeException("This should roll back")
    })
    catch {
      case _: RuntimeException =>
        title("The rollbacked actor is no longer available outside of the transaction")
        ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))
    }
  }

  @Test
  def nestedTransactions(): Unit = {
    title("Transactions can be nested, just like Spring's Propagation.NESTED, which is the default in jOOQ")
    try ctx.transaction(c1 => {
      c1.dsl.insertInto(ACTOR)
        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .values(201L, "Jon", "Doe")
        .execute

      try {
        title("A nested transaction implicitly creates a savepoint")
        c1.dsl.transaction(c2 => {
          c2.dsl.insertInto(ACTOR)
            .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .values(202L, "Jane", "Smith")
            .execute

          title("The nested transaction now has 2 new actors")
          ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))

          // But now we rollback the nested transaction
          throw new RuntimeException("This should roll back to the savepoint")
        })
      } catch {
        case e: RuntimeException =>
          title("The rollbacked actor is no longer available outside of the transaction")
          ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))
        // Now we may decide to re-throw to roll back everything, or continue at the savepoint
        // throw e;
      }
    })
    catch {
      case ignore: RuntimeException =>
    } finally {
      title("At the end of the transaction, we may have a result depending on whether we committed the savepoint or not")
      ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))
    }
  }

  @Test def programmaticTransactions(): Unit = {
    title("You can always roll your own. jOOQ doesn't hide JDBC from you")
    ctx.connection(connection => {
      connection.setAutoCommit(false)

      try {
        title("Derive a new configuration from your existing one, with an explicit JDBC connection")
        val c = ctx.configuration.derive(connection).dsl
        c.insertInto(ACTOR)
          .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
          .values(201L, "Jon", "Doe")
          .execute

        title("Explicit commit")
        connection.commit()
        c.insertInto(ACTOR)
          .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
          .values(202L, "Jane", "Smith")
          .execute

        title("Within the transaction, we should have both records")
        ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))

        title("Explicit rollback")
        connection.rollback()
      } finally connection.setAutoCommit(true)
    })

    title("At the end of the transaction, we may have a result depending on whether we committed or not")
    ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
