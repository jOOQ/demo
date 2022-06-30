package org.jooq.demo.skala

import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables._
import org.junit.Test


class Demo15ComputedColumns extends AbstractDemo {

  // Computed columns are a commercial only feature
  /* [pro] */

  @Test
  def fetchVirtualClientSideComputedColumns() {
    ctx.select(CUSTOMER.FULL_NAME, CUSTOMER.FULL_ADDRESS)
      .from(CUSTOMER)
      .fetch
  }

  /* [/pro] */

}
