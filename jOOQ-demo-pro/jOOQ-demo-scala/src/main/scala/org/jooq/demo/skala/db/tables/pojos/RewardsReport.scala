/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.pojos


import java.io.Serializable
import java.lang.Boolean
import java.lang.Integer
import java.lang.Long
import java.lang.String
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
case class RewardsReport(
  var customerId: Long,
  var storeId: Long,
  var firstName: String,
  var lastName: String,
  var email: String,
  var addressId: Long,
  var activebool: Boolean,
  var createDate: LocalDate,
  var lastUpdate: LocalDateTime,
  var active: Integer
) extends Serializable {

  def this() = this(null, null, null, null, null, null, null, null, null, null)

  def this(value: RewardsReport) = this(
    value.customerId,
    value.storeId,
    value.firstName,
    value.lastName,
    value.email,
    value.addressId,
    value.activebool,
    value.createDate,
    value.lastUpdate,
    value.active
  )

  /**
   * Getter for <code>public.rewards_report.customer_id</code>.
   */
  def getCustomerId: Long = this.customerId

  /**
   * Setter for <code>public.rewards_report.customer_id</code>.
   */
  def setCustomerId(customerId: Long): Unit = {
    this.customerId = customerId
  }

  /**
   * Getter for <code>public.rewards_report.store_id</code>.
   */
  def getStoreId: Long = this.storeId

  /**
   * Setter for <code>public.rewards_report.store_id</code>.
   */
  def setStoreId(storeId: Long): Unit = {
    this.storeId = storeId
  }

  /**
   * Getter for <code>public.rewards_report.first_name</code>.
   */
  def getFirstName: String = this.firstName

  /**
   * Setter for <code>public.rewards_report.first_name</code>.
   */
  def setFirstName(firstName: String): Unit = {
    this.firstName = firstName
  }

  /**
   * Getter for <code>public.rewards_report.last_name</code>.
   */
  def getLastName: String = this.lastName

  /**
   * Setter for <code>public.rewards_report.last_name</code>.
   */
  def setLastName(lastName: String): Unit = {
    this.lastName = lastName
  }

  /**
   * Getter for <code>public.rewards_report.email</code>.
   */
  def getEmail: String = this.email

  /**
   * Setter for <code>public.rewards_report.email</code>.
   */
  def setEmail(email: String): Unit = {
    this.email = email
  }

  /**
   * Getter for <code>public.rewards_report.address_id</code>.
   */
  def getAddressId: Long = this.addressId

  /**
   * Setter for <code>public.rewards_report.address_id</code>.
   */
  def setAddressId(addressId: Long): Unit = {
    this.addressId = addressId
  }

  /**
   * Getter for <code>public.rewards_report.activebool</code>.
   */
  def getActivebool: Boolean = this.activebool

  /**
   * Setter for <code>public.rewards_report.activebool</code>.
   */
  def setActivebool(activebool: Boolean): Unit = {
    this.activebool = activebool
  }

  /**
   * Getter for <code>public.rewards_report.create_date</code>.
   */
  def getCreateDate: LocalDate = this.createDate

  /**
   * Setter for <code>public.rewards_report.create_date</code>.
   */
  def setCreateDate(createDate: LocalDate): Unit = {
    this.createDate = createDate
  }

  /**
   * Getter for <code>public.rewards_report.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = this.lastUpdate

  /**
   * Setter for <code>public.rewards_report.last_update</code>.
   */
  def setLastUpdate(lastUpdate: LocalDateTime): Unit = {
    this.lastUpdate = lastUpdate
  }

  /**
   * Getter for <code>public.rewards_report.active</code>.
   */
  def getActive: Integer = this.active

  /**
   * Setter for <code>public.rewards_report.active</code>.
   */
  def setActive(active: Integer): Unit = {
    this.active = active
  }
}
