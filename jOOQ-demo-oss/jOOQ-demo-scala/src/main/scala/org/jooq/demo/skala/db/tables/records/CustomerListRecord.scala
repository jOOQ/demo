/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.lang.String

import org.jooq.demo.skala.db.tables.CustomerList
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class CustomerListRecord extends TableRecordImpl[CustomerListRecord](CustomerList.CUSTOMER_LIST) {

  /**
   * Setter for <code>public.customer_list.id</code>.
   */
  def setId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.customer_list.id</code>.
   */
  def getId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.customer_list.name</code>.
   */
  def setName(value: String): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.customer_list.name</code>.
   */
  def getName: String = get(1).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.address</code>.
   */
  def setAddress(value: String): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.customer_list.address</code>.
   */
  def getAddress: String = get(2).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.zip code</code>.
   */
  def setZipCode(value: String): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.customer_list.zip code</code>.
   */
  def getZipCode: String = get(3).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.phone</code>.
   */
  def setPhone(value: String): Unit = {
    set(4, value)
  }

  /**
   * Getter for <code>public.customer_list.phone</code>.
   */
  def getPhone: String = get(4).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.city</code>.
   */
  def setCity(value: String): Unit = {
    set(5, value)
  }

  /**
   * Getter for <code>public.customer_list.city</code>.
   */
  def getCity: String = get(5).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.country</code>.
   */
  def setCountry(value: String): Unit = {
    set(6, value)
  }

  /**
   * Getter for <code>public.customer_list.country</code>.
   */
  def getCountry: String = get(6).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.notes</code>.
   */
  def setNotes(value: String): Unit = {
    set(7, value)
  }

  /**
   * Getter for <code>public.customer_list.notes</code>.
   */
  def getNotes: String = get(7).asInstanceOf[String]

  /**
   * Setter for <code>public.customer_list.sid</code>.
   */
  def setSid(value: Long): Unit = {
    set(8, value)
  }

  /**
   * Getter for <code>public.customer_list.sid</code>.
   */
  def getSid: Long = get(8).asInstanceOf[Long]

  /**
   * Create a detached, initialised CustomerListRecord
   */
  def this(id : Long, name : String, address : String, zipCode : String, phone : String, city : String, country : String, notes : String, sid : Long) = {
    this()

    this.setId(id)
    this.setName(name)
    this.setAddress(address)
    this.setZipCode(zipCode)
    this.setPhone(phone)
    this.setCity(city)
    this.setCountry(country)
    this.setNotes(notes)
    this.setSid(sid)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised CustomerListRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.CustomerList) = {
    this()

    if (value != null) {
      this.setId(value.getId)
      this.setName(value.getName)
      this.setAddress(value.getAddress)
      this.setZipCode(value.getZipCode)
      this.setPhone(value.getPhone)
      this.setCity(value.getCity)
      this.setCountry(value.getCountry)
      this.setNotes(value.getNotes)
      this.setSid(value.getSid)
      resetTouchedOnNotNull()
    }
  }
}
