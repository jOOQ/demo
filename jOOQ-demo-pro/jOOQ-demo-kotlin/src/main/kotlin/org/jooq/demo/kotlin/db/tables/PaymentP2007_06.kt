/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import java.math.BigDecimal
import java.time.LocalDateTime

import kotlin.collections.Collection
import kotlin.collections.List

import org.jooq.Check
import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Index
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.QueryPart
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.indexes.IDX_FK_PAYMENT_P2007_06_CUSTOMER_ID
import org.jooq.demo.kotlin.db.indexes.IDX_FK_PAYMENT_P2007_06_STAFF_ID
import org.jooq.demo.kotlin.db.keys.PAYMENT_P2007_06__PAYMENT_P2007_06_CUSTOMER_ID_FKEY
import org.jooq.demo.kotlin.db.keys.PAYMENT_P2007_06__PAYMENT_P2007_06_RENTAL_ID_FKEY
import org.jooq.demo.kotlin.db.keys.PAYMENT_P2007_06__PAYMENT_P2007_06_STAFF_ID_FKEY
import org.jooq.demo.kotlin.db.tables.Customer.CustomerPath
import org.jooq.demo.kotlin.db.tables.Rental.RentalPath
import org.jooq.demo.kotlin.db.tables.Staff.StaffPath
import org.jooq.demo.kotlin.db.tables.records.PaymentP2007_06Record
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class PaymentP2007_06(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, PaymentP2007_06Record>?,
    parentPath: InverseForeignKey<out Record, PaymentP2007_06Record>?,
    aliased: Table<PaymentP2007_06Record>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<PaymentP2007_06Record>(
    alias,
    Public.PUBLIC,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table(),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>public.payment_p2007_06</code>
         */
        val PAYMENT_P2007_06: PaymentP2007_06 = PaymentP2007_06()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<PaymentP2007_06Record> = PaymentP2007_06Record::class.java

    /**
     * The column <code>public.payment_p2007_06.payment_id</code>.
     */
    val PAYMENT_ID: TableField<PaymentP2007_06Record, Long?> = createField(DSL.name("payment_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.payment_p2007_06.customer_id</code>.
     */
    val CUSTOMER_ID: TableField<PaymentP2007_06Record, Long?> = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.payment_p2007_06.staff_id</code>.
     */
    val STAFF_ID: TableField<PaymentP2007_06Record, Long?> = createField(DSL.name("staff_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.payment_p2007_06.rental_id</code>.
     */
    val RENTAL_ID: TableField<PaymentP2007_06Record, Long?> = createField(DSL.name("rental_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.payment_p2007_06.amount</code>.
     */
    val AMOUNT: TableField<PaymentP2007_06Record, BigDecimal?> = createField(DSL.name("amount"), SQLDataType.NUMERIC(5, 2).nullable(false), this, "")

    /**
     * The column <code>public.payment_p2007_06.payment_date</code>.
     */
    val PAYMENT_DATE: TableField<PaymentP2007_06Record, LocalDateTime?> = createField(DSL.name("payment_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "")

    private constructor(alias: Name, aliased: Table<PaymentP2007_06Record>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<PaymentP2007_06Record>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<PaymentP2007_06Record>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.payment_p2007_06</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.payment_p2007_06</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.payment_p2007_06</code> table reference
     */
    constructor(): this(DSL.name("payment_p2007_06"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, PaymentP2007_06Record>?, parentPath: InverseForeignKey<out Record, PaymentP2007_06Record>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, PAYMENT_P2007_06, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class PaymentP2007_06Path : PaymentP2007_06, Path<PaymentP2007_06Record> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, PaymentP2007_06Record>?, parentPath: InverseForeignKey<out Record, PaymentP2007_06Record>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<PaymentP2007_06Record>): super(alias, aliased)
        override fun `as`(alias: String): PaymentP2007_06Path = PaymentP2007_06Path(DSL.name(alias), this)
        override fun `as`(alias: Name): PaymentP2007_06Path = PaymentP2007_06Path(alias, this)
        override fun `as`(alias: Table<*>): PaymentP2007_06Path = PaymentP2007_06Path(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIndexes(): List<Index> = listOf(IDX_FK_PAYMENT_P2007_06_CUSTOMER_ID, IDX_FK_PAYMENT_P2007_06_STAFF_ID)
    override fun getIdentity(): Identity<PaymentP2007_06Record, Long?> = super.getIdentity() as Identity<PaymentP2007_06Record, Long?>
    override fun getReferences(): List<ForeignKey<PaymentP2007_06Record, *>> = listOf(PAYMENT_P2007_06__PAYMENT_P2007_06_CUSTOMER_ID_FKEY, PAYMENT_P2007_06__PAYMENT_P2007_06_RENTAL_ID_FKEY, PAYMENT_P2007_06__PAYMENT_P2007_06_STAFF_ID_FKEY)

    /**
     * Get the implicit join path to the <code>public.customer</code> table.
     */
    fun customer(): CustomerPath = customer
    val customer: CustomerPath by lazy { CustomerPath(this, PAYMENT_P2007_06__PAYMENT_P2007_06_CUSTOMER_ID_FKEY, null) }

    /**
     * Get the implicit join path to the <code>public.rental</code> table.
     */
    fun rental(): RentalPath = rental
    val rental: RentalPath by lazy { RentalPath(this, PAYMENT_P2007_06__PAYMENT_P2007_06_RENTAL_ID_FKEY, null) }

    /**
     * Get the implicit join path to the <code>public.staff</code> table.
     */
    fun staff(): StaffPath = staff
    val staff: StaffPath by lazy { StaffPath(this, PAYMENT_P2007_06__PAYMENT_P2007_06_STAFF_ID_FKEY, null) }
    override fun getChecks(): List<Check<PaymentP2007_06Record>> = listOf(
        Internal.createCheck(this, DSL.name("payment_p2007_06_payment_date_check"), "(((payment_date >= '2007-06-01 00:00:00'::timestamp without time zone) AND (payment_date < '2007-07-01 00:00:00'::timestamp without time zone)))", true)
    )
    override fun `as`(alias: String): PaymentP2007_06 = PaymentP2007_06(DSL.name(alias), this)
    override fun `as`(alias: Name): PaymentP2007_06 = PaymentP2007_06(alias, this)
    override fun `as`(alias: Table<*>): PaymentP2007_06 = PaymentP2007_06(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): PaymentP2007_06 = PaymentP2007_06(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): PaymentP2007_06 = PaymentP2007_06(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): PaymentP2007_06 = PaymentP2007_06(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): PaymentP2007_06 = PaymentP2007_06(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): PaymentP2007_06 = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): PaymentP2007_06 = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): PaymentP2007_06 = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): PaymentP2007_06 = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): PaymentP2007_06 = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): PaymentP2007_06 = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): PaymentP2007_06 = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): PaymentP2007_06 = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): PaymentP2007_06 = where(DSL.notExists(select))
}
