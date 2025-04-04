/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.lang.String
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.skala.db.tables.Country
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class CountryRecord extends UpdatableRecordImpl[CountryRecord](Country.COUNTRY) {

  /**
   * Setter for <code>public.country.country_id</code>.
   */
  def setCountryId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.country.country_id</code>.
   */
  def getCountryId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.country.country</code>.
   */
  def setCountry(value: String): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.country.country</code>.
   */
  def getCountry: String = get(1).asInstanceOf[String]

  /**
   * Setter for <code>public.country.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.country.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(2).asInstanceOf[LocalDateTime]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  /**
   * Create a detached, initialised CountryRecord
   */
  def this(countryId : Long, country : String, lastUpdate : LocalDateTime) = {
    this()

    this.setCountryId(countryId)
    this.setCountry(country)
    this.setLastUpdate(lastUpdate)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised CountryRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Country) = {
    this()

    if (value != null) {
      this.setCountryId(value.getCountryId)
      this.setCountry(value.getCountry)
      this.setLastUpdate(value.getLastUpdate)
      resetTouchedOnNotNull()
    }
  }
}
