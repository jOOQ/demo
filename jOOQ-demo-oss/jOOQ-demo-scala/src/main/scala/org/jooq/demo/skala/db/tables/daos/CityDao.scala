/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.daos


import java.lang.Long
import java.lang.String
import java.time.LocalDateTime
import java.util.Collection
import java.util.List

import org.jooq.Configuration
import org.jooq.demo.skala.db.tables.City
import org.jooq.demo.skala.db.tables.records.CityRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
class CityDao(configuration: Configuration) extends DAOImpl[CityRecord, org.jooq.demo.skala.db.tables.pojos.City, Long](City.CITY, classOf[org.jooq.demo.skala.db.tables.pojos.City], configuration) {

  /**
   * Create a new CityDao without any configuration
   */
  def this() = this(null)

  override def getId(o: org.jooq.demo.skala.db.tables.pojos.City): Long = o.getCityId
  override def insert(obj: org.jooq.demo.skala.db.tables.pojos.City): Unit = super.insert(obj)
  override def insert(objs: org.jooq.demo.skala.db.tables.pojos.City*): Unit = super.insert(objs*)
  override def insert(objs: Collection[org.jooq.demo.skala.db.tables.pojos.City]): Unit = super.insert(objs)
  override def update(obj: org.jooq.demo.skala.db.tables.pojos.City): Unit = super.update(obj)
  override def update(objs: org.jooq.demo.skala.db.tables.pojos.City*): Unit = super.update(objs*)
  override def update(objs: Collection[org.jooq.demo.skala.db.tables.pojos.City]): Unit = super.update(objs)
  override def merge(obj: org.jooq.demo.skala.db.tables.pojos.City): Unit = super.merge(obj)
  override def merge(objs: org.jooq.demo.skala.db.tables.pojos.City*): Unit = super.merge(objs*)
  override def merge(objs: Collection[org.jooq.demo.skala.db.tables.pojos.City]): Unit = super.merge(objs)
  override def delete(obj: org.jooq.demo.skala.db.tables.pojos.City): Unit = super.delete(obj)
  override def delete(objs: org.jooq.demo.skala.db.tables.pojos.City*): Unit = super.delete(objs*)
  override def delete(objs: Collection[org.jooq.demo.skala.db.tables.pojos.City]): Unit = super.delete(objs)
  override def deleteById(id: Long): Unit = super.deleteById(id)
  override def deleteById(ids: Long*): Unit = super.deleteById(ids*)
  override def deleteById(ids: Collection[Long]): Unit = super.deleteById(ids)

  /**
   * Fetch records that have <code>city_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfCityId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.City] = fetchRange(City.CITY.CITY_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>city_id IN (values)</code>
   */
  def fetchByCityId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.City] = fetch(City.CITY.CITY_ID, values*)

  /**
   * Fetch a unique record that has <code>city_id = value</code>
   */
  def fetchOneByCityId(value: Long): org.jooq.demo.skala.db.tables.pojos.City = fetchOne(City.CITY.CITY_ID, value)

  /**
   * Fetch records that have <code>city BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfCity(lowerInclusive: String, upperInclusive: String): List[org.jooq.demo.skala.db.tables.pojos.City] = fetchRange(City.CITY.CITY_, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>city IN (values)</code>
   */
  def fetchByCity(values: String*): List[org.jooq.demo.skala.db.tables.pojos.City] = fetch(City.CITY.CITY_, values*)

  /**
   * Fetch records that have <code>country_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfCountryId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.City] = fetchRange(City.CITY.COUNTRY_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>country_id IN (values)</code>
   */
  def fetchByCountryId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.City] = fetch(City.CITY.COUNTRY_ID, values*)

  /**
   * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime, upperInclusive: LocalDateTime): List[org.jooq.demo.skala.db.tables.pojos.City] = fetchRange(City.CITY.LAST_UPDATE, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>last_update IN (values)</code>
   */
  def fetchByLastUpdate(values: LocalDateTime*): List[org.jooq.demo.skala.db.tables.pojos.City] = fetch(City.CITY.LAST_UPDATE, values*)
}
