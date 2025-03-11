/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.demo.java.db.Indexes;
import org.jooq.demo.java.db.Keys;
import org.jooq.demo.java.db.Public;
import org.jooq.demo.java.db.tables.Address.AddressPath;
import org.jooq.demo.java.db.tables.Customer.CustomerPath;
import org.jooq.demo.java.db.tables.Inventory.InventoryPath;
import org.jooq.demo.java.db.tables.Staff.StaffPath;
import org.jooq.demo.java.db.tables.records.StoreRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Store extends TableImpl<StoreRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.store</code>
     */
    public static final Store STORE = new Store();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StoreRecord> getRecordType() {
        return StoreRecord.class;
    }

    /**
     * The column <code>public.store.store_id</code>.
     */
    public final TableField<StoreRecord, Long> STORE_ID = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.store.manager_staff_id</code>.
     */
    public final TableField<StoreRecord, Long> MANAGER_STAFF_ID = createField(DSL.name("manager_staff_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.store.address_id</code>.
     */
    public final TableField<StoreRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.store.last_update</code>.
     */
    public final TableField<StoreRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.store.full_address</code>.
     */
    public final TableField<StoreRecord, String> FULL_ADDRESS = createField(DSL.name("full_address"), SQLDataType.CLOB.virtual(), this, "", ctx -> DSL.concat(address().ADDRESS_, DSL.inline(", "), address().POSTAL_CODE, DSL.inline(", "), address().city().CITY_, DSL.inline(", "), address().city().country().COUNTRY_));

    private Store(Name alias, Table<StoreRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Store(Name alias, Table<StoreRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.store</code> table reference
     */
    public Store(String alias) {
        this(DSL.name(alias), STORE);
    }

    /**
     * Create an aliased <code>public.store</code> table reference
     */
    public Store(Name alias) {
        this(alias, STORE);
    }

    /**
     * Create a <code>public.store</code> table reference
     */
    public Store() {
        this(DSL.name("store"), null);
    }

    public <O extends Record> Store(Table<O> path, ForeignKey<O, StoreRecord> childPath, InverseForeignKey<O, StoreRecord> parentPath) {
        super(path, childPath, parentPath, STORE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class StorePath extends Store implements Path<StoreRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> StorePath(Table<O> path, ForeignKey<O, StoreRecord> childPath, InverseForeignKey<O, StoreRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private StorePath(Name alias, Table<StoreRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public StorePath as(String alias) {
            return new StorePath(DSL.name(alias), this);
        }

        @Override
        public StorePath as(Name alias) {
            return new StorePath(alias, this);
        }

        @Override
        public StorePath as(Table<?> alias) {
            return new StorePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_UNQ_MANAGER_STAFF_ID);
    }

    @Override
    public Identity<StoreRecord, Long> getIdentity() {
        return (Identity<StoreRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<StoreRecord> getPrimaryKey() {
        return Keys.STORE_PKEY;
    }

    @Override
    public List<ForeignKey<StoreRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STORE__STORE_ADDRESS_ID_FKEY, Keys.STORE__STORE_MANAGER_STAFF_ID_FKEY);
    }

    private transient AddressPath _address;

    /**
     * Get the implicit join path to the <code>public.address</code> table.
     */
    public AddressPath address() {
        if (_address == null)
            _address = new AddressPath(this, Keys.STORE__STORE_ADDRESS_ID_FKEY, null);

        return _address;
    }

    private transient StaffPath _staff;

    /**
     * Get the implicit join path to the <code>public.staff</code> table.
     */
    public StaffPath staff() {
        if (_staff == null)
            _staff = new StaffPath(this, Keys.STORE__STORE_MANAGER_STAFF_ID_FKEY, null);

        return _staff;
    }

    private transient CustomerPath _customer;

    /**
     * Get the implicit to-many join path to the <code>public.customer</code>
     * table
     */
    public CustomerPath customer() {
        if (_customer == null)
            _customer = new CustomerPath(this, null, Keys.CUSTOMER__CUSTOMER_STORE_ID_FKEY.getInverseKey());

        return _customer;
    }

    private transient InventoryPath _inventory;

    /**
     * Get the implicit to-many join path to the <code>public.inventory</code>
     * table
     */
    public InventoryPath inventory() {
        if (_inventory == null)
            _inventory = new InventoryPath(this, null, Keys.INVENTORY__INVENTORY_STORE_ID_FKEY.getInverseKey());

        return _inventory;
    }

    @Override
    public Store as(String alias) {
        return new Store(DSL.name(alias), this);
    }

    @Override
    public Store as(Name alias) {
        return new Store(alias, this);
    }

    @Override
    public Store as(Table<?> alias) {
        return new Store(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Store rename(String name) {
        return new Store(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Store rename(Name name) {
        return new Store(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Store rename(Table<?> name) {
        return new Store(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Store where(Condition condition) {
        return new Store(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Store where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Store where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Store where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Store where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Store where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Store where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Store where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Store whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Store whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
