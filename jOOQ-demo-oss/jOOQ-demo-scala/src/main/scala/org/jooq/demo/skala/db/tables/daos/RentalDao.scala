/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.daos


import java.lang.Long
import java.time.LocalDateTime
import java.util.Collection
import java.util.List

import org.jooq.Configuration
import org.jooq.demo.skala.db.tables.Rental
import org.jooq.demo.skala.db.tables.records.RentalRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
class RentalDao(configuration: Configuration) extends DAOImpl[RentalRecord, org.jooq.demo.skala.db.tables.pojos.Rental, Long](Rental.RENTAL, classOf[org.jooq.demo.skala.db.tables.pojos.Rental], configuration) {

  /**
   * Create a new RentalDao without any configuration
   */
  def this() = this(null)

  override def getId(o: org.jooq.demo.skala.db.tables.pojos.Rental): Long = o.getRentalId
  override def insert(obj: org.jooq.demo.skala.db.tables.pojos.Rental): Unit = super.insert(obj)
  override def insert(objs: org.jooq.demo.skala.db.tables.pojos.Rental*): Unit = super.insert(objs*)
  override def insert(objs: Collection[org.jooq.demo.skala.db.tables.pojos.Rental]): Unit = super.insert(objs)
  override def update(obj: org.jooq.demo.skala.db.tables.pojos.Rental): Unit = super.update(obj)
  override def update(objs: org.jooq.demo.skala.db.tables.pojos.Rental*): Unit = super.update(objs*)
  override def update(objs: Collection[org.jooq.demo.skala.db.tables.pojos.Rental]): Unit = super.update(objs)
  override def merge(obj: org.jooq.demo.skala.db.tables.pojos.Rental): Unit = super.merge(obj)
  override def merge(objs: org.jooq.demo.skala.db.tables.pojos.Rental*): Unit = super.merge(objs*)
  override def merge(objs: Collection[org.jooq.demo.skala.db.tables.pojos.Rental]): Unit = super.merge(objs)
  override def delete(obj: org.jooq.demo.skala.db.tables.pojos.Rental): Unit = super.delete(obj)
  override def delete(objs: org.jooq.demo.skala.db.tables.pojos.Rental*): Unit = super.delete(objs*)
  override def delete(objs: Collection[org.jooq.demo.skala.db.tables.pojos.Rental]): Unit = super.delete(objs)
  override def deleteById(id: Long): Unit = super.deleteById(id)
  override def deleteById(ids: Long*): Unit = super.deleteById(ids*)
  override def deleteById(ids: Collection[Long]): Unit = super.deleteById(ids)

  /**
   * Fetch records that have <code>rental_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfRentalId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.RENTAL_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>rental_id IN (values)</code>
   */
  def fetchByRentalId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.RENTAL_ID, values*)

  /**
   * Fetch a unique record that has <code>rental_id = value</code>
   */
  def fetchOneByRentalId(value: Long): org.jooq.demo.skala.db.tables.pojos.Rental = fetchOne(Rental.RENTAL.RENTAL_ID, value)

  /**
   * Fetch records that have <code>rental_date BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfRentalDate(lowerInclusive: LocalDateTime, upperInclusive: LocalDateTime): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.RENTAL_DATE, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>rental_date IN (values)</code>
   */
  def fetchByRentalDate(values: LocalDateTime*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.RENTAL_DATE, values*)

  /**
   * Fetch records that have <code>inventory_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfInventoryId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.INVENTORY_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>inventory_id IN (values)</code>
   */
  def fetchByInventoryId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.INVENTORY_ID, values*)

  /**
   * Fetch records that have <code>customer_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfCustomerId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.CUSTOMER_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>customer_id IN (values)</code>
   */
  def fetchByCustomerId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.CUSTOMER_ID, values*)

  /**
   * Fetch records that have <code>return_date BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfReturnDate(lowerInclusive: LocalDateTime, upperInclusive: LocalDateTime): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.RETURN_DATE, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>return_date IN (values)</code>
   */
  def fetchByReturnDate(values: LocalDateTime*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.RETURN_DATE, values*)

  /**
   * Fetch records that have <code>staff_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfStaffId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.STAFF_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>staff_id IN (values)</code>
   */
  def fetchByStaffId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.STAFF_ID, values*)

  /**
   * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime, upperInclusive: LocalDateTime): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetchRange(Rental.RENTAL.LAST_UPDATE, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>last_update IN (values)</code>
   */
  def fetchByLastUpdate(values: LocalDateTime*): List[org.jooq.demo.skala.db.tables.pojos.Rental] = fetch(Rental.RENTAL.LAST_UPDATE, values*)
}
