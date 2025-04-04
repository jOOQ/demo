/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.routines


import java.lang.Boolean
import java.lang.Long

import org.jooq.Field
import org.jooq.Parameter
import org.jooq.demo.skala.db.Public
import org.jooq.impl.AbstractRoutine
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType


object InventoryInStock {

  /**
   * The parameter <code>public.inventory_in_stock.RETURN_VALUE</code>.
   */
  val RETURN_VALUE: Parameter[Boolean] = Internal.createParameter("RETURN_VALUE", SQLDataType.BOOLEAN, false, false)

  /**
   * The parameter <code>public.inventory_in_stock.p_inventory_id</code>.
   */
  val P_INVENTORY_ID: Parameter[Long] = Internal.createParameter("p_inventory_id", SQLDataType.BIGINT, false, false)
}

/**
 * This class is generated by jOOQ.
 */
class InventoryInStock extends AbstractRoutine[Boolean]("inventory_in_stock", Public.PUBLIC, DSL.comment(""), SQLDataType.BOOLEAN) {
  {
    setReturnParameter(InventoryInStock.RETURN_VALUE)
    addInParameter(InventoryInStock.P_INVENTORY_ID)
  }

  /**
   * Set the <code>p_inventory_id</code> parameter IN value to the routine
   */
  def setPInventoryId(value: Long) : Unit = setValue(InventoryInStock.P_INVENTORY_ID, value)

  /**
   * Set the <code>p_inventory_id</code> parameter to the function to be used
   * with a {@link org.jooq.Select} statement
   */
  def setPInventoryId(field: Field[Long]): Unit = {
    setField(InventoryInStock.P_INVENTORY_ID, field)
  }
}
