/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.daos


import java.lang.Long
import java.time.LocalDateTime
import java.util.List

import org.jooq.Configuration
import org.jooq.demo.skala.db.tables.Store
import org.jooq.demo.skala.db.tables.records.StoreRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
class StoreDao(configuration: Configuration) extends DAOImpl[StoreRecord, org.jooq.demo.skala.db.tables.pojos.Store, Long](Store.STORE, classOf[org.jooq.demo.skala.db.tables.pojos.Store], configuration) {

  /**
   * Create a new StoreDao without any configuration
   */
  def this() = this(null)

  override def getId(o: org.jooq.demo.skala.db.tables.pojos.Store): Long = o.getStoreId

  /**
   * Fetch records that have <code>store_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfStoreId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetchRange(Store.STORE.STORE_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>store_id IN (values)</code>
   */
  def fetchByStoreId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetch(Store.STORE.STORE_ID, values:_*)

  /**
   * Fetch a unique record that has <code>store_id = value</code>
   */
  def fetchOneByStoreId(value: Long): org.jooq.demo.skala.db.tables.pojos.Store = fetchOne(Store.STORE.STORE_ID, value)

  /**
   * Fetch records that have <code>manager_staff_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfManagerStaffId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetchRange(Store.STORE.MANAGER_STAFF_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>manager_staff_id IN (values)</code>
   */
  def fetchByManagerStaffId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetch(Store.STORE.MANAGER_STAFF_ID, values:_*)

  /**
   * Fetch records that have <code>address_id BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfAddressId(lowerInclusive: Long, upperInclusive: Long): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetchRange(Store.STORE.ADDRESS_ID, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>address_id IN (values)</code>
   */
  def fetchByAddressId(values: Long*): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetch(Store.STORE.ADDRESS_ID, values:_*)

  /**
   * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
   * upperInclusive</code>
   */
  def fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime, upperInclusive: LocalDateTime): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetchRange(Store.STORE.LAST_UPDATE, lowerInclusive, upperInclusive)

  /**
   * Fetch records that have <code>last_update IN (values)</code>
   */
  def fetchByLastUpdate(values: LocalDateTime*): List[org.jooq.demo.skala.db.tables.pojos.Store] = fetch(Store.STORE.LAST_UPDATE, values:_*)
}
