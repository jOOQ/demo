/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.demo.java.db.tables.Rental;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class RentalRecord extends UpdatableRecordImpl<RentalRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.rental.rental_id</code>.
     */
    public void setRentalId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.rental.rental_id</code>.
     */
    public Long getRentalId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.rental.rental_date</code>.
     */
    public void setRentalDate(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.rental.rental_date</code>.
     */
    public LocalDateTime getRentalDate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>public.rental.inventory_id</code>.
     */
    public void setInventoryId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.rental.inventory_id</code>.
     */
    public Long getInventoryId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.rental.customer_id</code>.
     */
    public void setCustomerId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.rental.customer_id</code>.
     */
    public Long getCustomerId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.rental.return_date</code>.
     */
    public void setReturnDate(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.rental.return_date</code>.
     */
    public LocalDateTime getReturnDate() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>public.rental.staff_id</code>.
     */
    public void setStaffId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.rental.staff_id</code>.
     */
    public Long getStaffId() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>public.rental.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.rental.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(6);
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
     * Create a detached RentalRecord
     */
    public RentalRecord() {
        super(Rental.RENTAL);
    }

    /**
     * Create a detached, initialised RentalRecord
     */
    public RentalRecord(Long rentalId, LocalDateTime rentalDate, Long inventoryId, Long customerId, LocalDateTime returnDate, Long staffId, LocalDateTime lastUpdate) {
        super(Rental.RENTAL);

        setRentalId(rentalId);
        setRentalDate(rentalDate);
        setInventoryId(inventoryId);
        setCustomerId(customerId);
        setReturnDate(returnDate);
        setStaffId(staffId);
        setLastUpdate(lastUpdate);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised RentalRecord
     */
    public RentalRecord(Long rentalId, LocalDateTime rentalDate, Long inventoryId, Long customerId, LocalDateTime returnDate, Long staffId) {
        super(Rental.RENTAL);

        setRentalId(rentalId);
        setRentalDate(rentalDate);
        setInventoryId(inventoryId);
        setCustomerId(customerId);
        setReturnDate(returnDate);
        setStaffId(staffId);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised RentalRecord
     */
    public RentalRecord(org.jooq.demo.java.db.tables.pojos.Rental value) {
        super(Rental.RENTAL);

        if (value != null) {
            setRentalId(value.rentalId());
            setRentalDate(value.rentalDate());
            setInventoryId(value.inventoryId());
            setCustomerId(value.customerId());
            setReturnDate(value.returnDate());
            setStaffId(value.staffId());
            setLastUpdate(value.lastUpdate());
            resetTouchedOnNotNull();
        }
    }
}
