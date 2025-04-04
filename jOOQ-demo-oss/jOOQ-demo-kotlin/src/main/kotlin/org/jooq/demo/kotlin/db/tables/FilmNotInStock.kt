/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Record
import org.jooq.Schema
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.tables.records.FilmNotInStockRecord
import org.jooq.impl.DSL
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class FilmNotInStock(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, FilmNotInStockRecord>?,
    parentPath: InverseForeignKey<out Record, FilmNotInStockRecord>?,
    aliased: Table<FilmNotInStockRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<FilmNotInStockRecord>(
    alias,
    Public.PUBLIC,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.function(),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>public.film_not_in_stock</code>
         */
        val FILM_NOT_IN_STOCK: FilmNotInStock = FilmNotInStock()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<FilmNotInStockRecord> = FilmNotInStockRecord::class.java

    /**
     * The column <code>public.film_not_in_stock.p_film_count</code>.
     */
    val P_FILM_COUNT: TableField<FilmNotInStockRecord, Int?> = createField(DSL.name("p_film_count"), SQLDataType.INTEGER, this, "")

    private constructor(alias: Name, aliased: Table<FilmNotInStockRecord>?): this(alias, null, null, null, aliased, arrayOf(
        DSL.value(null, SQLDataType.BIGINT),
        DSL.value(null, SQLDataType.BIGINT)
    ), null)
    private constructor(alias: Name, aliased: Table<FilmNotInStockRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)

    /**
     * Create an aliased <code>public.film_not_in_stock</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.film_not_in_stock</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.film_not_in_stock</code> table reference
     */
    constructor(): this(DSL.name("film_not_in_stock"), null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun `as`(alias: String): FilmNotInStock = FilmNotInStock(DSL.name(alias), this, parameters)
    override fun `as`(alias: Name): FilmNotInStock = FilmNotInStock(alias, this, parameters)
    override fun `as`(alias: Table<*>): FilmNotInStock = FilmNotInStock(alias.qualifiedName, this, parameters)

    /**
     * Rename this table
     */
    override fun rename(name: String): FilmNotInStock = FilmNotInStock(DSL.name(name), null, parameters)

    /**
     * Rename this table
     */
    override fun rename(name: Name): FilmNotInStock = FilmNotInStock(name, null, parameters)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): FilmNotInStock = FilmNotInStock(name.qualifiedName, null, parameters)

    /**
     * Call this table-valued function
     */
    fun call(
          pFilmId: Long?
        , pStoreId: Long?
    ): FilmNotInStock = FilmNotInStock(DSL.name("film_not_in_stock"), null, arrayOf(
        DSL.value(pFilmId, SQLDataType.BIGINT),
        DSL.value(pStoreId, SQLDataType.BIGINT)
    )).let { if (aliased()) it.`as`(unqualifiedName) else it }

    /**
     * Call this table-valued function
     */
    fun call(
          pFilmId: Field<Long?>
        , pStoreId: Field<Long?>
    ): FilmNotInStock = FilmNotInStock(DSL.name("film_not_in_stock"), null, arrayOf(
        pFilmId,
        pStoreId
    )).let { if (aliased()) it.`as`(unqualifiedName) else it }
}
