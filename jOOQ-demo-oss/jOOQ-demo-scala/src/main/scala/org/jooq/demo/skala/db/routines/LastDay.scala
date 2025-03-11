/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.routines


import java.time.LocalDate
import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.Parameter
import org.jooq.demo.skala.db.Public
import org.jooq.impl.AbstractRoutine
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType


object LastDay {

  /**
   * The parameter <code>public.last_day.RETURN_VALUE</code>.
   */
  val RETURN_VALUE: Parameter[LocalDate] = Internal.createParameter("RETURN_VALUE", SQLDataType.LOCALDATE, false, false)

  /**
   * The parameter <code>public.last_day._1</code>.
   */
  val _1: Parameter[LocalDateTime] = Internal.createParameter("_1", SQLDataType.LOCALDATETIME(6), false, true)
}

/**
 * This class is generated by jOOQ.
 */
class LastDay extends AbstractRoutine[LocalDate]("last_day", Public.PUBLIC, DSL.comment(""), SQLDataType.LOCALDATE) {
  {
    setReturnParameter(LastDay.RETURN_VALUE)
    addInParameter(LastDay._1)
  }

  /**
   * Set the <code>_1</code> parameter IN value to the routine
   */
  def set__1(value: LocalDateTime) : Unit = setValue(LastDay._1, value)

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  def set__1(field: Field[LocalDateTime]): Unit = {
    setField(LastDay._1, field)
  }
}
