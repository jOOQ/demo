/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Deprecated
import java.lang.Integer
import java.lang.Long
import java.lang.Object
import java.lang.Short
import java.lang.String
import java.math.BigDecimal
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.skala.db.enums.MpaaRating
import org.jooq.demo.skala.db.tables.Film
import org.jooq.impl.UpdatableRecordImpl

import scala.Array


/**
 * This class is generated by jOOQ.
 */
class FilmRecord extends UpdatableRecordImpl[FilmRecord](Film.FILM) {

  /**
   * Setter for <code>public.film.film_id</code>.
   */
  def setFilmId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.film.film_id</code>.
   */
  def getFilmId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.film.title</code>.
   */
  def setTitle(value: String): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.film.title</code>.
   */
  def getTitle: String = get(1).asInstanceOf[String]

  /**
   * Setter for <code>public.film.description</code>.
   */
  def setDescription(value: String): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.film.description</code>.
   */
  def getDescription: String = get(2).asInstanceOf[String]

  /**
   * Setter for <code>public.film.release_year</code>.
   */
  def setReleaseYear(value: Integer): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.film.release_year</code>.
   */
  def getReleaseYear: Integer = get(3).asInstanceOf[Integer]

  /**
   * Setter for <code>public.film.language_id</code>.
   */
  def setLanguageId(value: Long): Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.film.language_id</code>.
   */
  def getLanguageId: Long = get(4).asInstanceOf[Long]

  /**
   * Setter for <code>public.film.original_language_id</code>.
   */
  def setOriginalLanguageId(value: Long): Unit = {
    set(5, value)
  }

  /**
   * Getter for <code>public.film.original_language_id</code>.
   */
  def getOriginalLanguageId: Long = get(5).asInstanceOf[Long]

  /**
   * Setter for <code>public.film.rental_duration</code>.
   */
  def setRentalDuration(value: Short): Unit = {
    set(6, value)
  }

  /**
   * Getter for <code>public.film.rental_duration</code>.
   */
  def getRentalDuration: Short = get(6).asInstanceOf[Short]

  /**
   * Setter for <code>public.film.rental_rate</code>.
   */
  def setRentalRate(value: BigDecimal): Unit = {
    set(7, value)
  }

  /**
   * Getter for <code>public.film.rental_rate</code>.
   */
  def getRentalRate: BigDecimal = get(7).asInstanceOf[BigDecimal]

  /**
   * Setter for <code>public.film.length</code>.
   */
  def setLength(value: Short): Unit = {
    set(8, value)
  }

  /**
   * Getter for <code>public.film.length</code>.
   */
  def getLength: Short = get(8).asInstanceOf[Short]

  /**
   * Setter for <code>public.film.replacement_cost</code>.
   */
  def setReplacementCost(value: BigDecimal): Unit = {
    set(9, value)
  }

  /**
   * Getter for <code>public.film.replacement_cost</code>.
   */
  def getReplacementCost: BigDecimal = get(9).asInstanceOf[BigDecimal]

  /**
   * Setter for <code>public.film.rating</code>.
   */
  def setRating(value: MpaaRating): Unit = {
    set(10, value)
  }

  /**
   * Getter for <code>public.film.rating</code>.
   */
  def getRating: MpaaRating = get(10).asInstanceOf[MpaaRating]

  /**
   * Setter for <code>public.film.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(11, value)
  }

  /**
   * Getter for <code>public.film.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(11).asInstanceOf[LocalDateTime]

  /**
   * Setter for <code>public.film.special_features</code>.
   */
  def setSpecialFeatures(value: Array[String]): Unit = {
    set(12, value)
  }

  /**
   * Getter for <code>public.film.special_features</code>.
   */
  def getSpecialFeatures: Array[String] = get(12).asInstanceOf[Array[String]]

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type,
   * it may have been excluded from code generation. If this is a built-in type,
   * you can define an explicit {@link org.jooq.Binding} to specify how this
   * type should be handled. Deprecation can be turned off using {@literal
   * <deprecationOnUnknownTypes/>} in your code generator configuration.
   */
  @Deprecated
  def setFulltext(value: Object): Unit = {
    set(13, value)
  }

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type,
   * it may have been excluded from code generation. If this is a built-in type,
   * you can define an explicit {@link org.jooq.Binding} to specify how this
   * type should be handled. Deprecation can be turned off using {@literal
   * <deprecationOnUnknownTypes/>} in your code generator configuration.
   */
  @Deprecated
  def getFulltext: Object = get(13).asInstanceOf[Object]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  /**
   * Create a detached, initialised FilmRecord
   */
  def this(filmId : Long, title : String, description : String, releaseYear : Integer, languageId : Long, originalLanguageId : Long, rentalDuration : Short, rentalRate : BigDecimal, length : Short, replacementCost : BigDecimal, rating : MpaaRating, lastUpdate : LocalDateTime, specialFeatures : Array[String], fulltext : Object) = {
    this()

    this.setFilmId(filmId)
    this.setTitle(title)
    this.setDescription(description)
    this.setReleaseYear(releaseYear)
    this.setLanguageId(languageId)
    this.setOriginalLanguageId(originalLanguageId)
    this.setRentalDuration(rentalDuration)
    this.setRentalRate(rentalRate)
    this.setLength(length)
    this.setReplacementCost(replacementCost)
    this.setRating(rating)
    this.setLastUpdate(lastUpdate)
    this.setSpecialFeatures(specialFeatures)
    this.setFulltext(fulltext)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised FilmRecord
   */
  def this(filmId : Long, title : String, description : String, releaseYear : Integer, languageId : Long, originalLanguageId : Long, rentalDuration : Short, rentalRate : BigDecimal, length : Short, replacementCost : BigDecimal, rating : MpaaRating, specialFeatures : Array[String], fulltext : Object) = {
    this()

    this.setFilmId(filmId)
    this.setTitle(title)
    this.setDescription(description)
    this.setReleaseYear(releaseYear)
    this.setLanguageId(languageId)
    this.setOriginalLanguageId(originalLanguageId)
    this.setRentalDuration(rentalDuration)
    this.setRentalRate(rentalRate)
    this.setLength(length)
    this.setReplacementCost(replacementCost)
    this.setRating(rating)
    this.setSpecialFeatures(specialFeatures)
    this.setFulltext(fulltext)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised FilmRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Film) = {
    this()

    if (value != null) {
      this.setFilmId(value.getFilmId)
      this.setTitle(value.getTitle)
      this.setDescription(value.getDescription)
      this.setReleaseYear(value.getReleaseYear)
      this.setLanguageId(value.getLanguageId)
      this.setOriginalLanguageId(value.getOriginalLanguageId)
      this.setRentalDuration(value.getRentalDuration)
      this.setRentalRate(value.getRentalRate)
      this.setLength(value.getLength)
      this.setReplacementCost(value.getReplacementCost)
      this.setRating(value.getRating)
      this.setLastUpdate(value.getLastUpdate)
      this.setSpecialFeatures(value.getSpecialFeatures)
      this.setFulltext(value.getFulltext)
      resetTouchedOnNotNull()
    }
  }
}
