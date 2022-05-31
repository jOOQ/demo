package org.jooq.demo.skala

import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.demo.skala.db.tables.daos.ActorDao
import org.jooq.demo.skala.db.tables.pojos.Actor
import org.junit.{After, Test}


class Demo09DAOs extends AbstractDemo {

  @Test
  def pojos(): Unit = {
    title("jOOQ's code generator produces a POJO for every table")
    val actors = ctx
      .selectFrom(ACTOR)
      .where(ACTOR.ACTOR_ID.lt(4L))
      .fetchInto(classOf[Actor])
    actors.forEach(println(_))
  }

  @Test
  def daos(): Unit = {
    title("Daos further simplify CRUD when working with jOOQ")
    val dao = new ActorDao(ctx.configuration)

    dao.insert(
      Actor(201L, "John", "Doe", null),
      Actor(202L, "Jane", "Smith", null))
    dao.fetchByActorId(201L, 202L).forEach(println(_))
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
