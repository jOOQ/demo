/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.indexes


import org.jooq.Index
import org.jooq.demo.kotlin.db.tables.Actor
import org.jooq.demo.kotlin.db.tables.Address
import org.jooq.demo.kotlin.db.tables.City
import org.jooq.demo.kotlin.db.tables.Customer
import org.jooq.demo.kotlin.db.tables.Film
import org.jooq.demo.kotlin.db.tables.FilmActor
import org.jooq.demo.kotlin.db.tables.Inventory
import org.jooq.demo.kotlin.db.tables.Payment
import org.jooq.demo.kotlin.db.tables.PaymentP2007_01
import org.jooq.demo.kotlin.db.tables.PaymentP2007_02
import org.jooq.demo.kotlin.db.tables.PaymentP2007_03
import org.jooq.demo.kotlin.db.tables.PaymentP2007_04
import org.jooq.demo.kotlin.db.tables.PaymentP2007_05
import org.jooq.demo.kotlin.db.tables.PaymentP2007_06
import org.jooq.demo.kotlin.db.tables.Rental
import org.jooq.demo.kotlin.db.tables.Store
import org.jooq.impl.DSL
import org.jooq.impl.Internal



// -------------------------------------------------------------------------
// INDEX definitions
// -------------------------------------------------------------------------

