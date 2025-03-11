/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import org.jooq.demo.kotlin.db.tables.StaffList
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class StaffListRecord() : TableRecordImpl<StaffListRecord>(StaffList.STAFF_LIST) {

    open var id: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open var name: String?
        set(value): Unit = set(1, value)
        get(): String? = get(1) as String?

    open var address: String?
        set(value): Unit = set(2, value)
        get(): String? = get(2) as String?

    open var zipCode: String?
        set(value): Unit = set(3, value)
        get(): String? = get(3) as String?

    open var phone: String?
        set(value): Unit = set(4, value)
        get(): String? = get(4) as String?

    open var city: String?
        set(value): Unit = set(5, value)
        get(): String? = get(5) as String?

    open var country: String?
        set(value): Unit = set(6, value)
        get(): String? = get(6) as String?

    open var sid: Long?
        set(value): Unit = set(7, value)
        get(): Long? = get(7) as Long?

    /**
     * Create a detached, initialised StaffListRecord
     */
    constructor(id: Long? = null, name: String? = null, address: String? = null, zipCode: String? = null, phone: String? = null, city: String? = null, country: String? = null, sid: Long? = null): this() {
        this.id = id
        this.name = name
        this.address = address
        this.zipCode = zipCode
        this.phone = phone
        this.city = city
        this.country = country
        this.sid = sid
        resetTouchedOnNotNull()
    }

    /**
     * Create a detached, initialised StaffListRecord
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.StaffList?): this() {
        if (value != null) {
            this.id = value.id
            this.name = value.name
            this.address = value.address
            this.zipCode = value.zipCode
            this.phone = value.phone
            this.city = value.city
            this.country = value.country
            this.sid = value.sid
            resetTouchedOnNotNull()
        }
    }
}
