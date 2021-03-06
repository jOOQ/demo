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
import org.jooq.Row5
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.skala.db.Indexes
import org.jooq.demo.skala.db.Keys
import org.jooq.demo.skala.db.Public
import org.jooq.demo.skala.db.tables.records.StoreRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object Store {

  /**
   * The reference instance of <code>public.store</code>
   */
  val STORE = new Store
}

/**
 * This class is generated by jOOQ.
 */
class Store(
  alias: Name,
  child: Table[_ <: Record],
  path: ForeignKey[_ <: Record, StoreRecord],
  aliased: Table[StoreRecord],
  parameters: Array[ Field[_] ]
)
extends TableImpl[StoreRecord](
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
  override def getRecordType: Class[StoreRecord] = classOf[StoreRecord]

  /**
   * The column <code>public.store.store_id</code>.
   */
  val STORE_ID: TableField[StoreRecord, Long] = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false).identity(true), "")

  /**
   * The column <code>public.store.manager_staff_id</code>.
   */
  val MANAGER_STAFF_ID: TableField[StoreRecord, Long] = createField(DSL.name("manager_staff_id"), SQLDataType.BIGINT.nullable(false), "")

  /**
   * The column <code>public.store.address_id</code>.
   */
  val ADDRESS_ID: TableField[StoreRecord, Long] = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), "")

  /**
   * The column <code>public.store.last_update</code>.
   */
  val LAST_UPDATE: TableField[StoreRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), "")

  /**
   * The column <code>public.store.full_address</code>.
   */
  val FULL_ADDRESS: TableField[StoreRecord, String] = createField(DSL.name("full_address"), SQLDataType.CLOB, "")

  private def this(alias: Name, aliased: Table[StoreRecord]) = this(alias, null, null, aliased, null)

  /**
   * Create an aliased <code>public.store</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.Store.STORE)

  /**
   * Create an aliased <code>public.store</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.Store.STORE)

  /**
   * Create a <code>public.store</code> table reference
   */
  def this() = this(DSL.name("store"), null)

  def this(child: Table[_ <: Record], key: ForeignKey[_ <: Record, StoreRecord]) = this(Internal.createPathAlias(child, key), child, key, org.jooq.demo.skala.db.tables.Store.STORE, null)

  override def getSchema: Schema = if (aliased()) null else Public.PUBLIC

  override def getIndexes: List[Index] = Arrays.asList[ Index ](Indexes.IDX_UNQ_MANAGER_STAFF_ID)

  override def getIdentity: Identity[StoreRecord, Long] = super.getIdentity.asInstanceOf[ Identity[StoreRecord, Long] ]

  override def getPrimaryKey: UniqueKey[StoreRecord] = Keys.STORE_PKEY

  override def getReferences: List[ ForeignKey[StoreRecord, _] ] = Arrays.asList[ ForeignKey[StoreRecord, _] ](Keys.STORE__STORE_MANAGER_STAFF_ID_FKEY, Keys.STORE__STORE_ADDRESS_ID_FKEY)

  /**
   * Get the implicit join path to the <code>public.staff</code> table.
   */
  lazy val staff: Staff = { new Staff(this, Keys.STORE__STORE_MANAGER_STAFF_ID_FKEY) }

  /**
   * Get the implicit join path to the <code>public.address</code> table.
   */
  lazy val address: Address = { new Address(this, Keys.STORE__STORE_ADDRESS_ID_FKEY) }
  override def as(alias: String): Store = new Store(DSL.name(alias), this)
  override def as(alias: Name): Store = new Store(alias, this)
  override def as(alias: Table[_]): Store = new Store(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): Store = new Store(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): Store = new Store(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[_]): Store = new Store(name.getQualifiedName(), null)

  // -------------------------------------------------------------------------
  // Row5 type methods
  // -------------------------------------------------------------------------
  override def fieldsRow: Row5[Long, Long, Long, LocalDateTime, String] = super.fieldsRow.asInstanceOf[ Row5[Long, Long, Long, LocalDateTime, String] ]

  /**
   * Convenience mapping calling {@link #convertFrom(Function)}.
   */
  def mapping[U](from: (Long, Long, Long, LocalDateTime, String) => U): SelectField[U] = convertFrom(r => from.apply(r.value1(), r.value2(), r.value3(), r.value4(), r.value5()))

  /**
   * Convenience mapping calling {@link #convertFrom(Class, Function)}.
   */
  def mapping[U](toType: Class[U], from: (Long, Long, Long, LocalDateTime, String) => U): SelectField[U] = convertFrom(toType,r => from.apply(r.value1(), r.value2(), r.value3(), r.value4(), r.value5()))
}
