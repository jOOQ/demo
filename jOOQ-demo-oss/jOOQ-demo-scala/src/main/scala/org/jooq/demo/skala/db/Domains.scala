/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db


import java.lang.Integer

import org.jooq.Domain
import org.jooq.Schema
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.LazySchema
import org.jooq.impl.SQLDataType


/**
 * Convenience access to all Domains in public.
 */
object Domains {

  /**
   * The domain <code>public.year</code>.
   */
  val YEAR: Domain[Integer] = Internal.createDomain(
      schema
    , DSL.name("year")
    , DSL.comment("")
    , SQLDataType.INTEGER
    , Internal.createCheck(null, null, "CHECK (((VALUE >= 1901) AND (VALUE <= 2155)))")
  )

  private def schema: Schema = new LazySchema(DSL.name("public"), DSL.comment(""), () => Public.PUBLIC)
}
