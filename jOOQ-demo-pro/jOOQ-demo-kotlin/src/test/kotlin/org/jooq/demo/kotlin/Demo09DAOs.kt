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
        // jOOQ can generate a Plain Old Java Object (POJO) for each table, in multiple styles, including JavaBeans
        // style POJOs, records, and more. These POJOs can be used as Data Transfer Objects (DTO) between modules
        // without exposing any jOOQ API, such as jOOQ's TableRecord, for example. Unlike jOOQ's Records, however,
        // POJOs do not implement any dirty tracking, optimistic locking, etc., so their usefulness is limited.

        title("jOOQ's code generator produces a data class for every table")
        val actors = ctx
            .selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))
            .fetchInto(Actor::class.java)

        actors.forEach { println(it) }

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-execution/fetching/pojos/
        // https://www.jooq.org/doc/latest/manual/sql-execution/fetching/pojos-with-recordmapper-provider/
    }

    @Test
    fun daos() {
        // A DAO is a generated class that maps between a generated Table and the generated POJO, and offers a few
        // convenience methods for common CRUD operations, based directly on those POJOs. This can be useful for
        // faster implementation of trivial CRUD. They're not meant as a replacement for actual querying with jOOQ!
        // See also https://blog.jooq.org/to-dao-or-not-to-dao/

        title("Daos further simplify CRUD when working with jOOQ")
        val dao = ActorDao(ctx.configuration())
        dao.insert(
            Actor(201L, "John", "Doe", null),
            Actor(202L, "Jane", "Smith", null)
        )

        dao.fetchByActorId(201L, 202L).forEach { println(it) }

        // More information here:
        // https://www.jooq.org/doc/latest/manual/sql-execution/daos/
        // https://blog.jooq.org/to-dao-or-not-to-dao/
    }

    @After
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }
}