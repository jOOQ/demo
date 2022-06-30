/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.pojos


import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class Payment(
    var paymentId: Long? = null,
    var customerId: Long? = null,
    var staffId: Long? = null,
    var rentalId: Long? = null,
    var amount: BigDecimal? = null,
    var paymentDate: LocalDateTime? = null
): Serializable {

}
