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
data class City(
    var cityId: Long? = null,
    var city: String? = null,
    var countryId: Long? = null,
    var lastUpdate: LocalDateTime? = null
): Serializable {

}
