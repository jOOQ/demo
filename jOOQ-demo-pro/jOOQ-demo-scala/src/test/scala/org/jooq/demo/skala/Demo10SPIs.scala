package org.jooq.demo.skala

import org.jooq.{DSLContext, ExecuteListener, RecordContext, RecordListener, VisitListener}
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.impl.DSL.using
import org.jooq.scalaextensions.Conversions._
import org.junit.{After, Test}

import java.util.concurrent.atomic.AtomicLong


class Demo10SPIs extends AbstractDemo {

  @Test
  def executeListener(): Unit = {
    title("The ExecuteListener SPI allows for intercepting the execution lifecycle")
    val c = ctx.configuration
      .derive(ExecuteListener
        .onRenderEnd(e => e.sql("/* some telemetry comment */ " + e.sql))
        .onExecuteStart(e => println("Executing: " + e.sql))
        .onRecordEnd(e => println("Fetched record: " + e.record.formatJSON)))
      .dsl

    c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID < 4L)
      .fetch

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-building/dsl-context/custom-execute-listeners/
    // https://www.jooq.org/doc/latest/manual/sql-execution/execute-listeners/
  }

  @Test
  def visitListener(): Unit = {
    title("The VisitListener SPI allows for intercepting the SQL rendering process")
    val c = ctx.configuration
      .derive(VisitListener.onVisitStart(vc => println("Visiting: " + using(ctx.family).render(vc.queryPart))))
      .dsl

    c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID < 4L)
      .fetch

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-building/queryparts/custom-sql-transformation/
  }

  @Test
  def recordListener(): Unit = {
    title("The RecordListener SPI allows for intercepting all UpdatableRecord operations")

    // Assume some sound algorithm to generate sequences for IDs in the client
    val sequence = new AtomicLong(201)
    val c = ctx.configuration.derive(RecordListener.onInsertStart(rc => {
      if (rc.record.get(ACTOR.ACTOR_ID) == null)
        rc.record.set(ACTOR.ACTOR_ID, java.lang.Long.valueOf(sequence.getAndIncrement))
    })).dsl

    // No IntegrityConstraintViolationException due to a missing ID!
    val a1 = c.newRecord(ACTOR)
    a1.setFirstName("John")
    a1.setLastName("Doe")
    a1.insert

    val a2 = c.newRecord(ACTOR)
    a2.setFirstName("Jane")
    a2.setLastName("Smith")
    a2.insert

    println("Next sequence value will be: " + sequence.get)

    // More information:
    // https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/crud-record-listener/
  }


  // There are many more SPIs, check out Configuration::derive methods!

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
