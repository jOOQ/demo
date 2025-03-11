/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.skala.db.tables.Rental
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class RentalRecord extends UpdatableRecordImpl[RentalRecord](Rental.RENTAL) {

  /**
   * Setter for <code>public.rental.rental_id</code>.
   */
  def setRentalId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.rental.rental_id</code>.
   */
  def getRentalId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.rental.rental_date</code>.
   */
  def setRentalDate(value: LocalDateTime): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.rental.rental_date</code>.
   */
  def getRentalDate: LocalDateTime = get(1).asInstanceOf[LocalDateTime]

  /**
   * Setter for <code>public.rental.inventory_id</code>.
   */
  def setInventoryId(value: Long): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.rental.inventory_id</code>.
   */
  def getInventoryId: Long = get(2).asInstanceOf[Long]

  /**
   * Setter for <code>public.rental.customer_id</code>.
   */
  def setCustomerId(value: Long): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.rental.customer_id</code>.
   */
  def getCustomerId: Long = get(3).asInstanceOf[Long]

  /**
   * Setter for <code>public.rental.return_date</code>.
   */
  def setReturnDate(value: LocalDateTime): Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.rental.return_date</code>.
   */
  def getReturnDate: LocalDateTime = get(4).asInstanceOf[LocalDateTime]

  /**
   * Setter for <code>public.rental.staff_id</code>.
   */
  def setStaffId(value: Long): Unit = {
    set(5, value)
  }

  /**
   * Getter for <code>public.rental.staff_id</code>.
   */
  def getStaffId: Long = get(5).asInstanceOf[Long]

  /**
   * Setter for <code>public.rental.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(6, value)
  }

  /**
   * Getter for <code>public.rental.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(6).asInstanceOf[LocalDateTime]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  /**
   * Create a detached, initialised RentalRecord
   */
  def this(rentalId : Long, rentalDate : LocalDateTime, inventoryId : Long, customerId : Long, returnDate : LocalDateTime, staffId : Long, lastUpdate : LocalDateTime) = {
    this()

    this.setRentalId(rentalId)
    this.setRentalDate(rentalDate)
    this.setInventoryId(inventoryId)
    this.setCustomerId(customerId)
    this.setReturnDate(returnDate)
    this.setStaffId(staffId)
    this.setLastUpdate(lastUpdate)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised RentalRecord
   */
  def this(rentalId : Long, rentalDate : LocalDateTime, inventoryId : Long, customerId : Long, returnDate : LocalDateTime, staffId : Long) = {
    this()

    this.setRentalId(rentalId)
    this.setRentalDate(rentalDate)
    this.setInventoryId(inventoryId)
    this.setCustomerId(customerId)
    this.setReturnDate(returnDate)
    this.setStaffId(staffId)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised RentalRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Rental) = {
    this()

    if (value != null) {
      this.setRentalId(value.getRentalId)
      this.setRentalDate(value.getRentalDate)
      this.setInventoryId(value.getInventoryId)
      this.setCustomerId(value.getCustomerId)
      this.setReturnDate(value.getReturnDate)
      this.setStaffId(value.getStaffId)
      this.setLastUpdate(value.getLastUpdate)
      resetTouchedOnNotNull()
    }
  }
}
