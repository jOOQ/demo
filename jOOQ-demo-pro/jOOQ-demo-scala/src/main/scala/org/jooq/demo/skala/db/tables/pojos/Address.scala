/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.pojos


import java.io.Serializable
import java.lang.Long
import java.lang.String
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
case class Address(
  var addressId: Long,
  var address: String,
  var address2: String,
  var district: String,
  var cityId: Long,
  var postalCode: String,
  var phone: String,
  var lastUpdate: LocalDateTime
) extends Serializable {

  def this() = this(null, null, null, null, null, null, null, null)

  def this(value: Address) = this(
    value.addressId,
    value.address,
    value.address2,
    value.district,
    value.cityId,
    value.postalCode,
    value.phone,
    value.lastUpdate
  )

  /**
   * Getter for <code>public.address.address_id</code>.
   */
  def getAddressId: Long = this.addressId

  /**
   * Setter for <code>public.address.address_id</code>.
   */
  def setAddressId(addressId: Long): Unit = {
    this.addressId = addressId
  }

  /**
   * Getter for <code>public.address.address</code>.
   */
  def getAddress: String = this.address

  /**
   * Setter for <code>public.address.address</code>.
   */
  def setAddress(address: String): Unit = {
    this.address = address
  }

  /**
   * Getter for <code>public.address.address2</code>.
   */
  def getAddress2: String = this.address2

  /**
   * Setter for <code>public.address.address2</code>.
   */
  def setAddress2(address2: String): Unit = {
    this.address2 = address2
  }

  /**
   * Getter for <code>public.address.district</code>.
   */
  def getDistrict: String = this.district

  /**
   * Setter for <code>public.address.district</code>.
   */
  def setDistrict(district: String): Unit = {
    this.district = district
  }

  /**
   * Getter for <code>public.address.city_id</code>.
   */
  def getCityId: Long = this.cityId

  /**
   * Setter for <code>public.address.city_id</code>.
   */
  def setCityId(cityId: Long): Unit = {
    this.cityId = cityId
  }

  /**
   * Getter for <code>public.address.postal_code</code>.
   */
  def getPostalCode: String = this.postalCode

  /**
   * Setter for <code>public.address.postal_code</code>.
   */
  def setPostalCode(postalCode: String): Unit = {
    this.postalCode = postalCode
  }

  /**
   * Getter for <code>public.address.phone</code>.
   */
  def getPhone: String = this.phone

  /**
   * Setter for <code>public.address.phone</code>.
   */
  def setPhone(phone: String): Unit = {
    this.phone = phone
  }

  /**
   * Getter for <code>public.address.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = this.lastUpdate

  /**
   * Setter for <code>public.address.last_update</code>.
   */
  def setLastUpdate(lastUpdate: LocalDateTime): Unit = {
    this.lastUpdate = lastUpdate
  }
}
