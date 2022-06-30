/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.daos


import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Configuration
import org.jooq.Record2
import org.jooq.demo.kotlin.db.tables.FilmActor
import org.jooq.demo.kotlin.db.tables.records.FilmActorRecord
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class FilmActorDao(configuration: Configuration?) : DAOImpl<FilmActorRecord, org.jooq.demo.kotlin.db.tables.pojos.FilmActor, Record2<Long?, Long?>>(FilmActor.FILM_ACTOR, org.jooq.demo.kotlin.db.tables.pojos.FilmActor::class.java, configuration) {

    /**
     * Create a new FilmActorDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: org.jooq.demo.kotlin.db.tables.pojos.FilmActor): Record2<Long?, Long?>? = compositeKeyRecord(o.actorId, o.filmId)

    /**
     * Fetch records that have <code>actor_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfActorId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.FilmActor> = fetchRange(FilmActor.FILM_ACTOR.ACTOR_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>actor_id IN (values)</code>
     */
    fun fetchByActorId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.FilmActor> = fetch(FilmActor.FILM_ACTOR.ACTOR_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>film_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfFilmId(lowerInclusive: Long?, upperInclusive: Long?): List<org.jooq.demo.kotlin.db.tables.pojos.FilmActor> = fetchRange(FilmActor.FILM_ACTOR.FILM_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>film_id IN (values)</code>
     */
    fun fetchByFilmId(vararg values: Long): List<org.jooq.demo.kotlin.db.tables.pojos.FilmActor> = fetch(FilmActor.FILM_ACTOR.FILM_ID, *values.toTypedArray())

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfLastUpdate(lowerInclusive: LocalDateTime?, upperInclusive: LocalDateTime?): List<org.jooq.demo.kotlin.db.tables.pojos.FilmActor> = fetchRange(FilmActor.FILM_ACTOR.LAST_UPDATE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    fun fetchByLastUpdate(vararg values: LocalDateTime): List<org.jooq.demo.kotlin.db.tables.pojos.FilmActor> = fetch(FilmActor.FILM_ACTOR.LAST_UPDATE, *values)
}
