/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables.pojos


import java.io.Serializable
import java.time.LocalDateTime


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
data class Rental(
    var rentalId: Long? = null,
    var rentalDate: LocalDateTime? = null,
    var inventoryId: Long? = null,
    var customerId: Long? = null,
    var returnDate: LocalDateTime? = null,
    var staffId: Long? = null,
    var lastUpdate: LocalDateTime? = null
): Serializable {

}
