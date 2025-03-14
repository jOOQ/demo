/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Integer

import org.jooq.demo.skala.db.tables.FilmNotInStock
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class FilmNotInStockRecord extends TableRecordImpl[FilmNotInStockRecord](FilmNotInStock.FILM_NOT_IN_STOCK) {

  /**
   * Setter for <code>public.film_not_in_stock.p_film_count</code>.
   */
  def setPFilmCount(value: Integer): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.film_not_in_stock.p_film_count</code>.
   */
  def getPFilmCount: Integer = get(0).asInstanceOf[Integer]

  /**
   * Create a detached, initialised FilmNotInStockRecord
   */
  def this(pFilmCount : Integer) = {
    this()

    this.setPFilmCount(pFilmCount)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised FilmNotInStockRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.FilmNotInStock) = {
    this()

    if (value != null) {
      this.setPFilmCount(value.getPFilmCount)
      resetTouchedOnNotNull()
    }
  }
}
