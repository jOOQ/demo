/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public record StaffList(
    Long id,
    String name,
    String address,
    String zipCode,
    String phone,
    String city,
    String country,
    Long sid
) implements Serializable {

    private static final long serialVersionUID = 1L;

}
