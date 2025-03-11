/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import java.math.BigDecimal

import org.jooq.demo.kotlin.db.tables.SalesByStore
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class SalesByStoreRecord() : TableRecordImpl<SalesByStoreRecord>(SalesByStore.SALES_BY_STORE) {

    open var store: String?
        set(value): Unit = set(0, value)
        get(): String? = get(0) as String?

    open var manager: String?
        set(value): Unit = set(1, value)
        get(): String? = get(1) as String?

    open var totalSales: BigDecimal?
        set(value): Unit = set(2, value)
        get(): BigDecimal? = get(2) as BigDecimal?

    /**
     * Create a detached, initialised SalesByStoreRecord
     */
    constructor(store: String? = null, manager: String? = null, totalSales: BigDecimal? = null): this() {
        this.store = store
        this.manager = manager
        this.totalSales = totalSales
        resetTouchedOnNotNull()
    }

    /**
     * Create a detached, initialised SalesByStoreRecord
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.SalesByStore?): this() {
        if (value != null) {
            this.store = value.store
            this.manager = value.manager
            this.totalSales = value.totalSales
            resetTouchedOnNotNull()
        }
    }
}
