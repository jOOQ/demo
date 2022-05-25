package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.junit.After
import org.junit.Test
import java.sql.SQLException

class Demo04DML : AbstractDemo() {
    @Test
    fun updatableRecords() {
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
    }

    @Test
    fun dml() {
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
    }

    @After
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }
}