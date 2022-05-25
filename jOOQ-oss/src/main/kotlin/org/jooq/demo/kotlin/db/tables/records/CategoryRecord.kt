/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record3
import org.jooq.Row3
import org.jooq.demo.kotlin.db.tables.Category
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class CategoryRecord() : UpdatableRecordImpl<CategoryRecord>(Category.CATEGORY), Record3<Long?, String?, LocalDateTime?> {

    open var categoryId: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open var name: String?
        set(value): Unit = set(1, value)
        get(): String? = get(1) as String?

    open var lastUpdate: LocalDateTime?
        set(value): Unit = set(2, value)
        get(): LocalDateTime? = get(2) as LocalDateTime?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row3<Long?, String?, LocalDateTime?> = super.fieldsRow() as Row3<Long?, String?, LocalDateTime?>
    override fun valuesRow(): Row3<Long?, String?, LocalDateTime?> = super.valuesRow() as Row3<Long?, String?, LocalDateTime?>
    override fun field1(): Field<Long?> = Category.CATEGORY.CATEGORY_ID
    override fun field2(): Field<String?> = Category.CATEGORY.NAME
    override fun field3(): Field<LocalDateTime?> = Category.CATEGORY.LAST_UPDATE
    override fun component1(): Long? = categoryId
    override fun component2(): String? = name
    override fun component3(): LocalDateTime? = lastUpdate
    override fun value1(): Long? = categoryId
    override fun value2(): String? = name
    override fun value3(): LocalDateTime? = lastUpdate

    override fun value1(value: Long?): CategoryRecord {
        this.categoryId = value
        return this
    }

    override fun value2(value: String?): CategoryRecord {
        this.name = value
        return this
    }

    override fun value3(value: LocalDateTime?): CategoryRecord {
        this.lastUpdate = value
        return this
    }

    override fun values(value1: Long?, value2: String?, value3: LocalDateTime?): CategoryRecord {
        this.value1(value1)
        this.value2(value2)
        this.value3(value3)
        return this
    }

    /**
     * Create a detached, initialised CategoryRecord
     */
    constructor(categoryId: Long? = null, name: String? = null, lastUpdate: LocalDateTime? = null): this() {
        this.categoryId = categoryId
        this.name = name
        this.lastUpdate = lastUpdate
    }

    /**
     * Create a detached, initialised CategoryRecord
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.Category?): this() {
        if (value != null) {
            this.categoryId = value.categoryId
            this.name = value.name
            this.lastUpdate = value.lastUpdate
        }
    }
}
