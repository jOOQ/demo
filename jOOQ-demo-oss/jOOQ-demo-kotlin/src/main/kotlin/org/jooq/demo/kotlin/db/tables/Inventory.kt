/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import java.time.LocalDateTime

import kotlin.collections.Collection
import kotlin.collections.List

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
import org.jooq.UniqueKey
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.indexes.IDX_STORE_ID_FILM_ID
import org.jooq.demo.kotlin.db.keys.INVENTORY_PKEY
import org.jooq.demo.kotlin.db.keys.INVENTORY__INVENTORY_FILM_ID_FKEY
import org.jooq.demo.kotlin.db.keys.INVENTORY__INVENTORY_STORE_ID_FKEY
import org.jooq.demo.kotlin.db.keys.RENTAL__RENTAL_INVENTORY_ID_FKEY
import org.jooq.demo.kotlin.db.tables.Film.FilmPath
import org.jooq.demo.kotlin.db.tables.Rental.RentalPath
import org.jooq.demo.kotlin.db.tables.Store.StorePath
import org.jooq.demo.kotlin.db.tables.records.InventoryRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class Inventory(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, InventoryRecord>?,
    parentPath: InverseForeignKey<out Record, InventoryRecord>?,
    aliased: Table<InventoryRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<InventoryRecord>(
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
         * The reference instance of <code>public.inventory</code>
         */
        val INVENTORY: Inventory = Inventory()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<InventoryRecord> = InventoryRecord::class.java

    /**
     * The column <code>public.inventory.inventory_id</code>.
     */
    val INVENTORY_ID: TableField<InventoryRecord, Long?> = createField(DSL.name("inventory_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.inventory.film_id</code>.
     */
    val FILM_ID: TableField<InventoryRecord, Long?> = createField(DSL.name("film_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.inventory.store_id</code>.
     */
    val STORE_ID: TableField<InventoryRecord, Long?> = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>public.inventory.last_update</code>.
     */
    val LAST_UPDATE: TableField<InventoryRecord, LocalDateTime?> = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "")

    private constructor(alias: Name, aliased: Table<InventoryRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<InventoryRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<InventoryRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.inventory</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.inventory</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.inventory</code> table reference
     */
    constructor(): this(DSL.name("inventory"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, InventoryRecord>?, parentPath: InverseForeignKey<out Record, InventoryRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, INVENTORY, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class InventoryPath : Inventory, Path<InventoryRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, InventoryRecord>?, parentPath: InverseForeignKey<out Record, InventoryRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<InventoryRecord>): super(alias, aliased)
        override fun `as`(alias: String): InventoryPath = InventoryPath(DSL.name(alias), this)
        override fun `as`(alias: Name): InventoryPath = InventoryPath(alias, this)
        override fun `as`(alias: Table<*>): InventoryPath = InventoryPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIndexes(): List<Index> = listOf(IDX_STORE_ID_FILM_ID)
    override fun getIdentity(): Identity<InventoryRecord, Long?> = super.getIdentity() as Identity<InventoryRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<InventoryRecord> = INVENTORY_PKEY
    override fun getReferences(): List<ForeignKey<InventoryRecord, *>> = listOf(INVENTORY__INVENTORY_FILM_ID_FKEY, INVENTORY__INVENTORY_STORE_ID_FKEY)

    /**
     * Get the implicit join path to the <code>public.film</code> table.
     */
    fun film(): FilmPath = film
    val film: FilmPath by lazy { FilmPath(this, INVENTORY__INVENTORY_FILM_ID_FKEY, null) }

    /**
     * Get the implicit join path to the <code>public.store</code> table.
     */
    fun store(): StorePath = store
    val store: StorePath by lazy { StorePath(this, INVENTORY__INVENTORY_STORE_ID_FKEY, null) }

    private lateinit var _rental: RentalPath

    /**
     * Get the implicit to-many join path to the <code>public.rental</code>
     * table
     */
    fun rental(): RentalPath {
        if (!this::_rental.isInitialized)
            _rental = RentalPath(this, null, RENTAL__RENTAL_INVENTORY_ID_FKEY.inverseKey)

        return _rental;
    }

    val rental: RentalPath
        get(): RentalPath = rental()
    override fun `as`(alias: String): Inventory = Inventory(DSL.name(alias), this)
    override fun `as`(alias: Name): Inventory = Inventory(alias, this)
    override fun `as`(alias: Table<*>): Inventory = Inventory(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Inventory = Inventory(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Inventory = Inventory(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Inventory = Inventory(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): Inventory = Inventory(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): Inventory = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): Inventory = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): Inventory = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): Inventory = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): Inventory = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): Inventory = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): Inventory = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): Inventory = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): Inventory = where(DSL.notExists(select))
}
