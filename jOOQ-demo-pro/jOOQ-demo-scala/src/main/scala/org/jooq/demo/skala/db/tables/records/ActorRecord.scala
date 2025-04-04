/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables.records


import java.lang.Long
import java.lang.String
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.skala.db.tables.Actor
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
class ActorRecord extends UpdatableRecordImpl[ActorRecord](Actor.ACTOR) {

  /**
   * Setter for <code>public.actor.actor_id</code>.
   */
  def setActorId(value: Long): Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.actor.actor_id</code>.
   */
  def getActorId: Long = get(0).asInstanceOf[Long]

  /**
   * Setter for <code>public.actor.first_name</code>.
   */
  def setFirstName(value: String): Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.actor.first_name</code>.
   */
  def getFirstName: String = get(1).asInstanceOf[String]

  /**
   * Setter for <code>public.actor.last_name</code>.
   */
  def setLastName(value: String): Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.actor.last_name</code>.
   */
  def getLastName: String = get(2).asInstanceOf[String]

  /**
   * Setter for <code>public.actor.last_update</code>.
   */
  def setLastUpdate(value: LocalDateTime): Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.actor.last_update</code>.
   */
  def getLastUpdate: LocalDateTime = get(3).asInstanceOf[LocalDateTime]

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  override def key: Record1[Long] = super.key.asInstanceOf[ Record1[Long] ]

  /**
   * Create a detached, initialised ActorRecord
   */
  def this(actorId : Long, firstName : String, lastName : String, lastUpdate : LocalDateTime) = {
    this()

    this.setActorId(actorId)
    this.setFirstName(firstName)
    this.setLastName(lastName)
    this.setLastUpdate(lastUpdate)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised ActorRecord
   */
  def this(actorId : Long, firstName : String, lastName : String) = {
    this()

    this.setActorId(actorId)
    this.setFirstName(firstName)
    this.setLastName(lastName)
    resetTouchedOnNotNull()
  }

  /**
   * Create a detached, initialised ActorRecord
   */
  def this(value: org.jooq.demo.skala.db.tables.pojos.Actor) = {
    this()

    if (value != null) {
      this.setActorId(value.getActorId)
      this.setFirstName(value.getFirstName)
      this.setLastName(value.getLastName)
      this.setLastUpdate(value.getLastUpdate)
      resetTouchedOnNotNull()
    }
  }
}
