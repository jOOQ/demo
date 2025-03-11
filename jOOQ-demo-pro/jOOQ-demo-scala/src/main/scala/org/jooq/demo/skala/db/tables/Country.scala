/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables


import java.lang.Boolean
import java.lang.Class
import java.lang.Long
import java.lang.String
import java.time.LocalDateTime
import java.util.Collection

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.skala.db.Keys
import org.jooq.demo.skala.db.Public
import org.jooq.demo.skala.db.tables.City.CityPath
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

  /**
   * A subtype implementing {@link Path} for simplified path-based joins.
   */
  class CountryPath(path: Table[? <: Record], childPath: ForeignKey[? <: Record, CountryRecord], parentPath: InverseForeignKey[? <: Record, CountryRecord]) extends Country(path, childPath, parentPath) with Path[CountryRecord]
}

/**
 * This class is generated by jOOQ.
 */
class Country(
  alias: Name,
  path: Table[? <: Record],
  childPath: ForeignKey[? <: Record, CountryRecord],
  parentPath: InverseForeignKey[? <: Record, CountryRecord],
  aliased: Table[CountryRecord],
  parameters: Array[ Field[?] ],
  where: Condition
)
extends TableImpl[CountryRecord](
  alias,
  Public.PUBLIC,
  path,
  childPath,
  parentPath,
  aliased,
  parameters,
  DSL.comment(""),
  TableOptions.table,
  where
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
  val LAST_UPDATE: TableField[CountryRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[CountryRecord]) = this(alias, null, null, null, aliased, null, null)
  private def this(alias: Name, aliased: Table[CountryRecord], where: Condition) = this(alias, null, null, null, aliased, null, where)

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

  def this(path: Table[? <: Record], childPath: ForeignKey[? <: Record, CountryRecord], parentPath: InverseForeignKey[? <: Record, CountryRecord]) = this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, org.jooq.demo.skala.db.tables.Country.COUNTRY, null, null)

  override def getSchema: Schema = if (super.aliased()) null else Public.PUBLIC

  override def getIdentity: Identity[CountryRecord, Long] = super.getIdentity.asInstanceOf[ Identity[CountryRecord, Long] ]

  override def getPrimaryKey: UniqueKey[CountryRecord] = Keys.COUNTRY_PKEY

  /**
   * Get the implicit to-many join path to the <code>public.city</code> table
   */
  lazy val city: CityPath = { new CityPath(this, null, Keys.CITY__CITY_COUNTRY_ID_FKEY.getInverseKey()) }
  override def as(alias: String): Country = new Country(DSL.name(alias), this)
  override def as(alias: Name): Country = new Country(alias, this)
  override def as(alias: Table[?]): Country = new Country(alias.getQualifiedName(), this)

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
  override def rename(name: Table[?]): Country = new Country(name.getQualifiedName(), null)

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Condition): Country = new Country(getQualifiedName(), if (super.aliased()) this else null, condition)

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Collection[? <: Condition]): Country = where(DSL.and(conditions))

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Condition*): Country = where(DSL.and(conditions*))

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Field[Boolean]): Country = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(condition: SQL): Country = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String): Country = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String, binds: AnyRef*): Country = where(DSL.condition(condition, binds*))

  /**
   * Create an inline derived table from this table
   */
  override def whereExists(select: Select[?]): Country = where(DSL.exists(select))

  /**
   * Create an inline derived table from this table
   */
  override def whereNotExists(select: Select[?]): Country = where(DSL.notExists(select))
}
