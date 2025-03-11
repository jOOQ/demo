/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import java.math.BigDecimal
import java.time.LocalDateTime

import org.jooq.demo.kotlin.db.tables.PaymentP2007_01
import org.jooq.impl.TableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class PaymentP2007_01Record() : TableRecordImpl<PaymentP2007_01Record>(PaymentP2007_01.PAYMENT_P2007_01) {

    open var paymentId: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open var customerId: Long?
        set(value): Unit = set(1, value)
        get(): Long? = get(1) as Long?

    open var staffId: Long?
        set(value): Unit = set(2, value)
        get(): Long? = get(2) as Long?

    open var rentalId: Long?
        set(value): Unit = set(3, value)
        get(): Long? = get(3) as Long?

    open var amount: BigDecimal?
        set(value): Unit = set(4, value)
        get(): BigDecimal? = get(4) as BigDecimal?

    open var paymentDate: LocalDateTime?
        set(value): Unit = set(5, value)
        get(): LocalDateTime? = get(5) as LocalDateTime?

    /**
     * Create a detached, initialised PaymentP2007_01Record
     */
    constructor(paymentId: Long? = null, customerId: Long? = null, staffId: Long? = null, rentalId: Long? = null, amount: BigDecimal? = null, paymentDate: LocalDateTime? = null): this() {
        this.paymentId = paymentId
        this.customerId = customerId
        this.staffId = staffId
        this.rentalId = rentalId
        this.amount = amount
        this.paymentDate = paymentDate
        resetTouchedOnNotNull()
    }

    /**
     * Create a detached, initialised PaymentP2007_01Record
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.PaymentP2007_01?): this() {
        if (value != null) {
            this.paymentId = value.paymentId
            this.customerId = value.customerId
            this.staffId = value.staffId
            this.rentalId = value.rentalId
            this.amount = value.amount
            this.paymentDate = value.paymentDate
            resetTouchedOnNotNull()
        }
    }
}
