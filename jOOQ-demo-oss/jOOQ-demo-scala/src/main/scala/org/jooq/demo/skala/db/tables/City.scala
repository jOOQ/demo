/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables


import java.lang.Class
import java.lang.Long
import java.lang.String
import java.time.LocalDateTime
import java.util.Arrays
import java.util.List
import java.util.function.Function

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Index
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row4
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.skala.db.Indexes
import org.jooq.demo.skala.db.Keys
import org.jooq.demo.skala.db.Public
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
}

/**
 * This class is generated by jOOQ.
 */
class City(
  alias: Name,
  child: Table[_ <: Record],
  path: ForeignKey[_ <: Record, CityRecord],
  aliased: Table[CityRecord],
  parameters: Array[ Field[_] ]
)
extends TableImpl[CityRecord](
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
  val LAST_UPDATE: TableField[CityRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[CityRecord]) = this(alias, null, null, aliased, null)

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

  def this(child: Table[_ <: Record], key: ForeignKey[_ <: Record, CityRecord]) = this(Internal.createPathAlias(child, key), child, key, org.jooq.demo.skala.db.tables.City.CITY, null)

  override def getSchema: Schema = if (aliased()) null else Public.PUBLIC

  override def getIndexes: List[Index] = Arrays.asList[ Index ](Indexes.IDX_FK_COUNTRY_ID)

  override def getIdentity: Identity[CityRecord, Long] = super.getIdentity.asInstanceOf[ Identity[CityRecord, Long] ]

  override def getPrimaryKey: UniqueKey[CityRecord] = Keys.CITY_PKEY

  override def getReferences: List[ ForeignKey[CityRecord, _] ] = Arrays.asList[ ForeignKey[CityRecord, _] ](Keys.CITY__CITY_COUNTRY_ID_FKEY)

  /**
   * Get the implicit join path to the <code>public.country</code> table.
   */
  lazy val country: Country = { new Country(this, Keys.CITY__CITY_COUNTRY_ID_FKEY) }
  override def as(alias: String): City = new City(DSL.name(alias), this)
  override def as(alias: Name): City = new City(alias, this)
  override def as(alias: Table[_]): City = new City(alias.getQualifiedName(), this)

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
  override def rename(name: Table[_]): City = new City(name.getQualifiedName(), null)

  // -------------------------------------------------------------------------
  // Row4 type methods
  // -------------------------------------------------------------------------
  override def fieldsRow: Row4[Long, String, Long, LocalDateTime] = super.fieldsRow.asInstanceOf[ Row4[Long, String, Long, LocalDateTime] ]

  /**
   * Convenience mapping calling {@link #convertFrom(Function)}.
   */
  def mapping[U](from: (Long, String, Long, LocalDateTime) => U): SelectField[U] = convertFrom(r => from.apply(r.value1(), r.value2(), r.value3(), r.value4()))

  /**
   * Convenience mapping calling {@link #convertFrom(Class, Function)}.
   */
  def mapping[U](toType: Class[U], from: (Long, String, Long, LocalDateTime) => U): SelectField[U] = convertFrom(toType,r => from.apply(r.value1(), r.value2(), r.value3(), r.value4()))
}
