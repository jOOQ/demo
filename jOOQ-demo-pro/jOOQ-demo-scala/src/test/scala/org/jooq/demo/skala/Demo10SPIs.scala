package org.jooq.demo.skala

import org.jooq.{ExecuteListener, VisitListener}
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.impl.DSL.using
import org.jooq.scalaextensions.Conversions._
import org.junit.Test


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
  }

  @Test def visitListener(): Unit = {
    title("The VisitListener SPI allows for intercepting the SQL rendering process")
    val c = ctx.configuration
      .derive(VisitListener.onVisitStart(vc => println("Visiting: " + using(ctx.family).render(vc.queryPart))))
      .dsl

    c.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .from(ACTOR)
      .where(ACTOR.ACTOR_ID < 4L)
      .fetch
  }
  // There are many more SPIs, check out Configuration::derive methods!
}
