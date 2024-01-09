package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.junit.After
import org.junit.Test
import java.sql.SQLException

class Demo04DML : AbstractDemo() {

    @Test
    fun updatableRecords() {
        // As seen previous in the querying demo, UpdatableRecords can be fetched using the special selectFrom() method,
        // which projects an entire table. It's also possible to nest a table to get multiple nested UpdatableRecords
        // in a single query.

        // Such records can be modified for an update, or new records can be created for an insert.

        // The goal of these UpdatableRecords isn't to implement a full fledged ORM with object graph persistence
        // capabilities. They only offer simple 1:1 table mappings with a few useful CRUD features.

        title("By default, every table has a corresponding TableRecord or UpdatableRecord, which work like active records")
        with(ctx.newRecord(ACTOR)) {
            actorId = 201L
            firstName = "Jane"
            lastName = "Doe"

            insert()
        }

        title("You can fetch them in a type safe way by using the special selectFrom() method, or appropriate mappers")
        with(ctx
            .selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.eq(201L))
            .fetchSingle()) {

            title("UpdatableRecord.store() will decide itself, whether it should insert() or update()")
            lastName = "Smith"
            store()
        }

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-execution/crud-with-updatablerecords/
    }

    @Test
    fun dml() {
        // In addition to simple CRUD, any bulk DML statement can be executed with jOOQ as well

        title("All sorts of classic bulk DML statements are available")
        ctx.insertInto(ACTOR)
            .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .values(201L, "Jon", "Doe")
            .values(202L, "Jane", "Smith")
            .execute()
        ctx.update(ACTOR)
            .set(ACTOR.LAST_NAME, "X")
            .where(ACTOR.ACTOR_ID.gt(200L))
            .execute()

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/update-statement/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/delete-statement/
        // - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/merge-statement/
    }

    @After
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }
}