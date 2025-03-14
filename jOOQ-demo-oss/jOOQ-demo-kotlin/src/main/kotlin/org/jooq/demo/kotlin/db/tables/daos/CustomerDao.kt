/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.daos


import java.time.LocalDate
import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Configuration
import org.jooq.demo.kotlin.db.tables.Customer
import org.jooq.demo.kotlin.db.tables.records.CustomerRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class CustomerDao(configuration: Configuration?) : DAOImpl<CustomerRecord, org.jooq.demo.kotlin.db.tables.pojos.Customer, Long>(Customer.CUSTOMER, org.jooq.demo.kotlin.db.tables.pojos.Customer::class.java, configuration) {

    /**
     * Create a new CustomerDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: org.jooq.demo.kotlin.db.tables.pojos.Customer): Long? = o.customerId

    /**
     * Fetch records that have <code>customer_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfCustomerId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.CUSTOMER_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>customer_id IN (values)</code>
     */
    fun fetchByCustomerId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.CUSTOMER_ID, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>customer_id = value</code>
     */
    fun fetchOneByCustomerId(value: Long): org.jooq.demo.kotlin.db.tables.pojos.Customer? = fetchOne(Customer.CUSTOMER.CUSTOMER_ID, value)

    /**
     * Fetch records that have <code>store_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfStoreId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.STORE_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>store_id IN (values)</code>
     */
    fun fetchByStoreId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.STORE_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>first_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfFirstName(lowerInclusive: String?, upperInclusive: String?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.FIRST_NAME, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    fun fetchByFirstName(vararg values: String): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.FIRST_NAME, *values)

    /**
     * Fetch records that have <code>last_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfLastName(lowerInclusive: String?, upperInclusive: String?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.LAST_NAME, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    fun fetchByLastName(vararg values: String): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.LAST_NAME, *values)

    /**
     * Fetch records that have <code>email BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfEmail(lowerInclusive: String?, upperInclusive: String?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.EMAIL, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    fun fetchByEmail(vararg values: String): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.EMAIL, *values)

    /**
     * Fetch records that have <code>address_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfAddressId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.ADDRESS_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>address_id IN (values)</code>
     */
    fun fetchByAddressId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.ADDRESS_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>activebool BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfActivebool(lowerInclusive: Boolean?, upperInclusive: Boolean?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.ACTIVEBOOL, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>activebool IN (values)</code>
     */
    fun fetchByActivebool(vararg values: Boolean): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.ACTIVEBOOL, *values.toTypedArray())

    /**
     * Fetch records that have <code>create_date BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfCreateDate(lowerInclusive: LocalDate?, upperInclusive: LocalDate?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.CREATE_DATE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>create_date IN (values)</code>
     */
    fun fetchByCreateDate(vararg values: LocalDate): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.CREATE_DATE, *values)

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.LAST_UPDATE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    fun fetchByLastUpdate(vararg values: LocalDateTime): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.LAST_UPDATE, *values)

    /**
     * Fetch records that have <code>active BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfActive(lowerInclusive: Int?, upperInclusive: Int?): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetchRange(Customer.CUSTOMER.ACTIVE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>active IN (values)</code>
     */
    fun fetchByActive(vararg values: Int): List<org.jooq.demo.kotlin.db.tables.pojos.Customer> = fetch(Customer.CUSTOMER.ACTIVE, *values.toTypedArray())
}
