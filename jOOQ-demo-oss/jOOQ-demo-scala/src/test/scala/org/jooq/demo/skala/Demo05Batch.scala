package org.jooq.demo.skala

import org.jooq.Configuration
import org.jooq.demo.AbstractDemo
import org.jooq.demo.skala.db.tables.records.ActorRecord
import org.jooq.demo.skala.db.Tables
import org.junit.After
import org.junit.Test

import java.sql.SQLException
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables._
import org.jooq.scalaextensions.Conversions._

class Demo05Batch extends AbstractDemo {

  @Test
  def batchUpdatableRecords(): Unit = {
    title("A set of updatable records can be conveniently batch stored, inserted, updated")
    val a1 = ctx.newRecord(ACTOR)
    val a2 = ctx.newRecord(ACTOR)
    a1.setActorId(201L)
    a1.setFirstName("John")
    a1.setLastName("Doe")
    a2.setActorId(202L)
    a2.setFirstName("Jane")
    a2.setLastName("Smith")
    ctx.batchStore(a1, a2).execute
  }

  @Test
  def batchedConnection(): Unit = {
    title("Just collect all JDBC statements, and delay them until appropriate to run them in a batch")
    ctx.batched(c => {

      // Don't use ctx here, but the nested configuration
      title("This doesn't actually store the record yet, it just caches the query for later storage")
      c.dsl.insertInto(ACTOR)
        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .values(201L, "Jon", "Doe")
        .execute

      title("Again, nothing is stored just yet")
      c.dsl.insertInto(ACTOR)
        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .values(202L, "Jane", "Smith")
        .execute

      title("When a different query string is encountered, then we 'flush' the batch for the query to produce correct results")
      c.dsl.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
        .from(ACTOR)
        .where(ACTOR.ACTOR_ID > 200L)
        .fetch
    })
  }

  @Test def batchManually(): Unit = {
    ctx.batch(ctx.insertInto(ACTOR).columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME).values // Pass a few dummy values here
    (null.asInstanceOf[Long], null, null)).bind(201L, "Jon", "Doe").bind(202L, "Jane", "Smith").execute
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
