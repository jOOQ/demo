/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables


import java.lang.Boolean
import java.lang.Class
import java.lang.Long
import java.lang.String
import java.time.LocalDateTime
import java.util.Arrays
import java.util.Collection
import java.util.List

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Index
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
import org.jooq.demo.skala.db.Indexes
import org.jooq.demo.skala.db.Keys
import org.jooq.demo.skala.db.Public
import org.jooq.demo.skala.db.tables.Address.AddressPath
import org.jooq.demo.skala.db.tables.Country.CountryPath
import org.jooq.demo.skala.db.tables.records.CityRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object City {

  /**
   * The reference instance of <code>public.city</code>
   */
  val CITY = new City

  /**
   * A subtype implementing {@link Path} for simplified path-based joins.
   */
  class CityPath(path: Table[? <: Record], childPath: ForeignKey[? <: Record, CityRecord], parentPath: InverseForeignKey[? <: Record, CityRecord]) extends City(path, childPath, parentPath) with Path[CityRecord]
}

/**
 * This class is generated by jOOQ.
 */
class City(
  alias: Name,
  path: Table[? <: Record],
  childPath: ForeignKey[? <: Record, CityRecord],
  parentPath: InverseForeignKey[? <: Record, CityRecord],
  aliased: Table[CityRecord],
  parameters: Array[ Field[?] ],
  where: Condition
)
extends TableImpl[CityRecord](
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
  override def getRecordType: Class[CityRecord] = classOf[CityRecord]

  /**
   * The column <code>public.city.city_id</code>.
   */
  val CITY_ID: TableField[CityRecord, Long] = createField(DSL.name("city_id"), SQLDataType.BIGINT.nullable(false).identity(true), "")

  /**
   * The column <code>public.city.city</code>.
   */
  val CITY_ : TableField[CityRecord, String] = createField(DSL.name("city"), SQLDataType.VARCHAR(50).nullable(false), "")

  /**
   * The column <code>public.city.country_id</code>.
   */
  val COUNTRY_ID: TableField[CityRecord, Long] = createField(DSL.name("country_id"), SQLDataType.BIGINT.nullable(false), "")

  /**
   * The column <code>public.city.last_update</code>.
   */
  val LAST_UPDATE: TableField[CityRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[CityRecord]) = this(alias, null, null, null, aliased, null, null)
  private def this(alias: Name, aliased: Table[CityRecord], where: Condition) = this(alias, null, null, null, aliased, null, where)

  /**
   * Create an aliased <code>public.city</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.City.CITY)

  /**
   * Create an aliased <code>public.city</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.City.CITY)

  /**
   * Create a <code>public.city</code> table reference
   */
  def this() = this(DSL.name("city"), null)

  def this(path: Table[? <: Record], childPath: ForeignKey[? <: Record, CityRecord], parentPath: InverseForeignKey[? <: Record, CityRecord]) = this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, org.jooq.demo.skala.db.tables.City.CITY, null, null)

  override def getSchema: Schema = if (super.aliased()) null else Public.PUBLIC

  override def getIndexes: List[Index] = Arrays.asList[ Index ](Indexes.IDX_FK_COUNTRY_ID)

  override def getIdentity: Identity[CityRecord, Long] = super.getIdentity.asInstanceOf[ Identity[CityRecord, Long] ]

  override def getPrimaryKey: UniqueKey[CityRecord] = Keys.CITY_PKEY

  override def getReferences: List[ ForeignKey[CityRecord, ?] ] = Arrays.asList[ ForeignKey[CityRecord, ?] ](Keys.CITY__CITY_COUNTRY_ID_FKEY)

  /**
   * Get the implicit join path to the <code>public.country</code> table.
   */
  lazy val country: CountryPath = { new CountryPath(this, Keys.CITY__CITY_COUNTRY_ID_FKEY, null) }

  /**
   * Get the implicit to-many join path to the <code>public.address</code> table
   */
  lazy val address: AddressPath = { new AddressPath(this, null, Keys.ADDRESS__ADDRESS_CITY_ID_FKEY.getInverseKey()) }
  override def as(alias: String): City = new City(DSL.name(alias), this)
  override def as(alias: Name): City = new City(alias, this)
  override def as(alias: Table[?]): City = new City(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): City = new City(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): City = new City(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[?]): City = new City(name.getQualifiedName(), null)

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Condition): City = new City(getQualifiedName(), if (super.aliased()) this else null, condition)

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Collection[? <: Condition]): City = where(DSL.and(conditions))

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Condition*): City = where(DSL.and(conditions*))

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Field[Boolean]): City = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(condition: SQL): City = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String): City = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String, binds: AnyRef*): City = where(DSL.condition(condition, binds*))

  /**
   * Create an inline derived table from this table
   */
  override def whereExists(select: Select[?]): City = where(DSL.exists(select))

  /**
   * Create an inline derived table from this table
   */
  override def whereNotExists(select: Select[?]): City = where(DSL.notExists(select))
}
