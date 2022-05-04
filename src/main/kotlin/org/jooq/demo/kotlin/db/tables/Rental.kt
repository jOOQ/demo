/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import java.time.LocalDateTime
import java.util.function.Function

import kotlin.collections.List

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Index
import org.jooq.Name
import org.jooq.Record
import org.jooq.Records
import org.jooq.Result
import org.jooq.Row7
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableLike
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.indexes.RENTAL_IDX_FK_INVENTORY_ID
import org.jooq.demo.kotlin.db.keys.PAYMENT__PAYMENT_RENTAL_ID_FKEY
import org.jooq.demo.kotlin.db.keys.RENTAL__IDX_UNQ_RENTAL_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID
import org.jooq.demo.kotlin.db.keys.RENTAL__RENTAL_CUSTOMER_ID_FKEY
import org.jooq.demo.kotlin.db.keys.RENTAL__RENTAL_INVENTORY_ID_FKEY
import org.jooq.demo.kotlin.db.keys.RENTAL__RENTAL_PKEY
import org.jooq.demo.kotlin.db.keys.RENTAL__RENTAL_STAFF_ID_FKEY
import org.jooq.demo.kotlin.db.tables.records.CustomerRecord
import org.jooq.demo.kotlin.db.tables.records.InventoryRecord
import org.jooq.demo.kotlin.db.tables.records.PaymentRecord
import org.jooq.demo.kotlin.db.tables.records.RentalRecord
import org.jooq.demo.kotlin.db.tables.records.StaffRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Rental(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, RentalRecord>?,
    aliased: Table<RentalRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<RentalRecord>(
    alias,
    Public.PUBLIC,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>public.rental</code>
         */
        val RENTAL: Rental = Rental()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<RentalRecord> = RentalRecord::class.java

    /**
     * The column <code>public.rental.rental_id</code>.
     */
    val RENTAL_ID: TableField<RentalRecord, Long?> = createField(DSL.name("rental_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.rental.rental_date</code>.
     */
    val RENTAL_DATE: TableField<RentalRecord, LocalDateTime?> = createField(DSL.name("rental_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "")

    /**
     * The column <code>public.rental.inventory_id</code>.
     */
    val INVENTORY_ID: TableField<RentalRecord, Long?> = createField(DSL.name("inventory_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.rental.customer_id</code>.
     */
    val CUSTOMER_ID: TableField<RentalRecord, Long?> = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.rental.return_date</code>.
     */
    val RETURN_DATE: TableField<RentalRecord, LocalDateTime?> = createField(DSL.name("return_date"), SQLDataType.LOCALDATETIME(6), this, "")

    /**
     * The column <code>public.rental.staff_id</code>.
     */
    val STAFF_ID: TableField<RentalRecord, Long?> = createField(DSL.name("staff_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.rental.last_update</code>.
     */
    val LAST_UPDATE: TableField<RentalRecord, LocalDateTime?> = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now():::TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "")

    private constructor(alias: Name, aliased: Table<RentalRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<RentalRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>public.rental</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.rental</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.rental</code> table reference
     */
    constructor(): this(DSL.name("rental"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, RentalRecord>): this(Internal.createPathAlias(child, key), child, key, RENTAL, null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIndexes(): List<Index> = listOf(RENTAL_IDX_FK_INVENTORY_ID)
    override fun getIdentity(): Identity<RentalRecord, Long?> = super.getIdentity() as Identity<RentalRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<RentalRecord> = RENTAL__RENTAL_PKEY
    override fun getUniqueKeys(): List<UniqueKey<RentalRecord>> = listOf(RENTAL__IDX_UNQ_RENTAL_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID)
    override fun getReferences(): List<ForeignKey<RentalRecord, *>> = listOf(RENTAL__RENTAL_INVENTORY_ID_FKEY, RENTAL__RENTAL_CUSTOMER_ID_FKEY, RENTAL__RENTAL_STAFF_ID_FKEY)

    private lateinit var _inventory: Inventory
    private lateinit var _customer: Customer
    private lateinit var _staff: Staff

    /**
     * Get the implicit join path to the <code>public.inventory</code> table.
     */
    fun inventory(): Inventory {
        if (!this::_inventory.isInitialized)
            _inventory = Inventory(this, RENTAL__RENTAL_INVENTORY_ID_FKEY)

        return _inventory;
    }

    val inventory: Inventory
        get(): Inventory = inventory()

    /**
     * Get the implicit join path to the <code>public.customer</code> table.
     */
    fun customer(): Customer {
        if (!this::_customer.isInitialized)
            _customer = Customer(this, RENTAL__RENTAL_CUSTOMER_ID_FKEY)

        return _customer;
    }

    val customer: Customer
        get(): Customer = customer()

    /**
     * Get the implicit join path to the <code>public.staff</code> table.
     */
    fun staff(): Staff {
        if (!this::_staff.isInitialized)
            _staff = Staff(this, RENTAL__RENTAL_STAFF_ID_FKEY)

        return _staff;
    }

    val staff: Staff
        get(): Staff = staff()

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.rental</code> to-one parent table.
     */
    fun inventoryRow(): Field<InventoryRecord> = inventoryRow { it }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.rental</code> to-one parent table.
     */
    fun <O : Record> inventoryRow(subquery: (Inventory) -> TableLike<O>): Field<O> = toOneRow(RENTAL__RENTAL_INVENTORY_ID_FKEY, { subquery(it as Inventory) })

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.rental</code> to-one parent table.
     */
    fun customerRow(): Field<CustomerRecord> = customerRow { it }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.rental</code> to-one parent table.
     */
    fun <O : Record> customerRow(subquery: (Customer) -> TableLike<O>): Field<O> = toOneRow(RENTAL__RENTAL_CUSTOMER_ID_FKEY, { subquery(it as Customer) })

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.rental</code> to-one parent table.
     */
    fun staffRow(): Field<StaffRecord> = staffRow { it }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.rental</code> to-one parent table.
     */
    fun <O : Record> staffRow(subquery: (Staff) -> TableLike<O>): Field<O> = toOneRow(RENTAL__RENTAL_STAFF_ID_FKEY, { subquery(it as Staff) })

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment</code> one-to-many child table.
     */
    fun paymentMultiset(): Field<Result<PaymentRecord>> = paymentMultiset { it }

    /**
     * A convenience constructor for correlated <code>MULTISET</code>s
     * expressions to the <code>public.payment</code> one-to-many child table.
     */
    fun <O: Record>paymentMultiset(subquery: (Payment) -> TableLike<O>): Field<Result<O>> = oneToManyMultiset(PAYMENT__PAYMENT_RENTAL_ID_FKEY, { subquery(it as Payment) })
    override fun `as`(alias: String): Rental = Rental(DSL.name(alias), this)
    override fun `as`(alias: Name): Rental = Rental(alias, this)
    override fun `as`(alias: Table<*>): Rental = Rental(alias.getQualifiedName(), this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Rental = Rental(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Rental = Rental(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Rental = Rental(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row7<Long?, LocalDateTime?, Long?, Long?, LocalDateTime?, Long?, LocalDateTime?> = super.fieldsRow() as Row7<Long?, LocalDateTime?, Long?, Long?, LocalDateTime?, Long?, LocalDateTime?>

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, LocalDateTime?, Long?, Long?, LocalDateTime?, Long?, LocalDateTime?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, LocalDateTime?, Long?, Long?, LocalDateTime?, Long?, LocalDateTime?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}