package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.CUSTOMER
import org.jooq.demo.kotlin.db.tables.references.PAYMENT
import org.jooq.demo.kotlin.db.tables.references.RENTAL
import org.jooq.impl.DSL
import org.jooq.impl.*
import org.junit.Test

class Demo17Policies : AbstractDemo() {

    @Test
    fun testPolicy() {
        title("Policies allow for filtering out data in DML statements")

        // Computed columns are a commercial only feature
        /* [pro] */

        // Queries run with this configuration will only be able to access CUSTOMER_ID = 1 on the CUSTOMER and PAYMENT tables
        val c = ctx.configuration().derive(
            DefaultPolicyProvider()
                .append(CUSTOMER, CUSTOMER.CUSTOMER_ID.eq(1L))
                .append(PAYMENT, PAYMENT.CUSTOMER_ID.eq(1L))
        ).dsl()

        c.select(
            PAYMENT.customer().FIRST_NAME,
            PAYMENT.customer().LAST_NAME,
            PAYMENT.AMOUNT
        )
            .from(PAYMENT)
            .fetch()

        /* [/pro] */

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/queryparts/policies/
    }

    @Test
    fun testPolicyInheritance() {
        title("Policies can be inherited between tables")

        // Computed columns are a commercial only feature
        /* [pro] */

        // Queries run with this configuration will only be able to access CUSTOMER_ID = 1 on the CUSTOMER table
        // This policy is inherited by various other tables by path specification
        val c1 = ctx.configuration().derive(
            DefaultPolicyProvider()
                .append(
                    CUSTOMER, CUSTOMER.CUSTOMER_ID.eq(1L),
                    PAYMENT.customer(),
                    RENTAL.customer()
                )
        ).dsl()

        c1.select(DSL.sum(PAYMENT.AMOUNT))
            .from(PAYMENT)
            .fetch()

        /* [/pro] */

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/sql-building/queryparts/policies/
    }
}
