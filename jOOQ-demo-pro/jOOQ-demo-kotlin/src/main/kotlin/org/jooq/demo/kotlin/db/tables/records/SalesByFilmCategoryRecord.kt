/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import java.math.BigDecimal

import org.jooq.Field
import org.jooq.Record2
import org.jooq.Row2
import org.jooq.demo.kotlin.db.tables.SalesByFilmCategory
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class SalesByFilmCategoryRecord() : TableRecordImpl<SalesByFilmCategoryRecord>(SalesByFilmCategory.SALES_BY_FILM_CATEGORY), Record2<String?, BigDecimal?> {

    open var category: String?
        set(value): Unit = set(0, value)
        get(): String? = get(0) as String?

    open var totalSales: BigDecimal?
        set(value): Unit = set(1, value)
        get(): BigDecimal? = get(1) as BigDecimal?

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row2<String?, BigDecimal?> = super.fieldsRow() as Row2<String?, BigDecimal?>
    override fun valuesRow(): Row2<String?, BigDecimal?> = super.valuesRow() as Row2<String?, BigDecimal?>
    override fun field1(): Field<String?> = SalesByFilmCategory.SALES_BY_FILM_CATEGORY.CATEGORY
    override fun field2(): Field<BigDecimal?> = SalesByFilmCategory.SALES_BY_FILM_CATEGORY.TOTAL_SALES
    override fun component1(): String? = category
    override fun component2(): BigDecimal? = totalSales
    override fun value1(): String? = category
    override fun value2(): BigDecimal? = totalSales

    override fun value1(value: String?): SalesByFilmCategoryRecord {
        this.category = value
        return this
    }

    override fun value2(value: BigDecimal?): SalesByFilmCategoryRecord {
        this.totalSales = value
        return this
    }

    override fun values(value1: String?, value2: BigDecimal?): SalesByFilmCategoryRecord {
        this.value1(value1)
        this.value2(value2)
        return this
    }

    /**
     * Create a detached, initialised SalesByFilmCategoryRecord
     */
    constructor(category: String? = null, totalSales: BigDecimal? = null): this() {
        this.category = category
        this.totalSales = totalSales
    }

    /**
     * Create a detached, initialised SalesByFilmCategoryRecord
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.SalesByFilmCategory?): this() {
        if (value != null) {
            this.category = value.category
            this.totalSales = value.totalSales
        }
    }
}