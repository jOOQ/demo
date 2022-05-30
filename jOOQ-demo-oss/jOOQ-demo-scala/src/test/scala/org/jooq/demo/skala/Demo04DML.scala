package org.jooq.demo.skala

import org.jooq.demo.AbstractDemo
import org.jooq.demo.skala.db.tables.records.ActorRecord
import org.junit.After
import org.junit.Test

import java.sql.SQLException
import org.jooq.Records.mapping
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables._
import org.jooq.scalaextensions.Conversions._


class Demo04DML extends AbstractDemo {

  @Test
  def updatableRecords(): Unit = {
    title("By default, every table has a corresponding TableRecord or UpdatableRecord, which work like active records")
    val a1 = ctx.newRecord(ACTOR)
    a1.setActorId(201L)
    a1.setFirstName("Jane")
    a1.setLastName("Doe")
    a1.insert

    title("You can fetch them in a type safe way by using the special selectFrom() method, or appropriate mappers")
    val a2 = ctx.selectFrom(ACTOR).where(ACTOR.ACTOR_ID.eq(201L)).fetchSingle

    title("UpdatableRecord.store() will decide itself, whether it should insert() or update()")
    a2.setLastName("Smith")
    a2.store
  }

  @Test
  def dml(): Unit = {
    title("All sorts of classic bulk DML statements are available")
    ctx.insertInto(ACTOR)
      .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
      .values(201L, "Jon", "Doe")
      .values(202L, "Jane", "Smith")
      .execute

    ctx.update(ACTOR)
      .set(ACTOR.LAST_NAME, "X")
      .where(ACTOR.ACTOR_ID.gt(200L))
      .execute
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
