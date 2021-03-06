/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables


import java.lang.Class
import java.lang.Long
import java.lang.String
import java.time.LocalDateTime
import java.util.function.Function

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row3
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.skala.db.Keys
import org.jooq.demo.skala.db.Public
import org.jooq.demo.skala.db.tables.records.CountryRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object Country {

  /**
   * The reference instance of <code>public.country</code>
   */
  val COUNTRY = new Country
}

/**
 * This class is generated by jOOQ.
 */
class Country(
  alias: Name,
  child: Table[_ <: Record],
  path: ForeignKey[_ <: Record, CountryRecord],
  aliased: Table[CountryRecord],
  parameters: Array[ Field[_] ]
)
extends TableImpl[CountryRecord](
  alias,
  Public.PUBLIC,
  child,
  path,
  aliased,
  parameters,
  DSL.comment(""),
  TableOptions.table
) {

  /**
   * The class holding records for this type
   */
  override def getRecordType: Class[CountryRecord] = classOf[CountryRecord]

  /**
   * The column <code>public.country.country_id</code>.
   */
  val COUNTRY_ID: TableField[CountryRecord, Long] = createField(DSL.name("country_id"), SQLDataType.BIGINT.nullable(false).identity(true), "")

  /**
   * The column <code>public.country.country</code>.
   */
  val COUNTRY_ : TableField[CountryRecord, String] = createField(DSL.name("country"), SQLDataType.VARCHAR(50).nullable(false), "")

  /**
   * The column <code>public.country.last_update</code>.
   */
  val LAST_UPDATE: TableField[CountryRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[CountryRecord]) = this(alias, null, null, aliased, null)

  /**
   * Create an aliased <code>public.country</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.Country.COUNTRY)

  /**
   * Create an aliased <code>public.country</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.Country.COUNTRY)

  /**
   * Create a <code>public.country</code> table reference
   */
  def this() = this(DSL.name("country"), null)

  def this(child: Table[_ <: Record], key: ForeignKey[_ <: Record, CountryRecord]) = this(Internal.createPathAlias(child, key), child, key, org.jooq.demo.skala.db.tables.Country.COUNTRY, null)

  override def getSchema: Schema = if (aliased()) null else Public.PUBLIC

  override def getIdentity: Identity[CountryRecord, Long] = super.getIdentity.asInstanceOf[ Identity[CountryRecord, Long] ]

  override def getPrimaryKey: UniqueKey[CountryRecord] = Keys.COUNTRY_PKEY
  override def as(alias: String): Country = new Country(DSL.name(alias), this)
  override def as(alias: Name): Country = new Country(alias, this)
  override def as(alias: Table[_]): Country = new Country(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): Country = new Country(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): Country = new Country(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[_]): Country = new Country(name.getQualifiedName(), null)

  // -------------------------------------------------------------------------
  // Row3 type methods
  // -------------------------------------------------------------------------
  override def fieldsRow: Row3[Long, String, LocalDateTime] = super.fieldsRow.asInstanceOf[ Row3[Long, String, LocalDateTime] ]

  /**
   * Convenience mapping calling {@link #convertFrom(Function)}.
   */
  def mapping[U](from: (Long, String, LocalDateTime) => U): SelectField[U] = convertFrom(r => from.apply(r.value1(), r.value2(), r.value3()))

  /**
   * Convenience mapping calling {@link #convertFrom(Class, Function)}.
   */
  def mapping[U](toType: Class[U], from: (Long, String, LocalDateTime) => U): SelectField[U] = convertFrom(toType,r => from.apply(r.value1(), r.value2(), r.value3()))
}
