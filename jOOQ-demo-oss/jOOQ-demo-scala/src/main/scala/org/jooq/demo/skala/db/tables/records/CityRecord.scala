/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.lang.String
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.skala.db.tables.City
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class CityRecord extends UpdatableRecordImpl[CityRecord](City.CITY) {

  /**
   * Setter for <code>public.city.city_id</code>.
   */
  def setCityId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.city.city_id</code>.
   */
  def getCityId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.city.city</code>.
   */
  def setCity(value: String): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.city.city</code>.
   */
  def getCity: String = get(1).asInstanceOf[String]

  /**
   * Setter for <code>public.city.country_id</code>.
   */
  def setCountryId(value: Long): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.city.country_id</code>.
   */
  def getCountryId: Long = get(2).asInstanceOf[Long]

  /**
   * Setter for <code>public.city.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.city.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(3).asInstanceOf[LocalDateTime]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  /**
   * Create a detached, initialised CityRecord
   */
  def this(cityId : Long, city : String, countryId : Long, lastUpdate : LocalDateTime) = {
    this()

    this.setCityId(cityId)
    this.setCity(city)
    this.setCountryId(countryId)
    this.setLastUpdate(lastUpdate)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised CityRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.City) = {
    this()

    if (value != null) {
      this.setCityId(value.getCityId)
      this.setCity(value.getCity)
      this.setCountryId(value.getCountryId)
      this.setLastUpdate(value.getLastUpdate)
      resetTouchedOnNotNull()
    }
  }
}