val FILM_FULLTEXT_IDX: Index = Internal.createIndex(DSL.name("film_fulltext_idx"), Film.FILM, arrayOf(Film.FILM.FULLTEXT), false)
val IDX_ACTOR_LAST_NAME: Index = Internal.createIndex(DSL.name("idx_actor_last_name"), Actor.ACTOR, arrayOf(Actor.ACTOR.LAST_NAME), false)
val IDX_FK_ADDRESS_ID: Index = Internal.createIndex(DSL.name("idx_fk_address_id"), Customer.CUSTOMER, arrayOf(Customer.CUSTOMER.ADDRESS_ID), false)
val IDX_FK_CITY_ID: Index = Internal.createIndex(DSL.name("idx_fk_city_id"), Address.ADDRESS, arrayOf(Address.ADDRESS.CITY_ID), false)
val IDX_FK_COUNTRY_ID: Index = Internal.createIndex(DSL.name("idx_fk_country_id"), City.CITY, arrayOf(City.CITY.COUNTRY_ID), false)
val IDX_FK_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_customer_id"), Payment.PAYMENT, arrayOf(Payment.PAYMENT.CUSTOMER_ID), false)
val IDX_FK_FILM_ID: Index = Internal.createIndex(DSL.name("idx_fk_film_id"), FilmActor.FILM_ACTOR, arrayOf(FilmActor.FILM_ACTOR.FILM_ID), false)
val IDX_FK_INVENTORY_ID: Index = Internal.createIndex(DSL.name("idx_fk_inventory_id"), Rental.RENTAL, arrayOf(Rental.RENTAL.INVENTORY_ID), false)
val IDX_FK_LANGUAGE_ID: Index = Internal.createIndex(DSL.name("idx_fk_language_id"), Film.FILM, arrayOf(Film.FILM.LANGUAGE_ID), false)
val IDX_FK_ORIGINAL_LANGUAGE_ID: Index = Internal.createIndex(DSL.name("idx_fk_original_language_id"), Film.FILM, arrayOf(Film.FILM.ORIGINAL_LANGUAGE_ID), false)
val IDX_FK_PAYMENT_P2007_01_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_01_customer_id"), PaymentP2007_01.PAYMENT_P2007_01, arrayOf(PaymentP2007_01.PAYMENT_P2007_01.CUSTOMER_ID), false)
val IDX_FK_PAYMENT_P2007_01_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_01_staff_id"), PaymentP2007_01.PAYMENT_P2007_01, arrayOf(PaymentP2007_01.PAYMENT_P2007_01.STAFF_ID), false)
val IDX_FK_PAYMENT_P2007_02_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_02_customer_id"), PaymentP2007_02.PAYMENT_P2007_02, arrayOf(PaymentP2007_02.PAYMENT_P2007_02.CUSTOMER_ID), false)
val IDX_FK_PAYMENT_P2007_02_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_02_staff_id"), PaymentP2007_02.PAYMENT_P2007_02, arrayOf(PaymentP2007_02.PAYMENT_P2007_02.STAFF_ID), false)
val IDX_FK_PAYMENT_P2007_03_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_03_customer_id"), PaymentP2007_03.PAYMENT_P2007_03, arrayOf(PaymentP2007_03.PAYMENT_P2007_03.CUSTOMER_ID), false)
val IDX_FK_PAYMENT_P2007_03_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_03_staff_id"), PaymentP2007_03.PAYMENT_P2007_03, arrayOf(PaymentP2007_03.PAYMENT_P2007_03.STAFF_ID), false)
val IDX_FK_PAYMENT_P2007_04_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_04_customer_id"), PaymentP2007_04.PAYMENT_P2007_04, arrayOf(PaymentP2007_04.PAYMENT_P2007_04.CUSTOMER_ID), false)
val IDX_FK_PAYMENT_P2007_04_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_04_staff_id"), PaymentP2007_04.PAYMENT_P2007_04, arrayOf(PaymentP2007_04.PAYMENT_P2007_04.STAFF_ID), false)
val IDX_FK_PAYMENT_P2007_05_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_05_customer_id"), PaymentP2007_05.PAYMENT_P2007_05, arrayOf(PaymentP2007_05.PAYMENT_P2007_05.CUSTOMER_ID), false)
val IDX_FK_PAYMENT_P2007_05_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_05_staff_id"), PaymentP2007_05.PAYMENT_P2007_05, arrayOf(PaymentP2007_05.PAYMENT_P2007_05.STAFF_ID), false)
val IDX_FK_PAYMENT_P2007_06_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_06_customer_id"), PaymentP2007_06.PAYMENT_P2007_06, arrayOf(PaymentP2007_06.PAYMENT_P2007_06.CUSTOMER_ID), false)
val IDX_FK_PAYMENT_P2007_06_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_payment_p2007_06_staff_id"), PaymentP2007_06.PAYMENT_P2007_06, arrayOf(PaymentP2007_06.PAYMENT_P2007_06.STAFF_ID), false)
val IDX_FK_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_fk_staff_id"), Payment.PAYMENT, arrayOf(Payment.PAYMENT.STAFF_ID), false)
val IDX_FK_STORE_ID: Index = Internal.createIndex(DSL.name("idx_fk_store_id"), Customer.CUSTOMER, arrayOf(Customer.CUSTOMER.STORE_ID), false)
val IDX_LAST_NAME: Index = Internal.createIndex(DSL.name("idx_last_name"), Customer.CUSTOMER, arrayOf(Customer.CUSTOMER.LAST_NAME), false)
val IDX_STORE_ID_FILM_ID: Index = Internal.createIndex(DSL.name("idx_store_id_film_id"), Inventory.INVENTORY, arrayOf(Inventory.INVENTORY.STORE_ID, Inventory.INVENTORY.FILM_ID), false)
val IDX_TITLE: Index = Internal.createIndex(DSL.name("idx_title"), Film.FILM, arrayOf(Film.FILM.TITLE), false)
val IDX_UNQ_MANAGER_STAFF_ID: Index = Internal.createIndex(DSL.name("idx_unq_manager_staff_id"), Store.STORE, arrayOf(Store.STORE.MANAGER_STAFF_ID), true)
val IDX_UNQ_RENTAL_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID: Index = Internal.createIndex(DSL.name("idx_unq_rental_rental_date_inventory_id_customer_id"), Rental.RENTAL, arrayOf(Rental.RENTAL.RENTAL_DATE, Rental.RENTAL.INVENTORY_ID, Rental.RENTAL.CUSTOMER_ID), true)
