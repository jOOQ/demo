/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.records;


import java.math.BigDecimal;

import org.jooq.demo.java.db.enums.MpaaRating;
import org.jooq.demo.java.db.tables.FilmList;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FilmListRecord extends TableRecordImpl<FilmListRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.film_list.fid</code>.
     */
    public void setFid(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.film_list.fid</code>.
     */
    public Long getFid() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.film_list.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.film_list.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.film_list.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.film_list.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.film_list.category</code>.
     */
    public void setCategory(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.film_list.category</code>.
     */
    public String getCategory() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.film_list.price</code>.
     */
    public void setPrice(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.film_list.price</code>.
     */
    public BigDecimal getPrice() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>public.film_list.length</code>.
     */
    public void setLength(Short value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.film_list.length</code>.
     */
    public Short getLength() {
        return (Short) get(5);
    }

    /**
     * Setter for <code>public.film_list.rating</code>.
     */
    public void setRating(MpaaRating value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.film_list.rating</code>.
     */
    public MpaaRating getRating() {
        return (MpaaRating) get(6);
    }

    /**
     * Setter for <code>public.film_list.actors</code>.
     */
    public void setActors(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.film_list.actors</code>.
     */
    public String getActors() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilmListRecord
     */
    public FilmListRecord() {
        super(FilmList.FILM_LIST);
    }

    /**
     * Create a detached, initialised FilmListRecord
     */
    public FilmListRecord(Long fid, String title, String description, String category, BigDecimal price, Short length, MpaaRating rating, String actors) {
        super(FilmList.FILM_LIST);

        setFid(fid);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setPrice(price);
        setLength(length);
        setRating(rating);
        setActors(actors);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised FilmListRecord
     */
    public FilmListRecord(org.jooq.demo.java.db.tables.pojos.FilmList value) {
        super(FilmList.FILM_LIST);

        if (value != null) {
            setFid(value.fid());
            setTitle(value.title());
            setDescription(value.description());
            setCategory(value.category());
            setPrice(value.price());
            setLength(value.length());
            setRating(value.rating());
            setActors(value.actors());
            resetTouchedOnNotNull();
        }
    }
}
