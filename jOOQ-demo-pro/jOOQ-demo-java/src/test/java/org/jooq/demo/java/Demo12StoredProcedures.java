package org.jooq.demo.java;

import org.jooq.Variable;
import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.Routines;
import org.junit.After;
import org.junit.Test;

import java.sql.SQLException;

import static org.jooq.demo.java.db.Routines.inventoryInStock;
import static org.jooq.demo.java.db.Tables.*;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.INTEGER;

public class Demo12StoredProcedures extends AbstractDemo {

    @Test
    public void procedures() {
        title("Standalone procedure calls require a configuration argument");
        println("Inventory 1 in stock: " + Routines.inventoryInStock(configuration, 1L));

        title("But stored functions can also be embedded in queries");
        ctx.select(INVENTORY.INVENTORY_ID, inventoryInStock(INVENTORY.INVENTORY_ID))
            .from(INVENTORY)
            .limit(5)
            .fetch();

        title("Table valued functions are particularly powerful with jOOQ");
        ctx.select(
               FILM.TITLE,
               STORE.STORE_ID,
               STORE.address().city().CITY_,
               FILM_IN_STOCK.P_FILM_COUNT)
           .from(FILM, STORE, lateral(FILM_IN_STOCK(FILM.FILM_ID, STORE.STORE_ID)).as(FILM_IN_STOCK))
           .orderBy(FILM.FILM_ID, STORE.STORE_ID)
           .limit(10)
           .fetch();
    }

    @Test
    public void proceduralLanguage() {
        title("The procedural language API allows for creating procedures or anonymous blocks");

        // This is a commercial only feature. Check out the commercial demo for details
        /* [pro] */
        Variable<Long> i = var("i", BIGINT);
        ctx.begin(
                for_(i).in(201L, 210L).loop(
                    insertInto(ACTOR)
                        .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                        .values(i, concat(inline("F"), i), concat(inline("L"), i))
                )
           )
            .execute();

        ctx.selectFrom(ACTOR).where(ACTOR.ACTOR_ID.gt(200L)).fetch();
        /* [/pro] */
    }

    @After
    public void teardown() throws SQLException {
        cleanup(ACTOR, ACTOR.ACTOR_ID);
        super.teardown();
    }
}
