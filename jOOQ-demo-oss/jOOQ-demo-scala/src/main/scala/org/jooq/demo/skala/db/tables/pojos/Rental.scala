/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.pojos


import java.io.Serializable
import java.lang.Long
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
case class Rental(
  var rentalId: Long,
  var rentalDate: LocalDateTime,
  var inventoryId: Long,
  var customerId: Long,
  var returnDate: LocalDateTime,
  var staffId: Long,
  var lastUpdate: LocalDateTime
) extends Serializable {

  def this() = this(null, null, null, null, null, null, null)

  def this(value: Rental) = this(
    value.rentalId,
    value.rentalDate,
    value.inventoryId,
    value.customerId,
    value.returnDate,
    value.staffId,
    value.lastUpdate
  )

  /**
   * Getter for <code>public.rental.rental_id</code>.
   */
  def getRentalId: Long = this.rentalId

  /**
   * Setter for <code>public.rental.rental_id</code>.
   */
  def setRentalId(rentalId: Long): Unit = {
    this.rentalId = rentalId
  }

  /**
   * Getter for <code>public.rental.rental_date</code>.
   */
  def getRentalDate: LocalDateTime = this.rentalDate

  /**
   * Setter for <code>public.rental.rental_date</code>.
   */
  def setRentalDate(rentalDate: LocalDateTime): Unit = {
    this.rentalDate = rentalDate
  }

  /**
   * Getter for <code>public.rental.inventory_id</code>.
   */
  def getInventoryId: Long = this.inventoryId

  /**
   * Setter for <code>public.rental.inventory_id</code>.
   */
  def setInventoryId(inventoryId: Long): Unit = {
    this.inventoryId = inventoryId
  }

  /**
   * Getter for <code>public.rental.customer_id</code>.
   */
  def getCustomerId: Long = this.customerId

  /**
   * Setter for <code>public.rental.customer_id</code>.
   */
  def setCustomerId(customerId: Long): Unit = {
    this.customerId = customerId
  }

  /**
   * Getter for <code>public.rental.return_date</code>.
   */
  def getReturnDate: LocalDateTime = this.returnDate

  /**
   * Setter for <code>public.rental.return_date</code>.
   */
  def setReturnDate(returnDate: LocalDateTime): Unit = {
    this.returnDate = returnDate
  }

  /**
   * Getter for <code>public.rental.staff_id</code>.
   */
  def getStaffId: Long = this.staffId

  /**
   * Setter for <code>public.rental.staff_id</code>.
   */
  def setStaffId(staffId: Long): Unit = {
    this.staffId = staffId
  }

  /**
   * Getter for <code>public.rental.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = this.lastUpdate

  /**
   * Setter for <code>public.rental.last_update</code>.
   */
  def setLastUpdate(lastUpdate: LocalDateTime): Unit = {
    this.lastUpdate = lastUpdate
  }
}
