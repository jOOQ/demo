/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.daos


import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Configuration
import org.jooq.demo.kotlin.db.tables.Country
import org.jooq.demo.kotlin.db.tables.records.CountryRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class CountryDao(configuration: Configuration?) : DAOImpl<CountryRecord, org.jooq.demo.kotlin.db.tables.pojos.Country, Long>(Country.COUNTRY, org.jooq.demo.kotlin.db.tables.pojos.Country::class.java, configuration) {

    /**
     * Create a new CountryDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: org.jooq.demo.kotlin.db.tables.pojos.Country): Long? = o.countryId

    /**
     * Fetch records that have <code>country_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfCountryId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Country> = fetchRange(Country.COUNTRY.COUNTRY_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>country_id IN (values)</code>
     */
    fun fetchByCountryId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Country> = fetch(Country.COUNTRY.COUNTRY_ID, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>country_id = value</code>
     */
    fun fetchOneByCountryId(value: Long): org.jooq.demo.kotlin.db.tables.pojos.Country? = fetchOne(Country.COUNTRY.COUNTRY_ID, value)

    /**
     * Fetch records that have <code>country BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfCountry(lowerInclusive: String?, upperInclusive: String?): List<org.jooq.demo.kotlin.db.tables.pojos.Country> = fetchRange(Country.COUNTRY.COUNTRY_, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>country IN (values)</code>
     */
    fun fetchByCountry(vararg values: String): List<org.jooq.demo.kotlin.db.tables.pojos.Country> = fetch(Country.COUNTRY.COUNTRY_, *values)

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<org.jooq.demo.kotlin.db.tables.pojos.Country> = fetchRange(Country.COUNTRY.LAST_UPDATE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    fun fetchByLastUpdate(vararg values: LocalDateTime): List<org.jooq.demo.kotlin.db.tables.pojos.Country> = fetch(Country.COUNTRY.LAST_UPDATE, *values)
}
