/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.demo.java.db.tables.City;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CityRecord extends UpdatableRecordImpl<CityRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.city.city_id</code>.
     */
    public void setCityId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.city.city_id</code>.
     */
    public Long getCityId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.city.city</code>.
     */
    public void setCity(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.city.city</code>.
     */
    public String getCity() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.city.country_id</code>.
     */
    public void setCountryId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.city.country_id</code>.
     */
    public Long getCountryId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.city.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.city.last_update</code>.
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
     * Create a detached CityRecord
     */
    public CityRecord() {
        super(City.CITY);
    }

    /**
     * Create a detached, initialised CityRecord
     */
    public CityRecord(Long cityId, String city, Long countryId, LocalDateTime lastUpdate) {
        super(City.CITY);

        setCityId(cityId);
        setCity(city);
        setCountryId(countryId);
        setLastUpdate(lastUpdate);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised CityRecord
     */
    public CityRecord(Long cityId, String city, Long countryId) {
        super(City.CITY);

        setCityId(cityId);
        setCity(city);
        setCountryId(countryId);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised CityRecord
     */
    public CityRecord(org.jooq.demo.java.db.tables.pojos.City value) {
        super(City.CITY);

        if (value != null) {
            setCityId(value.cityId());
            setCity(value.city());
            setCountryId(value.countryId());
            setLastUpdate(value.lastUpdate());
            resetTouchedOnNotNull();
        }
    }
}
