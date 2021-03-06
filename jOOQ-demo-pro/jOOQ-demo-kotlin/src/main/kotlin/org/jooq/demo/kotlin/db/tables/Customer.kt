/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import java.time.LocalDate
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
import org.jooq.Row12
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.indexes.IDX_FK_ADDRESS_ID
import org.jooq.demo.kotlin.db.indexes.IDX_FK_STORE_ID
import org.jooq.demo.kotlin.db.indexes.IDX_LAST_NAME
import org.jooq.demo.kotlin.db.keys.CUSTOMER_PKEY
import org.jooq.demo.kotlin.db.keys.CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY
import org.jooq.demo.kotlin.db.keys.CUSTOMER__CUSTOMER_STORE_ID_FKEY
import org.jooq.demo.kotlin.db.tables.records.CustomerRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Customer(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, CustomerRecord>?,
    aliased: Table<CustomerRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<CustomerRecord>(
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
         * The reference instance of <code>public.customer</code>
         */
        val CUSTOMER: Customer = Customer()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<CustomerRecord> = CustomerRecord::class.java

    /**
     * The column <code>public.customer.customer_id</code>.
     */
    val CUSTOMER_ID: TableField<CustomerRecord, Long?> = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.customer.store_id</code>.
     */
    val STORE_ID: TableField<CustomerRecord, Long?> = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.customer.first_name</code>.
     */
    val FIRST_NAME: TableField<CustomerRecord, String?> = createField(DSL.name("first_name"), SQLDataType.VARCHAR(45).nullable(false), this, "")

    /**
     * The column <code>public.customer.last_name</code>.
     */
    val LAST_NAME: TableField<CustomerRecord, String?> = createField(DSL.name("last_name"), SQLDataType.VARCHAR(45).nullable(false), this, "")

    /**
     * The column <code>public.customer.email</code>.
     */
    val EMAIL: TableField<CustomerRecord, String?> = createField(DSL.name("email"), SQLDataType.VARCHAR(50), this, "")

    /**
     * The column <code>public.customer.address_id</code>.
     */
    val ADDRESS_ID: TableField<CustomerRecord, Long?> = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.customer.activebool</code>.
     */
    val ACTIVEBOOL: TableField<CustomerRecord, Boolean?> = createField(DSL.name("activebool"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "")

    /**
     * The column <code>public.customer.create_date</code>.
     */
    val CREATE_DATE: TableField<CustomerRecord, LocalDate?> = createField(DSL.name("create_date"), SQLDataType.LOCALDATE.nullable(false).defaultValue(DSL.field("('now'::text)::date", SQLDataType.LOCALDATE)), this, "")

    /**
     * The column <code>public.customer.last_update</code>.
     */
    val LAST_UPDATE: TableField<CustomerRecord, LocalDateTime?> = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "")

    /**
     * The column <code>public.customer.active</code>.
     */
    val ACTIVE: TableField<CustomerRecord, Int?> = createField(DSL.name("active"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>public.customer.full_address</code>.
     */
    val FULL_ADDRESS: TableField<CustomerRecord, String?> = createField(DSL.name("full_address"), SQLDataType.CLOB.virtual(), this, "", { ctx -> DSL.concat(address.ADDRESS_, DSL.inline(", "), address.POSTAL_CODE, DSL.inline(", "), address.city.CITY_, DSL.inline(", "), address.city.country.COUNTRY_) })

    /**
     * The column <code>public.customer.full_name</code>.
     */
    val FULL_NAME: TableField<CustomerRecord, String?> = createField(DSL.name("full_name"), SQLDataType.CLOB.virtual(), this, "", { ctx -> DSL.concat(FIRST_NAME, DSL.inline(" "), LAST_NAME) })

    private constructor(alias: Name, aliased: Table<CustomerRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<CustomerRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>public.customer</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.customer</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.customer</code> table reference
     */
    constructor(): this(DSL.name("customer"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, CustomerRecord>): this(Internal.createPathAlias(child, key), child, key, CUSTOMER, null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIndexes(): List<Index> = listOf(IDX_FK_ADDRESS_ID, IDX_FK_STORE_ID, IDX_LAST_NAME)
    override fun getIdentity(): Identity<CustomerRecord, Long?> = super.getIdentity() as Identity<CustomerRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<CustomerRecord> = CUSTOMER_PKEY
    override fun getReferences(): List<ForeignKey<CustomerRecord, *>> = listOf(CUSTOMER__CUSTOMER_STORE_ID_FKEY, CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY)

    private lateinit var _store: Store
    private lateinit var _address: Address

    /**
     * Get the implicit join path to the <code>public.store</code> table.
     */
    fun store(): Store {
        if (!this::_store.isInitialized)
            _store = Store(this, CUSTOMER__CUSTOMER_STORE_ID_FKEY)

        return _store;
    }

    val store: Store
        get(): Store = store()

    /**
     * Get the implicit join path to the <code>public.address</code> table.
     */
    fun address(): Address {
        if (!this::_address.isInitialized)
            _address = Address(this, CUSTOMER__CUSTOMER_ADDRESS_ID_FKEY)

        return _address;
    }

    val address: Address
        get(): Address = address()
    override fun `as`(alias: String): Customer = Customer(DSL.name(alias), this)
    override fun `as`(alias: Name): Customer = Customer(alias, this)
    override fun `as`(alias: Table<*>): Customer = Customer(alias.getQualifiedName(), this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Customer = Customer(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Customer = Customer(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Customer = Customer(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row12<Long?, Long?, String?, String?, String?, Long?, Boolean?, LocalDate?, LocalDateTime?, Int?, String?, String?> = super.fieldsRow() as Row12<Long?, Long?, String?, String?, String?, Long?, Boolean?, LocalDate?, LocalDateTime?, Int?, String?, String?>

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, Long?, String?, String?, String?, Long?, Boolean?, LocalDate?, LocalDateTime?, Int?, String?, String?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, Long?, String?, String?, String?, Long?, Boolean?, LocalDate?, LocalDateTime?, Int?, String?, String?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}
