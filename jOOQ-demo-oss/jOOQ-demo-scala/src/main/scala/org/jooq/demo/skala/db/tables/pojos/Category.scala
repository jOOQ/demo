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
case class Category(
  var categoryId: Long,
  var name: String,
  var lastUpdate: LocalDateTime
) extends Serializable {

  def this() = this(null, null, null)

  def this(value: Category) = this(
    value.categoryId,
    value.name,
    value.lastUpdate
  )

  /**
   * Getter for <code>public.category.category_id</code>.
   */
  def getCategoryId: Long = this.categoryId

  /**
   * Setter for <code>public.category.category_id</code>.
   */
  def setCategoryId(categoryId: Long): Unit = {
    this.categoryId = categoryId
  }

  /**
   * Getter for <code>public.category.name</code>.
   */
  def getName: String = this.name

  /**
   * Setter for <code>public.category.name</code>.
   */
  def setName(name: String): Unit = {
    this.name = name
  }

  /**
   * Getter for <code>public.category.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = this.lastUpdate

  /**
   * Setter for <code>public.category.last_update</code>.
   */
  def setLastUpdate(lastUpdate: LocalDateTime): Unit = {
    this.lastUpdate = lastUpdate
  }
}
