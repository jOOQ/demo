/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.records;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.demo.java.db.tables.PaymentP2007_02;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PaymentP2007_02Record extends TableRecordImpl<PaymentP2007_02Record> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.payment_p2007_02.payment_id</code>.
     */
    public void setPaymentId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.payment_p2007_02.payment_id</code>.
     */
    public Long getPaymentId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.payment_p2007_02.customer_id</code>.
     */
    public void setCustomerId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.payment_p2007_02.customer_id</code>.
     */
    public Long getCustomerId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.payment_p2007_02.staff_id</code>.
     */
    public void setStaffId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.payment_p2007_02.staff_id</code>.
     */
    public Long getStaffId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.payment_p2007_02.rental_id</code>.
     */
    public void setRentalId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.payment_p2007_02.rental_id</code>.
     */
    public Long getRentalId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.payment_p2007_02.amount</code>.
     */
    public void setAmount(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.payment_p2007_02.amount</code>.
     */
    public BigDecimal getAmount() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>public.payment_p2007_02.payment_date</code>.
     */
    public void setPaymentDate(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.payment_p2007_02.payment_date</code>.
     */
    public LocalDateTime getPaymentDate() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PaymentP2007_02Record
     */
    public PaymentP2007_02Record() {
        super(PaymentP2007_02.PAYMENT_P2007_02);
    }

    /**
     * Create a detached, initialised PaymentP2007_02Record
     */
    public PaymentP2007_02Record(Long paymentId, Long customerId, Long staffId, Long rentalId, BigDecimal amount, LocalDateTime paymentDate) {
        super(PaymentP2007_02.PAYMENT_P2007_02);

        setPaymentId(paymentId);
        setCustomerId(customerId);
        setStaffId(staffId);
        setRentalId(rentalId);
        setAmount(amount);
        setPaymentDate(paymentDate);
        resetTouchedOnNotNull();
    }

    /**
     * Create a detached, initialised PaymentP2007_02Record
     */
    public PaymentP2007_02Record(org.jooq.demo.java.db.tables.pojos.PaymentP2007_02 value) {
        super(PaymentP2007_02.PAYMENT_P2007_02);

        if (value != null) {
            setPaymentId(value.paymentId());
            setCustomerId(value.customerId());
            setStaffId(value.staffId());
            setRentalId(value.rentalId());
            setAmount(value.amount());
            setPaymentDate(value.paymentDate());
            resetTouchedOnNotNull();
        }
    }
}
