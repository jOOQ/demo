/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public record FilmNotInStock(
    Integer pFilmCount
) implements Serializable {

    private static final long serialVersionUID = 1L;


    public FilmNotInStock(FilmNotInStock value) {
        this(
            value.pFilmCount
        );
    }
}
