package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.daos.ActorDao
import org.jooq.demo.kotlin.db.tables.pojos.Actor
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.junit.After
import org.junit.Test

class Demo09DAOs : AbstractDemo() {

    @Test
    fun pojos() {
        title("jOOQ's code generator produces a data class for every table")
        val actors = ctx
            .selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))
            .fetchInto(Actor::class.java)

        actors.forEach { println(it) }
    }

    @Test
    fun daos() {
        title("Daos further simplify CRUD when working with jOOQ")
        val dao = ActorDao(ctx.configuration())
        dao.insert(
            Actor(201L, "John", "Doe", null),
            Actor(202L, "Jane", "Smith", null)
        )

        dao.fetchByActorId(201L, 202L).forEach { println(it) }
    }

    @After
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }
}