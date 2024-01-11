package org.jooq.demo.scala

import org.jooq.DSLContext
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.impl._
import org.junit.Test
import org.jooq.demo.skala.db.Tables._
import org.jooq.impl.DSL.sum


class Demo17Policies extends AbstractDemo {

  @Test
  def testPolicy(): Unit = {
    title("Policies allow for filtering out data in DML statements")

    // Computed columns are a commercial only feature

//
//    // Queries run with this configuration will only be able to access CUSTOMER_ID = 1 on the CUSTOMER and PAYMENT tables
//    val c = ctx.configuration.derive(new DefaultPolicyProvider()
//      .append(CUSTOMER, CUSTOMER.CUSTOMER_ID.eq(1L))
//      .append(PAYMENT, PAYMENT.CUSTOMER_ID.eq(1L))
//    ).dsl
//
//    c.select(
//        PAYMENT.customer.FIRST_NAME,
//        PAYMENT.customer.LAST_NAME,
//      PAYMENT.AMOUNT)
//      .from(PAYMENT)
//      .fetch
//
//

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/queryparts/policies/
  }

  @Test
  def testPolicyInheritance(): Unit = {
    title("Policies can be inherited between tables")

    // Computed columns are a commercial only feature

//
//    // Queries run with this configuration will only be able to access CUSTOMER_ID = 1 on the CUSTOMER table
//    // This policy is inherited by various other tables by path specification
//    val c1 = ctx.configuration.derive(new DefaultPolicyProvider()
//      .append(CUSTOMER, CUSTOMER.CUSTOMER_ID.eq(1L),
//        PAYMENT.customer,
//        RENTAL.customer
//      )
//    ).dsl
//
//    c1.select(sum(PAYMENT.AMOUNT))
//      .from(PAYMENT)
//      .fetch
//

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/sql-building/queryparts/policies/
  }
}
