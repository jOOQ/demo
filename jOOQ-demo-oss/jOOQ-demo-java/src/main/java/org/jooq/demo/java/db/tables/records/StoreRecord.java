/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.demo.java.db.tables.Store;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class StoreRecord extends UpdatableRecordImpl<StoreRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.store.store_id</code>.
     */
    public void setStoreId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.store.store_id</code>.
     */
    public Long getStoreId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.store.manager_staff_id</code>.
     */
    public void setManagerStaffId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.store.manager_staff_id</code>.
     */
    public Long getManagerStaffId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.store.address_id</code>.
     */
    public void setAddressId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.store.address_id</code>.
     */
    public Long getAddressId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.store.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.store.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(3);
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
     * Create a detached StoreRecord
     */
    public StoreRecord() {
        super(Store.STORE);
    }

    /**
     * Create a detached, initialised StoreRecord
     */
    public StoreRecord(Long storeId, Long managerStaffId, Long addressId, LocalDateTime lastUpdate) {
        super(Store.STORE);

        setStoreId(storeId);
        setManagerStaffId(managerStaffId);
        setAddressId(addressId);
        setLastUpdate(lastUpdate);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised StoreRecord
     */
    public StoreRecord(org.jooq.demo.java.db.tables.pojos.Store value) {
        super(Store.STORE);

        if (value != null) {
            setStoreId(value.storeId());
            setManagerStaffId(value.managerStaffId());
            setAddressId(value.addressId());
            setLastUpdate(value.lastUpdate());
            resetTouchedOnNotNull();
        }
    }
}
