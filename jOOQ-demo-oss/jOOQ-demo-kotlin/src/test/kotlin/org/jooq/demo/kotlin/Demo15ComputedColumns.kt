package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.CUSTOMER
import org.junit.Test


class Demo15ComputedColumns : AbstractDemo() {

    // Computed columns are a commercial only feature

//
//    @Test
//    fun fetchVirtualClientSideComputedColumns() {
//        ctx.select(
//                CUSTOMER.FULL_NAME,
//                CUSTOMER.FULL_ADDRESS
//            )
//            .from(CUSTOMER)
//            .fetch()
//    }
//
//
}
