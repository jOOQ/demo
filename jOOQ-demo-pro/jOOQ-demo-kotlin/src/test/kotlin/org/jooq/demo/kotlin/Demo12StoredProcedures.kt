package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.routines.references.inventoryInStock
import org.jooq.demo.kotlin.db.tables.references.*
import org.jooq.demo.kotlin.db.tables.references.FILM_IN_STOCK
import org.jooq.demo.kotlin.db.tables.references.INVENTORY
import org.jooq.demo.kotlin.db.tables.references.STORE
import org.jooq.impl.DSL
import org.jooq.impl.DSL.*
import org.jooq.impl.SQLDataType
import org.junit.After
import org.junit.Test
import java.sql.SQLException

class Demo12StoredProcedures : AbstractDemo() {

    @Test
    fun procedures() {
        // Calling stored procedures and functions ("routines") is very easy with jOOQ and its code generator. Every
        // routine has a corresponding generated stub in Java, which you can call with a Configuration, and jOOQ will
        // take care of binding all the IN/OUT parameters.

        title("Standalone procedure calls require a configuration argument")
        println("Inventory 1 in stock: " + inventoryInStock(configuration, 1L))

        title("But stored functions can also be embedded in queries")
        ctx.select(
                INVENTORY.INVENTORY_ID,
                inventoryInStock(INVENTORY.INVENTORY_ID)
            )
            .from(INVENTORY)
            .limit(5)
            .fetch()

        title("Table valued functions are particularly powerful with jOOQ")
        ctx.select(
                FILM.TITLE,
                STORE.STORE_ID,
                STORE.address.city.CITY_,
                FILM_IN_STOCK.P_FILM_COUNT
            )
            .from(
                FILM,
                STORE,
                lateral(FILM_IN_STOCK(FILM.FILM_ID, STORE.STORE_ID)).`as`(FILM_IN_STOCK)
            )
            .orderBy(STORE.STORE_ID)
            .limit(10)
            .fetch()

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/code-generation/codegen-procedures/
        // - https://www.jooq.org/doc/latest/manual/sql-execution/stored-procedures/
        //
        // And also various use-cases described in these blog posts:
        // - https://blog.jooq.org/the-best-way-to-call-stored-procedures-from-java-with-jooq/
        // - https://blog.jooq.org/calling-procedures-with-default-parameters-using-jdbc-or-jooq/
        // - https://blog.jooq.org/postgresqls-table-valued-functions/
        // - https://blog.jooq.org/access-pl-sql-procedures-from-java-with-jooq-a-jpublisher-alternative/
        // - https://blog.jooq.org/use-jooq-to-read-write-oracle-plsql-record-types/
        // - https://blog.jooq.org/using-oracle-aq-in-java-wont-get-any-easier-than-this/
        // - https://blog.jooq.org/how-to-pass-a-table-valued-parameter-to-a-t-sql-function-with-jooq/
    }


    @Test
    fun proceduralLanguage() {
        title("The procedural language API allows for creating procedures or anonymous blocks")

        // This is a commercial only feature. Check out the commercial demo for details
        /* [pro] */
        val i = `var`("i", SQLDataType.BIGINT)
        ctx.begin(
            for_(i).`in`(201L, 210L).loop(
                insertInto(ACTOR)
                    .columns(ACTOR.ACTOR_ID, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                    .values(i, concat(inline("F"), i), concat(inline("L"), i))
            )
        )
            .execute()

        ctx.selectFrom(ACTOR)
            .where(ACTOR.ACTOR_ID.gt(200L))
            .fetch()
        /* [/pro] */

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/procedural-statements/
    }

    @After
    @Throws(SQLException::class)
    override fun teardown() {
        cleanup(ACTOR, ACTOR.ACTOR_ID)
        super.teardown()
    }

}