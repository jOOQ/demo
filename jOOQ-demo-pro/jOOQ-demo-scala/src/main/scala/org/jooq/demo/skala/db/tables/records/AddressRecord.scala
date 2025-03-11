/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.lang.String
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.skala.db.tables.Address
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class AddressRecord extends UpdatableRecordImpl[AddressRecord](Address.ADDRESS) {

  /**
   * Setter for <code>public.address.address_id</code>.
   */
  def setAddressId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.address.address_id</code>.
   */
  def getAddressId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.address.address</code>.
   */
  def setAddress(value: String): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.address.address</code>.
   */
  def getAddress: String = get(1).asInstanceOf[String]

  /**
   * Setter for <code>public.address.address2</code>.
   */
  def setAddress2(value: String): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.address.address2</code>.
   */
  def getAddress2: String = get(2).asInstanceOf[String]

  /**
   * Setter for <code>public.address.district</code>.
   */
  def setDistrict(value: String): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.address.district</code>.
   */
  def getDistrict: String = get(3).asInstanceOf[String]

  /**
   * Setter for <code>public.address.city_id</code>.
   */
  def setCityId(value: Long): Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.address.city_id</code>.
   */
  def getCityId: Long = get(4).asInstanceOf[Long]

  /**
   * Setter for <code>public.address.postal_code</code>.
   */
  def setPostalCode(value: String): Unit = {
    set(5, value)
  }

  /**
   * Getter for <code>public.address.postal_code</code>.
   */
  def getPostalCode: String = get(5).asInstanceOf[String]

  /**
   * Setter for <code>public.address.phone</code>.
   */
  def setPhone(value: String): Unit = {
    set(6, value)
  }

  /**
   * Getter for <code>public.address.phone</code>.
   */
  def getPhone: String = get(6).asInstanceOf[String]

  /**
   * Setter for <code>public.address.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(7, value)
  }

  /**
   * Getter for <code>public.address.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(7).asInstanceOf[LocalDateTime]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  /**
   * Create a detached, initialised AddressRecord
   */
  def this(addressId : Long, address : String, address2 : String, district : String, cityId : Long, postalCode : String, phone : String, lastUpdate : LocalDateTime) = {
    this()

    this.setAddressId(addressId)
    this.setAddress(address)
    this.setAddress2(address2)
    this.setDistrict(district)
    this.setCityId(cityId)
    this.setPostalCode(postalCode)
    this.setPhone(phone)
    this.setLastUpdate(lastUpdate)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised AddressRecord
   */
  def this(addressId : Long, address : String, address2 : String, district : String, cityId : Long, postalCode : String, phone : String) = {
    this()

    this.setAddressId(addressId)
    this.setAddress(address)
    this.setAddress2(address2)
    this.setDistrict(district)
    this.setCityId(cityId)
    this.setPostalCode(postalCode)
    this.setPhone(phone)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised AddressRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Address) = {
    this()

    if (value != null) {
      this.setAddressId(value.getAddressId)
      this.setAddress(value.getAddress)
      this.setAddress2(value.getAddress2)
      this.setDistrict(value.getDistrict)
      this.setCityId(value.getCityId)
      this.setPostalCode(value.getPostalCode)
      this.setPhone(value.getPhone)
      this.setLastUpdate(value.getLastUpdate)
      resetTouchedOnNotNull()
    }
  }
}
