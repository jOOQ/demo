/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.pojos


import java.io.Serializable
import java.lang.Long
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
case class FilmCategory(
  var filmId: Long,
  var categoryId: Long,
  var lastUpdate: LocalDateTime
) extends Serializable {

  def this() = this(null, null, null)

  def this(value: FilmCategory) = this(
    value.filmId,
    value.categoryId,
    value.lastUpdate
  )

  /**
   * Getter for <code>public.film_category.film_id</code>.
   */
  def getFilmId: Long = this.filmId

  /**
   * Setter for <code>public.film_category.film_id</code>.
   */
  def setFilmId(filmId: Long): Unit = {
    this.filmId = filmId
  }

  /**
   * Getter for <code>public.film_category.category_id</code>.
   */
  def getCategoryId: Long = this.categoryId

  /**
   * Setter for <code>public.film_category.category_id</code>.
   */
  def setCategoryId(categoryId: Long): Unit = {
    this.categoryId = categoryId
  }

  /**
   * Getter for <code>public.film_category.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = this.lastUpdate

  /**
   * Setter for <code>public.film_category.last_update</code>.
   */
  def setLastUpdate(lastUpdate: LocalDateTime): Unit = {
    this.lastUpdate = lastUpdate
  }
}