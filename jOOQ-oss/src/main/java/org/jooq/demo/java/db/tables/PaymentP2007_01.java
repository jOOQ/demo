/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableLike;
import org.jooq.TableOptions;
import org.jooq.demo.java.db.Indexes;
import org.jooq.demo.java.db.Keys;
import org.jooq.demo.java.db.Public;
import org.jooq.demo.java.db.tables.records.CustomerRecord;
import org.jooq.demo.java.db.tables.records.PaymentP2007_01Record;
import org.jooq.demo.java.db.tables.records.RentalRecord;
import org.jooq.demo.java.db.tables.records.StaffRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaymentP2007_01 extends TableImpl<PaymentP2007_01Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.payment_p2007_01</code>
     */
    public static final PaymentP2007_01 PAYMENT_P2007_01 = new PaymentP2007_01();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PaymentP2007_01Record> getRecordType() {
        return PaymentP2007_01Record.class;
    }

    /**
     * The column <code>public.payment_p2007_01.payment_id</code>.
     */
    public final TableField<PaymentP2007_01Record, Long> PAYMENT_ID = createField(DSL.name("payment_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.payment_p2007_01.customer_id</code>.
     */
    public final TableField<PaymentP2007_01Record, Long> CUSTOMER_ID = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.payment_p2007_01.staff_id</code>.
     */
    public final TableField<PaymentP2007_01Record, Long> STAFF_ID = createField(DSL.name("staff_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.payment_p2007_01.rental_id</code>.
     */
    public final TableField<PaymentP2007_01Record, Long> RENTAL_ID = createField(DSL.name("rental_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.payment_p2007_01.amount</code>.
     */
    public final TableField<PaymentP2007_01Record, BigDecimal> AMOUNT = createField(DSL.name("amount"), SQLDataType.NUMERIC(5, 2).nullable(false), this, "");

    /**
     * The column <code>public.payment_p2007_01.payment_date</code>.
     */
    public final TableField<PaymentP2007_01Record, LocalDateTime> PAYMENT_DATE = createField(DSL.name("payment_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private PaymentP2007_01(Name alias, Table<PaymentP2007_01Record> aliased) {
        this(alias, aliased, null);
    }

    private PaymentP2007_01(Name alias, Table<PaymentP2007_01Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.payment_p2007_01</code> table reference
     */
    public PaymentP2007_01(String alias) {
        this(DSL.name(alias), PAYMENT_P2007_01);
    }

    /**
     * Create an aliased <code>public.payment_p2007_01</code> table reference
     */
    public PaymentP2007_01(Name alias) {
        this(alias, PAYMENT_P2007_01);
    }

    /**
     * Create a <code>public.payment_p2007_01</code> table reference
     */
    public PaymentP2007_01() {
        this(DSL.name("payment_p2007_01"), null);
    }

    public <O extends Record> PaymentP2007_01(Table<O> child, ForeignKey<O, PaymentP2007_01Record> key) {
        super(child, key, PAYMENT_P2007_01);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_FK_PAYMENT_P2007_01_CUSTOMER_ID, Indexes.IDX_FK_PAYMENT_P2007_01_STAFF_ID);
    }

    @Override
    public Identity<PaymentP2007_01Record, Long> getIdentity() {
        return (Identity<PaymentP2007_01Record, Long>) super.getIdentity();
    }

    @Override
    public List<ForeignKey<PaymentP2007_01Record, ?>> getReferences() {
        return Arrays.asList(Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_CUSTOMER_ID_FKEY, Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_STAFF_ID_FKEY, Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_RENTAL_ID_FKEY);
    }

    private transient Customer _customer;
    private transient Staff _staff;
    private transient Rental _rental;

    /**
     * Get the implicit join path to the <code>public.customer</code> table.
     */
    public Customer customer() {
        if (_customer == null)
            _customer = new Customer(this, Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_CUSTOMER_ID_FKEY);

        return _customer;
    }

    /**
     * Get the implicit join path to the <code>public.staff</code> table.
     */
    public Staff staff() {
        if (_staff == null)
            _staff = new Staff(this, Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_STAFF_ID_FKEY);

        return _staff;
    }

    /**
     * Get the implicit join path to the <code>public.rental</code> table.
     */
    public Rental rental() {
        if (_rental == null)
            _rental = new Rental(this, Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_RENTAL_ID_FKEY);

        return _rental;
    }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.payment_p2007_01</code> to-one parent table.
     */
    public Field<CustomerRecord> customerRow() {
        return customerRow(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.payment_p2007_01</code> to-one parent table.
     */
    public <O extends Record> Field<O> customerRow(Function<? super Customer, ? extends TableLike<O>> subquery) {
        return toOneRow(Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_CUSTOMER_ID_FKEY, t -> subquery.apply((Customer) t));
    }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.payment_p2007_01</code> to-one parent table.
     */
    public Field<StaffRecord> staffRow() {
        return staffRow(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.payment_p2007_01</code> to-one parent table.
     */
    public <O extends Record> Field<O> staffRow(Function<? super Staff, ? extends TableLike<O>> subquery) {
        return toOneRow(Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_STAFF_ID_FKEY, t -> subquery.apply((Staff) t));
    }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.payment_p2007_01</code> to-one parent table.
     */
    public Field<RentalRecord> rentalRow() {
        return rentalRow(Function.identity());
    }

    /**
     * A convenience constructor for correlated <code>ROW</code>s expressions to
     * the <code>public.payment_p2007_01</code> to-one parent table.
     */
    public <O extends Record> Field<O> rentalRow(Function<? super Rental, ? extends TableLike<O>> subquery) {
        return toOneRow(Keys.PAYMENT_P2007_01__PAYMENT_P2007_01_RENTAL_ID_FKEY, t -> subquery.apply((Rental) t));
    }

    @Override
    public List<Check<PaymentP2007_01Record>> getChecks() {
        return Arrays.asList(
            Internal.createCheck(this, DSL.name("payment_p2007_01_payment_date_check"), "(((payment_date >= '2007-01-01 00:00:00'::timestamp without time zone) AND (payment_date < '2007-02-01 00:00:00'::timestamp without time zone)))", true)
        );
    }

    @Override
    public PaymentP2007_01 as(String alias) {
        return new PaymentP2007_01(DSL.name(alias), this);
    }

    @Override
    public PaymentP2007_01 as(Name alias) {
        return new PaymentP2007_01(alias, this);
    }

    @Override
    public PaymentP2007_01 as(Table<?> alias) {
        return new PaymentP2007_01(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PaymentP2007_01 rename(String name) {
        return new PaymentP2007_01(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PaymentP2007_01 rename(Name name) {
        return new PaymentP2007_01(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PaymentP2007_01 rename(Table<?> name) {
        return new PaymentP2007_01(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, Long, Long, BigDecimal, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Long, ? super Long, ? super Long, ? super Long, ? super BigDecimal, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Long, ? super Long, ? super Long, ? super Long, ? super BigDecimal, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
