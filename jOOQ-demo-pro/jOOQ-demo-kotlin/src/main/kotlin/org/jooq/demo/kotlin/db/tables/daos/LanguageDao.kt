/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.daos


import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Configuration
import org.jooq.demo.kotlin.db.tables.Language
import org.jooq.demo.kotlin.db.tables.records.LanguageRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class LanguageDao(configuration: Configuration?) : DAOImpl<LanguageRecord, org.jooq.demo.kotlin.db.tables.pojos.Language, Long>(Language.LANGUAGE, org.jooq.demo.kotlin.db.tables.pojos.Language::class.java, configuration) {

    /**
     * Create a new LanguageDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: org.jooq.demo.kotlin.db.tables.pojos.Language): Long? = o.languageId

    /**
     * Fetch records that have <code>language_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfLanguageId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.Language> = fetchRange(Language.LANGUAGE.LANGUAGE_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>language_id IN (values)</code>
     */
    fun fetchByLanguageId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.Language> = fetch(Language.LANGUAGE.LANGUAGE_ID, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>language_id = value</code>
     */
    fun fetchOneByLanguageId(value: Long): org.jooq.demo.kotlin.db.tables.pojos.Language? = fetchOne(Language.LANGUAGE.LANGUAGE_ID, value)

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfName(lowerInclusive: String?, upperInclusive: String?): List<org.jooq.demo.kotlin.db.tables.pojos.Language> = fetchRange(Language.LANGUAGE.NAME, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    fun fetchByName(vararg values: String): List<org.jooq.demo.kotlin.db.tables.pojos.Language> = fetch(Language.LANGUAGE.NAME, *values)

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<org.jooq.demo.kotlin.db.tables.pojos.Language> = fetchRange(Language.LANGUAGE.LAST_UPDATE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    fun fetchByLastUpdate(vararg values: LocalDateTime): List<org.jooq.demo.kotlin.db.tables.pojos.Language> = fetch(Language.LANGUAGE.LAST_UPDATE, *values)
}
