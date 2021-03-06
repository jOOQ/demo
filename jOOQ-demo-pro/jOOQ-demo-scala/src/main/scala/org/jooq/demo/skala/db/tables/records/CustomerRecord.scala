/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Boolean
import java.lang.Integer
import java.lang.Long
import java.lang.String
import java.time.LocalDate
import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record12
import org.jooq.Row12
import org.jooq.demo.skala.db.tables.Customer
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class CustomerRecord extends UpdatableRecordImpl[CustomerRecord](Customer.CUSTOMER) with Record12[Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String] {

  /**
   * Setter for <code>public.customer.customer_id</code>.
   */
  def setCustomerId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.customer.customer_id</code>.
   */
  def getCustomerId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.customer.store_id</code>.
   */
  def setStoreId(value: Long): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.customer.store_id</code>.
   */
  def getStoreId: Long = get(1).asInstanceOf[Long]

  /**
   * Setter for <code>public.customer.first_name</code>.
   */
  def setFirstName(value: String): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.customer.first_name</code>.
   */
  def getFirstName: String = get(2).asInstanceOf[String]

  /**
   * Setter for <code>public.customer.last_name</code>.
   */
  def setLastName(value: String): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.customer.last_name</code>.
   */
  def getLastName: String = get(3).asInstanceOf[String]

  /**
   * Setter for <code>public.customer.email</code>.
   */
  def setEmail(value: String): Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.customer.email</code>.
   */
  def getEmail: String = get(4).asInstanceOf[String]

  /**
   * Setter for <code>public.customer.address_id</code>.
   */
  def setAddressId(value: Long): Unit = {
    set(5, value)
  }

  /**
   * Getter for <code>public.customer.address_id</code>.
   */
  def getAddressId: Long = get(5).asInstanceOf[Long]

  /**
   * Setter for <code>public.customer.activebool</code>.
   */
  def setActivebool(value: Boolean): Unit = {
    set(6, value)
  }

  /**
   * Getter for <code>public.customer.activebool</code>.
   */
  def getActivebool: Boolean = get(6).asInstanceOf[Boolean]

  /**
   * Setter for <code>public.customer.create_date</code>.
   */
  def setCreateDate(value: LocalDate): Unit = {
    set(7, value)
  }

  /**
   * Getter for <code>public.customer.create_date</code>.
   */
  def getCreateDate: LocalDate = get(7).asInstanceOf[LocalDate]

  /**
   * Setter for <code>public.customer.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(8, value)
  }

  /**
   * Getter for <code>public.customer.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(8).asInstanceOf[LocalDateTime]

  /**
   * Setter for <code>public.customer.active</code>.
   */
  def setActive(value: Integer): Unit = {
    set(9, value)
  }

  /**
   * Getter for <code>public.customer.active</code>.
   */
  def getActive: Integer = get(9).asInstanceOf[Integer]

  /**
   * Setter for <code>public.customer.full_address</code>.
   */
  def setFullAddress(value: String): Unit = {
    set(10, value)
  }

  /**
   * Getter for <code>public.customer.full_address</code>.
   */
  def getFullAddress: String = get(10).asInstanceOf[String]

  /**
   * Setter for <code>public.customer.full_name</code>.
   */
  def setFullName(value: String): Unit = {
    set(11, value)
  }

  /**
   * Getter for <code>public.customer.full_name</code>.
   */
  def getFullName: String = get(11).asInstanceOf[String]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  // -------------------------------------------------------------------------
  // Record12 type implementation
  // -------------------------------------------------------------------------

  override def fieldsRow: Row12[Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String] = super.fieldsRow.asInstanceOf[ Row12[Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String] ]

  override def valuesRow: Row12[Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String] = super.valuesRow.asInstanceOf[ Row12[Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer, String, String] ]
  override def field1: Field[Long] = Customer.CUSTOMER.CUSTOMER_ID
  override def field2: Field[Long] = Customer.CUSTOMER.STORE_ID
  override def field3: Field[String] = Customer.CUSTOMER.FIRST_NAME
  override def field4: Field[String] = Customer.CUSTOMER.LAST_NAME
  override def field5: Field[String] = Customer.CUSTOMER.EMAIL
  override def field6: Field[Long] = Customer.CUSTOMER.ADDRESS_ID
  override def field7: Field[Boolean] = Customer.CUSTOMER.ACTIVEBOOL
  override def field8: Field[LocalDate] = Customer.CUSTOMER.CREATE_DATE
  override def field9: Field[LocalDateTime] = Customer.CUSTOMER.LAST_UPDATE
  override def field10: Field[Integer] = Customer.CUSTOMER.ACTIVE
  override def field11: Field[String] = Customer.CUSTOMER.FULL_ADDRESS
  override def field12: Field[String] = Customer.CUSTOMER.FULL_NAME
  override def component1: Long = getCustomerId
  override def component2: Long = getStoreId
  override def component3: String = getFirstName
  override def component4: String = getLastName
  override def component5: String = getEmail
  override def component6: Long = getAddressId
  override def component7: Boolean = getActivebool
  override def component8: LocalDate = getCreateDate
  override def component9: LocalDateTime = getLastUpdate
  override def component10: Integer = getActive
  override def component11: String = getFullAddress
  override def component12: String = getFullName
  override def value1: Long = getCustomerId
  override def value2: Long = getStoreId
  override def value3: String = getFirstName
  override def value4: String = getLastName
  override def value5: String = getEmail
  override def value6: Long = getAddressId
  override def value7: Boolean = getActivebool
  override def value8: LocalDate = getCreateDate
  override def value9: LocalDateTime = getLastUpdate
  override def value10: Integer = getActive
  override def value11: String = getFullAddress
  override def value12: String = getFullName

  override def value1(value: Long): CustomerRecord = {
    setCustomerId(value)
    this
  }

  override def value2(value: Long): CustomerRecord = {
    setStoreId(value)
    this
  }

  override def value3(value: String): CustomerRecord = {
    setFirstName(value)
    this
  }

  override def value4(value: String): CustomerRecord = {
    setLastName(value)
    this
  }

  override def value5(value: String): CustomerRecord = {
    setEmail(value)
    this
  }

  override def value6(value: Long): CustomerRecord = {
    setAddressId(value)
    this
  }

  override def value7(value: Boolean): CustomerRecord = {
    setActivebool(value)
    this
  }

  override def value8(value: LocalDate): CustomerRecord = {
    setCreateDate(value)
    this
  }

  override def value9(value: LocalDateTime): CustomerRecord = {
    setLastUpdate(value)
    this
  }

  override def value10(value: Integer): CustomerRecord = {
    setActive(value)
    this
  }

  override def value11(value: String): CustomerRecord = {
    setFullAddress(value)
    this
  }

  override def value12(value: String): CustomerRecord = {
    setFullName(value)
    this
  }

  override def values(value1 : Long, value2 : Long, value3 : String, value4 : String, value5 : String, value6 : Long, value7 : Boolean, value8 : LocalDate, value9 : LocalDateTime, value10 : Integer, value11 : String, value12 : String): CustomerRecord = {
    this.value1(value1)
    this.value2(value2)
    this.value3(value3)
    this.value4(value4)
    this.value5(value5)
    this.value6(value6)
    this.value7(value7)
    this.value8(value8)
    this.value9(value9)
    this.value10(value10)
    this.value11(value11)
    this.value12(value12)
    this
  }

  /**
   * Create a detached, initialised CustomerRecord
   */
  def this(customerId : Long, storeId : Long, firstName : String, lastName : String, email : String, addressId : Long, activebool : Boolean, createDate : LocalDate, lastUpdate : LocalDateTime, active : Integer, fullAddress : String, fullName : String) = {
    this()

    this.setCustomerId(customerId)
    this.setStoreId(storeId)
    this.setFirstName(firstName)
    this.setLastName(lastName)
    this.setEmail(email)
    this.setAddressId(addressId)
    this.setActivebool(activebool)
    this.setCreateDate(createDate)
    this.setLastUpdate(lastUpdate)
    this.setActive(active)
    this.setFullAddress(fullAddress)
    this.setFullName(fullName)
  }

  /**
   * Create a detached, initialised CustomerRecord
   */
  def this(customerId : Long, storeId : Long, firstName : String, lastName : String, email : String, addressId : Long, activebool : Boolean, createDate : LocalDate, active : Integer, fullAddress : String, fullName : String) = {
    this()

    this.setCustomerId(customerId)
    this.setStoreId(storeId)
    this.setFirstName(firstName)
    this.setLastName(lastName)
    this.setEmail(email)
    this.setAddressId(addressId)
    this.setActivebool(activebool)
    this.setCreateDate(createDate)
    this.setActive(active)
    this.setFullAddress(fullAddress)
    this.setFullName(fullName)
  }

  /**
   * Create a detached, initialised CustomerRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Customer) = {
    this()

    if (value != null) {
      this.setCustomerId(value.getCustomerId)
      this.setStoreId(value.getStoreId)
      this.setFirstName(value.getFirstName)
      this.setLastName(value.getLastName)
      this.setEmail(value.getEmail)
      this.setAddressId(value.getAddressId)
      this.setActivebool(value.getActivebool)
      this.setCreateDate(value.getCreateDate)
      this.setLastUpdate(value.getLastUpdate)
      this.setActive(value.getActive)
      this.setFullAddress(value.getFullAddress)
      this.setFullName(value.getFullName)
    }
  }
}
