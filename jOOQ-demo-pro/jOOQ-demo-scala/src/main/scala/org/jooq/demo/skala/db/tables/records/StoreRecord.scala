/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.lang.String
import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record5
import org.jooq.Row5
import org.jooq.demo.skala.db.tables.Store
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class StoreRecord extends UpdatableRecordImpl[StoreRecord](Store.STORE) with Record5[Long, Long, Long, LocalDateTime, String] {

  /**
   * Setter for <code>public.store.store_id</code>.
   */
  def setStoreId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.store.store_id</code>.
   */
  def getStoreId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.store.manager_staff_id</code>.
   */
  def setManagerStaffId(value: Long): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.store.manager_staff_id</code>.
   */
  def getManagerStaffId: Long = get(1).asInstanceOf[Long]

  /**
   * Setter for <code>public.store.address_id</code>.
   */
  def setAddressId(value: Long): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.store.address_id</code>.
   */
  def getAddressId: Long = get(2).asInstanceOf[Long]

  /**
   * Setter for <code>public.store.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.store.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(3).asInstanceOf[LocalDateTime]

  /**
   * Setter for <code>public.store.full_address</code>.
   */
  def setFullAddress(value: String): Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.store.full_address</code>.
   */
  def getFullAddress: String = get(4).asInstanceOf[String]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  // -------------------------------------------------------------------------
  // Record5 type implementation
  // -------------------------------------------------------------------------

  override def fieldsRow: Row5[Long, Long, Long, LocalDateTime, String] = super.fieldsRow.asInstanceOf[ Row5[Long, Long, Long, LocalDateTime, String] ]

  override def valuesRow: Row5[Long, Long, Long, LocalDateTime, String] = super.valuesRow.asInstanceOf[ Row5[Long, Long, Long, LocalDateTime, String] ]
  override def field1: Field[Long] = Store.STORE.STORE_ID
  override def field2: Field[Long] = Store.STORE.MANAGER_STAFF_ID
  override def field3: Field[Long] = Store.STORE.ADDRESS_ID
  override def field4: Field[LocalDateTime] = Store.STORE.LAST_UPDATE
  override def field5: Field[String] = Store.STORE.FULL_ADDRESS
  override def component1: Long = getStoreId
  override def component2: Long = getManagerStaffId
  override def component3: Long = getAddressId
  override def component4: LocalDateTime = getLastUpdate
  override def component5: String = getFullAddress
  override def value1: Long = getStoreId
  override def value2: Long = getManagerStaffId
  override def value3: Long = getAddressId
  override def value4: LocalDateTime = getLastUpdate
  override def value5: String = getFullAddress

  override def value1(value: Long): StoreRecord = {
    setStoreId(value)
    this
  }

  override def value2(value: Long): StoreRecord = {
    setManagerStaffId(value)
    this
  }

  override def value3(value: Long): StoreRecord = {
    setAddressId(value)
    this
  }

  override def value4(value: LocalDateTime): StoreRecord = {
    setLastUpdate(value)
    this
  }

  override def value5(value: String): StoreRecord = {
    setFullAddress(value)
    this
  }

  override def values(value1 : Long, value2 : Long, value3 : Long, value4 : LocalDateTime, value5 : String): StoreRecord = {
    this.value1(value1)
    this.value2(value2)
    this.value3(value3)
    this.value4(value4)
    this.value5(value5)
    this
  }

  /**
   * Create a detached, initialised StoreRecord
   */
  def this(storeId : Long, managerStaffId : Long, addressId : Long, lastUpdate : LocalDateTime, fullAddress : String) = {
    this()

    this.setStoreId(storeId)
    this.setManagerStaffId(managerStaffId)
    this.setAddressId(addressId)
    this.setLastUpdate(lastUpdate)
    this.setFullAddress(fullAddress)
  }

  /**
   * Create a detached, initialised StoreRecord
   */
  def this(storeId : Long, managerStaffId : Long, addressId : Long, fullAddress : String) = {
    this()

    this.setStoreId(storeId)
    this.setManagerStaffId(managerStaffId)
    this.setAddressId(addressId)
    this.setFullAddress(fullAddress)
  }

  /**
   * Create a detached, initialised StoreRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Store) = {
    this()

    if (value != null) {
      this.setStoreId(value.getStoreId)
      this.setManagerStaffId(value.getManagerStaffId)
      this.setAddressId(value.getAddressId)
      this.setLastUpdate(value.getLastUpdate)
      this.setFullAddress(value.getFullAddress)
    }
  }
}
