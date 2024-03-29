/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.daos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.demo.java.db.tables.Category;
import org.jooq.demo.java.db.tables.records.CategoryRecord;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CategoryDao extends DAOImpl<CategoryRecord, org.jooq.demo.java.db.tables.pojos.Category, Long> {

    /**
     * Create a new CategoryDao without any configuration
     */
    public CategoryDao() {
        super(Category.CATEGORY, org.jooq.demo.java.db.tables.pojos.Category.class);
    }

    /**
     * Create a new CategoryDao with an attached configuration
     */
    public CategoryDao(Configuration configuration) {
        super(Category.CATEGORY, org.jooq.demo.java.db.tables.pojos.Category.class, configuration);
    }

    @Override
    public Long getId(org.jooq.demo.java.db.tables.pojos.Category object) {
        return object.categoryId();
    }

    /**
     * Fetch records that have <code>category_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Category> fetchRangeOfCategoryId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Category.CATEGORY.CATEGORY_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>category_id IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Category> fetchByCategoryId(Long... values) {
        return fetch(Category.CATEGORY.CATEGORY_ID, values);
    }

    /**
     * Fetch a unique record that has <code>category_id = value</code>
     */
    public org.jooq.demo.java.db.tables.pojos.Category fetchOneByCategoryId(Long value) {
        return fetchOne(Category.CATEGORY.CATEGORY_ID, value);
    }

    /**
     * Fetch a unique record that has <code>category_id = value</code>
     */
    public Optional<org.jooq.demo.java.db.tables.pojos.Category> fetchOptionalByCategoryId(Long value) {
        return fetchOptional(Category.CATEGORY.CATEGORY_ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Category> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Category.CATEGORY.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Category> fetchByName(String... values) {
        return fetch(Category.CATEGORY.NAME, values);
    }

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Category> fetchRangeOfLastUpdate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Category.CATEGORY.LAST_UPDATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<org.jooq.demo.java.db.tables.pojos.Category> fetchByLastUpdate(LocalDateTime... values) {
        return fetch(Category.CATEGORY.LAST_UPDATE, values);
    }
}
