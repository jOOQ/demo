/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public record Rental(
    Long rentalId,
    LocalDateTime rentalDate,
    Long inventoryId,
    Long customerId,
    LocalDateTime returnDate,
    Long staffId,
    LocalDateTime lastUpdate
) implements Serializable {

    private static final long serialVersionUID = 1L;


    public Rental(Rental value) {
        this(
            value.rentalId,
            value.rentalDate,
            value.inventoryId,
            value.customerId,
            value.returnDate,
            value.staffId,
            value.lastUpdate
        );
    }
}
