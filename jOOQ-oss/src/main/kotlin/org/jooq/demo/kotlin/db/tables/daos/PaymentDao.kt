/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.daos


import java.math.BigDecimal
import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Configuration
import org.jooq.demo.kotlin.db.tables.Payment
import org.jooq.demo.kotlin.db.tables.records.PaymentRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class PaymentDao(configuration: Configuration?) : DAOImpl<PaymentRecord, org.jooq.demo.kotlin.db.tables.pojos.Payment, Long>(Payment.PAYMENT, org.jooq.demo.kotlin.db.tables.pojos.Payment::class.java, configuration) {

    /**
     * Create a new PaymentDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: org.jooq.demo.kotlin.db.tables.pojos.Payment): Long? = o.paymentId

    /**
     * Fetch records that have <code>payment_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfPaymentId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetchRange(Payment.PAYMENT.PAYMENT_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>payment_id IN (values)</code>
     */
    fun fetchByPaymentId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetch(Payment.PAYMENT.PAYMENT_ID, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>payment_id = value</code>
     */
    fun fetchOneByPaymentId(value: Long): org.jooq.demo.kotlin.db.tables.pojos.Payment? = fetchOne(Payment.PAYMENT.PAYMENT_ID, value)

    /**
     * Fetch records that have <code>customer_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfCustomerId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetchRange(Payment.PAYMENT.CUSTOMER_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>customer_id IN (values)</code>
     */
    fun fetchByCustomerId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetch(Payment.PAYMENT.CUSTOMER_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>staff_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfStaffId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetchRange(Payment.PAYMENT.STAFF_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>staff_id IN (values)</code>
     */
    fun fetchByStaffId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetch(Payment.PAYMENT.STAFF_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>rental_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRentalId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetchRange(Payment.PAYMENT.RENTAL_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>rental_id IN (values)</code>
     */
    fun fetchByRentalId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetch(Payment.PAYMENT.RENTAL_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>amount BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfAmount(lowerInclusive: BigDecimal?, upperInclusive: BigDecimal?): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetchRange(Payment.PAYMENT.AMOUNT, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>amount IN (values)</code>
     */
    fun fetchByAmount(vararg values: BigDecimal): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetch(Payment.PAYMENT.AMOUNT, *values)

    /**
     * Fetch records that have <code>payment_date BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfPaymentDate(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetchRange(Payment.PAYMENT.PAYMENT_DATE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>payment_date IN (values)</code>
     */
    fun fetchByPaymentDate(vararg values: LocalDateTime): List<org.jooq.demo.kotlin.db.tables.pojos.Payment> = fetch(Payment.PAYMENT.PAYMENT_DATE, *values)
}
