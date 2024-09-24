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
import org.jooq.demo.skala.db.tables.Customer.CustomerPath
import org.jooq.demo.skala.db.tables.Inventory.InventoryPath
import org.jooq.demo.skala.db.tables.Payment.PaymentPath
import org.jooq.demo.skala.db.tables.PaymentP2007_01.PaymentP2007_01Path
import org.jooq.demo.skala.db.tables.PaymentP2007_02.PaymentP2007_02Path
import org.jooq.demo.skala.db.tables.PaymentP2007_03.PaymentP2007_03Path
import org.jooq.demo.skala.db.tables.PaymentP2007_04.PaymentP2007_04Path
import org.jooq.demo.skala.db.tables.PaymentP2007_05.PaymentP2007_05Path
import org.jooq.demo.skala.db.tables.PaymentP2007_06.PaymentP2007_06Path
import org.jooq.demo.skala.db.tables.Staff.StaffPath
import org.jooq.demo.skala.db.tables.records.RentalRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object Rental {

  /**
   * The reference instance of <code>public.rental</code>
   */
  val RENTAL = new Rental

  /**
   * A subtype implementing {@link Path} for simplified path-based joins.
   */
  class RentalPath(path: Table[_ <: Record], childPath: ForeignKey[_ <: Record, RentalRecord], parentPath: InverseForeignKey[_ <: Record, RentalRecord]) extends Rental(path, childPath, parentPath) with Path[RentalRecord]
}

/**
 * This class is generated by jOOQ.
 */
