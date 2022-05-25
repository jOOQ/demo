/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.daos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.demo.java.db.tables.Store;
import org.jooq.demo.java.db.tables.records.StoreRecord;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StoreDao extends DAOImpl<StoreRecord, org.jooq.demo.java.db.tables.pojos.Store, Long> {

    /**
     * Create a new StoreDao without any configuration
     */
    public StoreDao() {
        super(Store.STORE, org.jooq.demo.java.db.tables.pojos.Store.class);
    }

    /**
     * Create a new StoreDao with an attached configuration
     */
    public StoreDao(Configuration configuration) {
        super(Store.STORE, org.jooq.demo.java.db.tables.pojos.Store.class, configuration);
    }

    @Override
    public Long getId(org.jooq.demo.java.db.tables.pojos.Store object) {
        return object.storeId();
    }

    /**
     * Fetch records that have <code>store_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchRangeOfStoreId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Store.STORE.STORE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>store_id IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchByStoreId(Long... values) {
        return fetch(Store.STORE.STORE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>store_id = value</code>
     */
    public org.jooq.demo.java.db.tables.pojos.Store fetchOneByStoreId(Long value) {
        return fetchOne(Store.STORE.STORE_ID, value);
    }

    /**
     * Fetch a unique record that has <code>store_id = value</code>
     */
    public Optional<org.jooq.demo.java.db.tables.pojos.Store> fetchOptionalByStoreId(Long value) {
        return fetchOptional(Store.STORE.STORE_ID, value);
    }

    /**
     * Fetch records that have <code>manager_staff_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchRangeOfManagerStaffId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Store.STORE.MANAGER_STAFF_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>manager_staff_id IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchByManagerStaffId(Long... values) {
        return fetch(Store.STORE.MANAGER_STAFF_ID, values);
    }

    /**
     * Fetch records that have <code>address_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchRangeOfAddressId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Store.STORE.ADDRESS_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>address_id IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchByAddressId(Long... values) {
        return fetch(Store.STORE.ADDRESS_ID, values);
    }

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchRangeOfLastUpdate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Store.STORE.LAST_UPDATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Store> fetchByLastUpdate(LocalDateTime... values) {
        return fetch(Store.STORE.LAST_UPDATE, values);
    }
}
