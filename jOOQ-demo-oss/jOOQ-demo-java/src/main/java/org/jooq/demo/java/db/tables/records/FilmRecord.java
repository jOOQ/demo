/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.records;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.demo.java.db.enums.MpaaRating;
import org.jooq.demo.java.db.tables.Film;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FilmRecord extends UpdatableRecordImpl<FilmRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.film.film_id</code>.
     */
    public void setFilmId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.film.film_id</code>.
     */
    public Long getFilmId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.film.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.film.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.film.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.film.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.film.release_year</code>.
     */
    public void setReleaseYear(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.film.release_year</code>.
     */
    public Integer getReleaseYear() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.film.language_id</code>.
     */
    public void setLanguageId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.film.language_id</code>.
     */
    public Long getLanguageId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.film.original_language_id</code>.
     */
    public void setOriginalLanguageId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.film.original_language_id</code>.
     */
    public Long getOriginalLanguageId() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>public.film.rental_duration</code>.
     */
    public void setRentalDuration(Short value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.film.rental_duration</code>.
     */
    public Short getRentalDuration() {
        return (Short) get(6);
    }

    /**
     * Setter for <code>public.film.rental_rate</code>.
     */
    public void setRentalRate(BigDecimal value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.film.rental_rate</code>.
     */
    public BigDecimal getRentalRate() {
        return (BigDecimal) get(7);
    }

    /**
     * Setter for <code>public.film.length</code>.
     */
    public void setLength(Short value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.film.length</code>.
     */
    public Short getLength() {
        return (Short) get(8);
    }

    /**
     * Setter for <code>public.film.replacement_cost</code>.
     */
    public void setReplacementCost(BigDecimal value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.film.replacement_cost</code>.
     */
    public BigDecimal getReplacementCost() {
        return (BigDecimal) get(9);
    }

    /**
     * Setter for <code>public.film.rating</code>.
     */
    public void setRating(MpaaRating value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.film.rating</code>.
     */
    public MpaaRating getRating() {
        return (MpaaRating) get(10);
    }

    /**
     * Setter for <code>public.film.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.film.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(11);
    }

    /**
     * Setter for <code>public.film.special_features</code>.
     */
    public void setSpecialFeatures(String[] value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.film.special_features</code>.
     */
    public String[] getSpecialFeatures() {
        return (String[]) get(12);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public void setFulltext(Object value) {
        set(13, value);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getFulltext() {
        return get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilmRecord
     */
    public FilmRecord() {
        super(Film.FILM);
    }

    /**
     * Create a detached, initialised FilmRecord
     */
    public FilmRecord(Long filmId, String title, String description, Integer releaseYear, Long languageId, Long originalLanguageId, Short rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost, MpaaRating rating, LocalDateTime lastUpdate, String[] specialFeatures, Object fulltext) {
        super(Film.FILM);

        setFilmId(filmId);
        setTitle(title);
        setDescription(description);
        setReleaseYear(releaseYear);
        setLanguageId(languageId);
        setOriginalLanguageId(originalLanguageId);
        setRentalDuration(rentalDuration);
        setRentalRate(rentalRate);
        setLength(length);
        setReplacementCost(replacementCost);
        setRating(rating);
        setLastUpdate(lastUpdate);
        setSpecialFeatures(specialFeatures);
        setFulltext(fulltext);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised FilmRecord
     */
    public FilmRecord(org.jooq.demo.java.db.tables.pojos.Film value) {
        super(Film.FILM);

        if (value != null) {
            setFilmId(value.filmId());
            setTitle(value.title());
            setDescription(value.description());
            setReleaseYear(value.releaseYear());
            setLanguageId(value.languageId());
            setOriginalLanguageId(value.originalLanguageId());
            setRentalDuration(value.rentalDuration());
            setRentalRate(value.rentalRate());
            setLength(value.length());
            setReplacementCost(value.replacementCost());
            setRating(value.rating());
            setLastUpdate(value.lastUpdate());
            setSpecialFeatures(value.specialFeatures());
            setFulltext(value.fulltext());
            resetTouchedOnNotNull();
        }
    }
}
