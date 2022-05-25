package org.jooq.demo.java;

import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.tables.daos.ActorDao;
import org.jooq.demo.java.db.tables.pojos.Actor;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.demo.java.db.Tables.ACTOR;

public class Demo09DAOs extends AbstractDemo {

    @Test
    public void pojos() {
        title("jOOQ's code generator produces a POJO for every table");
        List<Actor> actors =
        ctx.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.lt(4L))
            .fetchInto(Actor.class);

        actors.forEach(System.out::println);
    }

    @Test
    public void daos() {
        title("Daos further simplify CRUD when working with jOOQ");
        ActorDao dao = new ActorDao(ctx.configuration());
        dao.insert(
            new Actor(201L, "John", "Doe", null),
            new Actor(202L, "Jane", "Smith", null)
        );

        dao.fetchByActorId(201L, 202L).forEach(System.out::println);
    }

    @After
    public void teardown() throws SQLException {
        cleanup();
        super.teardown();
    }
}
