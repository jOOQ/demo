package org.jooq.demo;

import org.jooq.*;
import org.jooq.demo.db.tables.Actor;
import org.jooq.demo.db.tables.FilmActor;
import org.jooq.demo.db.tables.records.ActorRecord;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.Records.mapping;
import static org.jooq.demo.db.Tables.*;
import static org.jooq.impl.DSL.*;

public class Demo02DML extends AbstractDemo {

    @Test
    public void updatableRecords() {
        title("By default, every table has a corresponding TableRecord or UpdatableRecord, which work like active records");
        ActorRecord a1 = ctx.newRecord(ACTOR);
        a1.setActorId(201L);
        a1.setFirstName("Jane");
        a1.setLastName("Doe");
        a1.insert();

        title("You can fetch them in a type safe way by using the special selectFrom() method, or appropriate mappers");
        ActorRecord a2 =
            ctx.selectFrom(ACTOR)
               .where(ACTOR.ACTOR_ID.eq(201L))
               .fetchSingle();

        title("UpdatableRecord.store() will decide itself, whether it should insert() or update()");
        a2.setLastName("Smith");
        a2.store();
    }

    @Test
    public void dml() {
        title("All sorts of classic bulk DML statements are available");

        ctx.insertInto(ACTOR)
           .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
           .values(201L, "Jon", "Doe")
           .values(202L, "Jane", "Smith")
           .execute();

        ctx.update(ACTOR)
           .set(ACTOR.LAST_NAME, "X")
           .where(ACTOR.ACTOR_ID.gt(200L))
           .execute();
    }

    @After
    public void teardown() throws SQLException {
        cleanup();
        super.teardown();
    }
}