class Rental(
  alias: Name,
  path: Table[_ <: Record],
  childPath: ForeignKey[_ <: Record, RentalRecord],
  parentPath: InverseForeignKey[_ <: Record, RentalRecord],
  aliased: Table[RentalRecord],
  parameters: Array[ Field[_] ],
  where: Condition
)
extends TableImpl[RentalRecord](
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
  override def getRecordType: Class[RentalRecord] = classOf[RentalRecord]

  /**
   * The column <code>public.rental.rental_id</code>.
   */
  val RENTAL_ID: TableField[RentalRecord, Long] = createField(DSL.name("rental_id"), SQLDataType.BIGINT.nullable(false).identity(true), "")

  /**
   * The column <code>public.rental.rental_date</code>.
   */
  val RENTAL_DATE: TableField[RentalRecord, LocalDateTime] = createField(DSL.name("rental_date"), SQLDataType.LOCALDATETIME(6).nullable(false), "")

  /**
   * The column <code>public.rental.inventory_id</code>.
   */
  val INVENTORY_ID: TableField[RentalRecord, Long] = createField(DSL.name("inventory_id"), SQLDataType.BIGINT.nullable(false), "")

  /**
   * The column <code>public.rental.customer_id</code>.
   */
  val CUSTOMER_ID: TableField[RentalRecord, Long] = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false), "")

  /**
   * The column <code>public.rental.return_date</code>.
   */
  val RETURN_DATE: TableField[RentalRecord, LocalDateTime] = createField(DSL.name("return_date"), SQLDataType.LOCALDATETIME(6), "")

  /**
   * The column <code>public.rental.staff_id</code>.
   */
  val STAFF_ID: TableField[RentalRecord, Long] = createField(DSL.name("staff_id"), SQLDataType.BIGINT.nullable(false), "")

  /**
   * The column <code>public.rental.last_update</code>.
   */
  val LAST_UPDATE: TableField[RentalRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[RentalRecord]) = this(alias, null, null, null, aliased, null, null)
  private def this(alias: Name, aliased: Table[RentalRecord], where: Condition) = this(alias, null, null, null, aliased, null, where)

  /**
   * Create an aliased <code>public.rental</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.Rental.RENTAL)

  /**
   * Create an aliased <code>public.rental</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.Rental.RENTAL)

  /**
   * Create a <code>public.rental</code> table reference
   */
  def this() = this(DSL.name("rental"), null)

  def this(path: Table[_ <: Record], childPath: ForeignKey[_ <: Record, RentalRecord], parentPath: InverseForeignKey[_ <: Record, RentalRecord]) = this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, org.jooq.demo.skala.db.tables.Rental.RENTAL, null, null)

  override def getSchema: Schema = if (super.aliased()) null else Public.PUBLIC

  override def getIndexes: List[Index] = Arrays.asList[ Index ](Indexes.IDX_FK_INVENTORY_ID, Indexes.IDX_UNQ_RENTAL_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID)

  override def getIdentity: Identity[RentalRecord, Long] = super.getIdentity.asInstanceOf[ Identity[RentalRecord, Long] ]

  override def getPrimaryKey: UniqueKey[RentalRecord] = Keys.RENTAL_PKEY

  override def getReferences: List[ ForeignKey[RentalRecord, _] ] = Arrays.asList[ ForeignKey[RentalRecord, _] ](Keys.RENTAL__RENTAL_CUSTOMER_ID_FKEY, Keys.RENTAL__RENTAL_INVENTORY_ID_FKEY, Keys.RENTAL__RENTAL_STAFF_ID_FKEY)

  /**
   * Get the implicit join path to the <code>public.customer</code> table.
   */
  lazy val customer: CustomerPath = { new CustomerPath(this, Keys.RENTAL__RENTAL_CUSTOMER_ID_FKEY, null) }

  /**
   * Get the implicit join path to the <code>public.inventory</code> table.
   */
  lazy val inventory: InventoryPath = { new InventoryPath(this, Keys.RENTAL__RENTAL_INVENTORY_ID_FKEY, null) }

  /**
   * Get the implicit join path to the <code>public.staff</code> table.
   */
  lazy val staff: StaffPath = { new StaffPath(this, Keys.RENTAL__RENTAL_STAFF_ID_FKEY, null) }

  /**
   * Get the implicit to-many join path to the
   * <code>public.payment_p2007_01</code> table
   */
  lazy val paymentP2007_01: PaymentP2007_01Path = { new PaymentP2007_01Path(this, null, Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_RENTAL_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the
   * <code>public.payment_p2007_02</code> table
   */
  lazy val paymentP2007_02: PaymentP2007_02Path = { new PaymentP2007_02Path(this, null, Keys.PAYMENT_P2007_02__PAYMENT_P2007_02_RENTAL_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the
   * <code>public.payment_p2007_03</code> table
   */
  lazy val paymentP2007_03: PaymentP2007_03Path = { new PaymentP2007_03Path(this, null, Keys.PAYMENT_P2007_03__PAYMENT_P2007_03_RENTAL_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the
   * <code>public.payment_p2007_04</code> table
   */
  lazy val paymentP2007_04: PaymentP2007_04Path = { new PaymentP2007_04Path(this, null, Keys.PAYMENT_P2007_04__PAYMENT_P2007_04_RENTAL_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the
   * <code>public.payment_p2007_05</code> table
   */
  lazy val paymentP2007_05: PaymentP2007_05Path = { new PaymentP2007_05Path(this, null, Keys.PAYMENT_P2007_05__PAYMENT_P2007_05_RENTAL_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the
   * <code>public.payment_p2007_06</code> table
   */
  lazy val paymentP2007_06: PaymentP2007_06Path = { new PaymentP2007_06Path(this, null, Keys.PAYMENT_P2007_06__PAYMENT_P2007_06_RENTAL_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the <code>public.payment</code> table
   */
  lazy val payment: PaymentPath = { new PaymentPath(this, null, Keys.PAYMENT__PAYMENT_RENTAL_ID_FKEY.getInverseKey()) }
  override def as(alias: String): Rental = new Rental(DSL.name(alias), this)
  override def as(alias: Name): Rental = new Rental(alias, this)
  override def as(alias: Table[_]): Rental = new Rental(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): Rental = new Rental(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): Rental = new Rental(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[_]): Rental = new Rental(name.getQualifiedName(), null)

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Condition): Rental = new Rental(getQualifiedName(), if (super.aliased()) this else null, condition)

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Collection[_ <: Condition]): Rental = where(DSL.and(conditions))

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Condition*): Rental = where(DSL.and(conditions:_*))

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Field[Boolean]): Rental = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(condition: SQL): Rental = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String): Rental = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String, binds: AnyRef*): Rental = where(DSL.condition(condition, binds:_*))

  /**
   * Create an inline derived table from this table
   */
  override def whereExists(select: Select[_]): Rental = where(DSL.exists(select))

  /**
   * Create an inline derived table from this table
   */
  override def whereNotExists(select: Select[_]): Rental = where(DSL.notExists(select))
}
