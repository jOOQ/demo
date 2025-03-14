/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.routines


import java.math.BigDecimal
import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.Parameter
import org.jooq.demo.kotlin.db.Public
import org.jooq.impl.AbstractRoutine
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class GetCustomerBalance : AbstractRoutine<BigDecimal>("get_customer_balance", Public.PUBLIC, DSL.comment(""), SQLDataType.NUMERIC) {
    companion object {

        /**
         * The parameter <code>public.get_customer_balance.RETURN_VALUE</code>.
         */
        val RETURN_VALUE: Parameter<BigDecimal?> = Internal.createParameter("RETURN_VALUE", SQLDataType.NUMERIC, false, false)

        /**
         * The parameter <code>public.get_customer_balance.p_customer_id</code>.
         */
        val P_CUSTOMER_ID: Parameter<Long?> = Internal.createParameter("p_customer_id", SQLDataType.BIGINT, false, false)

        /**
         * The parameter
         * <code>public.get_customer_balance.p_effective_date</code>.
         */
        val P_EFFECTIVE_DATE: Parameter<LocalDateTime?> = Internal.createParameter("p_effective_date", SQLDataType.LOCALDATETIME(6), false, false)
    }

    init {
        returnParameter = GetCustomerBalance.RETURN_VALUE
        addInParameter(GetCustomerBalance.P_CUSTOMER_ID)
        addInParameter(GetCustomerBalance.P_EFFECTIVE_DATE)
    }

    /**
     * Set the <code>p_customer_id</code> parameter IN value to the routine
     */
    fun setPCustomerId(value: Long?): Unit = setValue(GetCustomerBalance.P_CUSTOMER_ID, value)

    /**
     * Set the <code>p_customer_id</code> parameter to the function to be used
     * with a {@link org.jooq.Select} statement
     */
    fun setPCustomerId(field: Field<Long?>): Unit {
        setField(GetCustomerBalance.P_CUSTOMER_ID, field)
    }

    /**
     * Set the <code>p_effective_date</code> parameter IN value to the routine
     */
    fun setPEffectiveDate(value: LocalDateTime?): Unit = setValue(GetCustomerBalance.P_EFFECTIVE_DATE, value)

    /**
     * Set the <code>p_effective_date</code> parameter to the function to be
     * used with a {@link org.jooq.Select} statement
     */
    fun setPEffectiveDate(field: Field<LocalDateTime?>): Unit {
        setField(GetCustomerBalance.P_EFFECTIVE_DATE, field)
    }
}
