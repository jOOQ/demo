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
    // As seen previous in the querying demo, UpdatableRecords can be fetched using the special selectFrom() method,
    // which projects an entire table. It's also possible to nest a table to get multiple nested UpdatableRecords
    // in a single query.

    // Such records can be modified for an update, or new records can be created for an insert.

    // The goal of these UpdatableRecords isn't to implement a full fledged ORM with object graph persistence
    // capabilities. They only offer simple 1:1 table mappings with a few useful CRUD features.


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

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/
  }

  @Test
  def dml(): Unit = {
    // In addition to simple CRUD, any bulk DML statement can be executed with jOOQ as well

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

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/update-statement/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/delete-statement/
    // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/merge-statement/
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
