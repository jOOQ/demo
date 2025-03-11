package org.jooq.demo.skala

import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables._
import org.junit.Test


class Demo15ComputedColumns extends AbstractDemo {

  @Test
  def fetchVirtualClientSideComputedColumns() = {
    title("Client side computed columns emulate their server side equivalents by inlining the computation expression")

    // Computed columns are a commercial only feature
    /* [pro] */

    ctx.select(STAFF.FULL_NAME, STAFF.FULL_ADDRESS)
      .from(STAFF)
      .fetch
    /* [/pro] */

    // More information here:
    // - https://www.jooq.org/doc/latest/manual/code-generation/codegen-advanced/codegen-config-database/codegen-database-forced-types/codegen-database-forced-types-computed/
  }

}
