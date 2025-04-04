/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import org.jooq.demo.kotlin.db.tables.FilmNotInStock
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class FilmNotInStockRecord() : TableRecordImpl<FilmNotInStockRecord>(FilmNotInStock.FILM_NOT_IN_STOCK) {

    open var pFilmCount: Int?
        set(value): Unit = set(0, value)
        get(): Int? = get(0) as Int?

    /**
     * Create a detached, initialised FilmNotInStockRecord
     */
    constructor(pFilmCount: Int? = null): this() {
        this.pFilmCount = pFilmCount
        resetTouchedOnNotNull()
    }

    /**
     * Create a detached, initialised FilmNotInStockRecord
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.FilmNotInStock?): this() {
        if (value != null) {
            this.pFilmCount = value.pFilmCount
            resetTouchedOnNotNull()
        }
    }
}
