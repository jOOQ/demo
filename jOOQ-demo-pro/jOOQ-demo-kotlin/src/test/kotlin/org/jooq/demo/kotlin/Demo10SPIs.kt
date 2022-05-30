package org.jooq.demo.kotlin

import org.jooq.ExecuteListener
import org.jooq.VisitListener
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.jooq.impl.DSL
import org.junit.Test

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
    }

    // There are many more SPIs, check out Configuration::derive methods!
}