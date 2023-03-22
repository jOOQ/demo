package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.*
import org.junit.Test


class Demo15ComputedColumns : AbstractDemo() {

    // Computed columns are a commercial only feature

//
//    @Test
//    fun fetchVirtualClientSideComputedColumns() {
//        ctx.select(
//                STAFF.FULL_NAME,
//                STAFF.FULL_ADDRESS
//            )
//            .from(STAFF)
//            .fetch()
//    }
//
//
}
