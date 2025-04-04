/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.records


import java.math.BigDecimal
import java.time.LocalDateTime

import org.jooq.Record1
import org.jooq.demo.kotlin.db.enums.MpaaRating
import org.jooq.demo.kotlin.db.tables.Film
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class FilmRecord() : UpdatableRecordImpl<FilmRecord>(Film.FILM) {

    open var filmId: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open var title: String?
        set(value): Unit = set(1, value)
        get(): String? = get(1) as String?

    open var description: String?
        set(value): Unit = set(2, value)
        get(): String? = get(2) as String?

    open var releaseYear: Int?
        set(value): Unit = set(3, value)
        get(): Int? = get(3) as Int?

    open var languageId: Long?
        set(value): Unit = set(4, value)
        get(): Long? = get(4) as Long?

    open var originalLanguageId: Long?
        set(value): Unit = set(5, value)
        get(): Long? = get(5) as Long?

    open var rentalDuration: Short?
        set(value): Unit = set(6, value)
        get(): Short? = get(6) as Short?

    open var rentalRate: BigDecimal?
        set(value): Unit = set(7, value)
        get(): BigDecimal? = get(7) as BigDecimal?

    open var length: Short?
        set(value): Unit = set(8, value)
        get(): Short? = get(8) as Short?

    open var replacementCost: BigDecimal?
        set(value): Unit = set(9, value)
        get(): BigDecimal? = get(9) as BigDecimal?

    open var rating: MpaaRating?
        set(value): Unit = set(10, value)
        get(): MpaaRating? = get(10) as MpaaRating?

    open var lastUpdate: LocalDateTime?
        set(value): Unit = set(11, value)
        get(): LocalDateTime? = get(11) as LocalDateTime?

    open var specialFeatures: Array<String?>?
        set(value): Unit = set(12, value)
        get(): Array<String?>? = get(12) as Array<String?>?

    open var fulltext: Any?
        set(value): Unit = set(13, value)
        get(): Any? = get(13) as Any?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    /**
     * Create a detached, initialised FilmRecord
     */
    constructor(filmId: Long? = null, title: String? = null, description: String? = null, releaseYear: Int? = null, languageId: Long? = null, originalLanguageId: Long? = null, rentalDuration: Short? = null, rentalRate: BigDecimal? = null, length: Short? = null, replacementCost: BigDecimal? = null, rating: MpaaRating? = null, lastUpdate: LocalDateTime? = null, specialFeatures: Array<String?>? = null, fulltext: Any? = null): this() {
        this.filmId = filmId
        this.title = title
        this.description = description
        this.releaseYear = releaseYear
        this.languageId = languageId
        this.originalLanguageId = originalLanguageId
        this.rentalDuration = rentalDuration
        this.rentalRate = rentalRate
        this.length = length
        this.replacementCost = replacementCost
        this.rating = rating
        this.lastUpdate = lastUpdate
        this.specialFeatures = specialFeatures
        this.fulltext = fulltext
        resetTouchedOnNotNull()
    }

    /**
     * Create a detached, initialised FilmRecord
     */
    constructor(filmId: Long? = null, title: String? = null, description: String? = null, releaseYear: Int? = null, languageId: Long? = null, originalLanguageId: Long? = null, rentalDuration: Short? = null, rentalRate: BigDecimal? = null, length: Short? = null, replacementCost: BigDecimal? = null, rating: MpaaRating? = null, specialFeatures: Array<String?>? = null, fulltext: Any? = null): this() {
        this.filmId = filmId
        this.title = title
        this.description = description
        this.releaseYear = releaseYear
        this.languageId = languageId
        this.originalLanguageId = originalLanguageId
        this.rentalDuration = rentalDuration
        this.rentalRate = rentalRate
        this.length = length
        this.replacementCost = replacementCost
        this.rating = rating
        this.specialFeatures = specialFeatures
        this.fulltext = fulltext
        resetTouchedOnNotNull()
    }

    /**
     * Create a detached, initialised FilmRecord
     */
    constructor(value: org.jooq.demo.kotlin.db.tables.pojos.Film?): this() {
        if (value != null) {
            this.filmId = value.filmId
            this.title = value.title
            this.description = value.description
            this.releaseYear = value.releaseYear
            this.languageId = value.languageId
            this.originalLanguageId = value.originalLanguageId
            this.rentalDuration = value.rentalDuration
            this.rentalRate = value.rentalRate
            this.length = value.length
            this.replacementCost = value.replacementCost
            this.rating = value.rating
            this.lastUpdate = value.lastUpdate
            this.specialFeatures = value.specialFeatures
            this.fulltext = value.fulltext
            resetTouchedOnNotNull()
        }
    }
}
