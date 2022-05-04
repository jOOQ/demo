/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record Staff(
    Long          staffId,
    String        firstName,
    String        lastName,
    Long          addressId,
    String        email,
    Long          storeId,
    Boolean       active,
    String        username,
    String        password,
    LocalDateTime lastUpdate,
    byte[]        picture
) implements Serializable {

    private static final long serialVersionUID = 1L;

}