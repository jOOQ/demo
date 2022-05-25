package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.routines.references.inventoryInStock
import org.jooq.demo.kotlin.db.tables.references.FILM
import org.jooq.demo.kotlin.db.tables.references.FILM_IN_STOCK
import org.jooq.demo.kotlin.db.tables.references.INVENTORY
import org.jooq.demo.kotlin.db.tables.references.STORE
import org.jooq.impl.DSL.lateral
import org.junit.Test

class Demo12StoredProcedures : AbstractDemo() {

    @Test
    fun procedures() {
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
    }
}