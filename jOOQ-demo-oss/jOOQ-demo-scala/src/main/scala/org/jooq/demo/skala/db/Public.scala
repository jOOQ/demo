/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db


import java.lang.Integer
import java.lang.Long
import java.math.BigDecimal
import java.util.Arrays
import java.util.List

import org.jooq.Catalog
import org.jooq.Configuration
import org.jooq.Domain
import org.jooq.Field
import org.jooq.Result
import org.jooq.Table
import org.jooq.demo.skala.db.tables.Actor
import org.jooq.demo.skala.db.tables.ActorInfo
import org.jooq.demo.skala.db.tables.Address
import org.jooq.demo.skala.db.tables.Category
import org.jooq.demo.skala.db.tables.City
import org.jooq.demo.skala.db.tables.Country
import org.jooq.demo.skala.db.tables.Customer
import org.jooq.demo.skala.db.tables.CustomerList
import org.jooq.demo.skala.db.tables.Film
import org.jooq.demo.skala.db.tables.FilmActor
import org.jooq.demo.skala.db.tables.FilmCategory
import org.jooq.demo.skala.db.tables.FilmInStock
import org.jooq.demo.skala.db.tables.FilmList
import org.jooq.demo.skala.db.tables.FilmNotInStock
import org.jooq.demo.skala.db.tables.Inventory
import org.jooq.demo.skala.db.tables.Language
import org.jooq.demo.skala.db.tables.NicerButSlowerFilmList
import org.jooq.demo.skala.db.tables.Payment
import org.jooq.demo.skala.db.tables.PaymentP2007_01
import org.jooq.demo.skala.db.tables.PaymentP2007_02
import org.jooq.demo.skala.db.tables.PaymentP2007_03
import org.jooq.demo.skala.db.tables.PaymentP2007_04
import org.jooq.demo.skala.db.tables.PaymentP2007_05
import org.jooq.demo.skala.db.tables.PaymentP2007_06
import org.jooq.demo.skala.db.tables.Rental
import org.jooq.demo.skala.db.tables.RewardsReport
import org.jooq.demo.skala.db.tables.SalesByFilmCategory
import org.jooq.demo.skala.db.tables.SalesByStore
import org.jooq.demo.skala.db.tables.Staff
import org.jooq.demo.skala.db.tables.StaffList
import org.jooq.demo.skala.db.tables.Store
import org.jooq.demo.skala.db.tables.records.CustomerRecord
import org.jooq.demo.skala.db.tables.records.FilmInStockRecord
import org.jooq.demo.skala.db.tables.records.FilmNotInStockRecord
import org.jooq.impl.DSL
import org.jooq.impl.SchemaImpl


object Public {

  /**
   * The reference instance of <code>public</code>
   */
  val PUBLIC = new Public
}

/**
 * Standard public schema
 */
class Public extends SchemaImpl(DSL.name("public"), DefaultCatalog.DEFAULT_CATALOG, DSL.comment("Standard public schema")) {

  /**
   * The table <code>public.actor</code>.
   */
  def ACTOR = Actor.ACTOR

  /**
   * The table <code>public.actor_info</code>.
   */
  def ACTOR_INFO = ActorInfo.ACTOR_INFO

  /**
   * The table <code>public.address</code>.
   */
  def ADDRESS = Address.ADDRESS

  /**
   * The table <code>public.category</code>.
   */
  def CATEGORY = Category.CATEGORY

  /**
   * The table <code>public.city</code>.
   */
  def CITY = City.CITY

  /**
   * The table <code>public.country</code>.
   */
  def COUNTRY = Country.COUNTRY

  /**
   * The table <code>public.customer</code>.
   */
  def CUSTOMER = Customer.CUSTOMER

  /**
   * The table <code>public.customer_list</code>.
   */
  def CUSTOMER_LIST = CustomerList.CUSTOMER_LIST

  /**
   * The table <code>public.film</code>.
   */
  def FILM = Film.FILM

  /**
   * The table <code>public.film_actor</code>.
   */
  def FILM_ACTOR = FilmActor.FILM_ACTOR

  /**
   * The table <code>public.film_category</code>.
   */
  def FILM_CATEGORY = FilmCategory.FILM_CATEGORY

  /**
   * The table <code>public.film_in_stock</code>.
   */
  def FILM_IN_STOCK = FilmInStock.FILM_IN_STOCK

  /**
   * Call <code>public.film_in_stock</code>.
   */
  def FILM_IN_STOCK(
      configuration: Configuration
    , pFilmId: Long
    , pStoreId: Long
  ): Result[FilmInStockRecord] = configuration.dsl().selectFrom(org.jooq.demo.skala.db.tables.FilmInStock.FILM_IN_STOCK.call(
      pFilmId
    , pStoreId
  )).fetch()

  /**
   * Get <code>public.film_in_stock</code> as a table.
   */
  def FILM_IN_STOCK(
      pFilmId: Long
    , pStoreId: Long
  ): FilmInStock = org.jooq.demo.skala.db.tables.FilmInStock.FILM_IN_STOCK.call(
    pFilmId,
    pStoreId
  )

  /**
   * Get <code>public.film_in_stock</code> as a table.
   */
  def FILM_IN_STOCK(
      pFilmId: Field[Long]
    , pStoreId: Field[Long]
  ): FilmInStock = org.jooq.demo.skala.db.tables.FilmInStock.FILM_IN_STOCK.call(
    pFilmId,
    pStoreId
  )

  /**
   * The table <code>public.film_list</code>.
   */
  def FILM_LIST = FilmList.FILM_LIST

  /**
   * The table <code>public.film_not_in_stock</code>.
   */
  def FILM_NOT_IN_STOCK = FilmNotInStock.FILM_NOT_IN_STOCK

  /**
   * Call <code>public.film_not_in_stock</code>.
   */
  def FILM_NOT_IN_STOCK(
      configuration: Configuration
    , pFilmId: Long
    , pStoreId: Long
  ): Result[FilmNotInStockRecord] = configuration.dsl().selectFrom(org.jooq.demo.skala.db.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(
      pFilmId
    , pStoreId
  )).fetch()

  /**
   * Get <code>public.film_not_in_stock</code> as a table.
   */
  def FILM_NOT_IN_STOCK(
      pFilmId: Long
    , pStoreId: Long
  ): FilmNotInStock = org.jooq.demo.skala.db.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(
    pFilmId,
    pStoreId
  )

  /**
   * Get <code>public.film_not_in_stock</code> as a table.
   */
  def FILM_NOT_IN_STOCK(
      pFilmId: Field[Long]
    , pStoreId: Field[Long]
  ): FilmNotInStock = org.jooq.demo.skala.db.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(
    pFilmId,
    pStoreId
  )

  /**
   * The table <code>public.inventory</code>.
   */
  def INVENTORY = Inventory.INVENTORY

  /**
   * The table <code>public.language</code>.
   */
  def LANGUAGE = Language.LANGUAGE

  /**
   * The table <code>public.nicer_but_slower_film_list</code>.
   */
  def NICER_BUT_SLOWER_FILM_LIST = NicerButSlowerFilmList.NICER_BUT_SLOWER_FILM_LIST

  /**
   * The table <code>public.payment</code>.
   */
  def PAYMENT = Payment.PAYMENT

  /**
   * The table <code>public.payment_p2007_01</code>.
   */
  def PAYMENT_P2007_01 = PaymentP2007_01.PAYMENT_P2007_01

  /**
   * The table <code>public.payment_p2007_02</code>.
   */
  def PAYMENT_P2007_02 = PaymentP2007_02.PAYMENT_P2007_02

  /**
   * The table <code>public.payment_p2007_03</code>.
   */
  def PAYMENT_P2007_03 = PaymentP2007_03.PAYMENT_P2007_03

  /**
   * The table <code>public.payment_p2007_04</code>.
   */
  def PAYMENT_P2007_04 = PaymentP2007_04.PAYMENT_P2007_04

  /**
   * The table <code>public.payment_p2007_05</code>.
   */
  def PAYMENT_P2007_05 = PaymentP2007_05.PAYMENT_P2007_05

  /**
   * The table <code>public.payment_p2007_06</code>.
   */
  def PAYMENT_P2007_06 = PaymentP2007_06.PAYMENT_P2007_06

  /**
   * The table <code>public.rental</code>.
   */
  def RENTAL = Rental.RENTAL

  /**
   * The table <code>public.rewards_report</code>.
   */
  def REWARDS_REPORT = RewardsReport.REWARDS_REPORT

  /**
   * Call <code>public.rewards_report</code>.
   */
  def REWARDS_REPORT(
      configuration: Configuration
    , minMonthlyPurchases: Integer
    , minDollarAmountPurchased: BigDecimal
  ): Result[CustomerRecord] = configuration.dsl().selectFrom(org.jooq.demo.skala.db.tables.RewardsReport.REWARDS_REPORT.call(
      minMonthlyPurchases
    , minDollarAmountPurchased
  )).fetch()

  /**
   * Get <code>public.rewards_report</code> as a table.
   */
  def REWARDS_REPORT(
      minMonthlyPurchases: Integer
    , minDollarAmountPurchased: BigDecimal
  ): RewardsReport = org.jooq.demo.skala.db.tables.RewardsReport.REWARDS_REPORT.call(
    minMonthlyPurchases,
    minDollarAmountPurchased
  )

  /**
   * Get <code>public.rewards_report</code> as a table.
   */
  def REWARDS_REPORT(
      minMonthlyPurchases: Field[Integer]
    , minDollarAmountPurchased: Field[BigDecimal]
  ): RewardsReport = org.jooq.demo.skala.db.tables.RewardsReport.REWARDS_REPORT.call(
    minMonthlyPurchases,
    minDollarAmountPurchased
  )

  /**
   * The table <code>public.sales_by_film_category</code>.
   */
  def SALES_BY_FILM_CATEGORY = SalesByFilmCategory.SALES_BY_FILM_CATEGORY

  /**
   * The table <code>public.sales_by_store</code>.
   */
  def SALES_BY_STORE = SalesByStore.SALES_BY_STORE

  /**
   * The table <code>public.staff</code>.
   */
  def STAFF = Staff.STAFF

  /**
   * The table <code>public.staff_list</code>.
   */
  def STAFF_LIST = StaffList.STAFF_LIST

  /**
   * The table <code>public.store</code>.
   */
  def STORE = Store.STORE

  override def getCatalog: Catalog = DefaultCatalog.DEFAULT_CATALOG

  override def getDomains: List[Domain[?]] = Arrays.asList[Domain[?]](
    Domains.YEAR
  )

  override def getTables: List[Table[?]] = Arrays.asList[Table[?]](
    Actor.ACTOR,
    ActorInfo.ACTOR_INFO,
    Address.ADDRESS,
    Category.CATEGORY,
    City.CITY,
    Country.COUNTRY,
    Customer.CUSTOMER,
    CustomerList.CUSTOMER_LIST,
    Film.FILM,
    FilmActor.FILM_ACTOR,
    FilmCategory.FILM_CATEGORY,
    FilmInStock.FILM_IN_STOCK,
    FilmList.FILM_LIST,
    FilmNotInStock.FILM_NOT_IN_STOCK,
    Inventory.INVENTORY,
    Language.LANGUAGE,
    NicerButSlowerFilmList.NICER_BUT_SLOWER_FILM_LIST,
    Payment.PAYMENT,
    PaymentP2007_01.PAYMENT_P2007_01,
    PaymentP2007_02.PAYMENT_P2007_02,
    PaymentP2007_03.PAYMENT_P2007_03,
    PaymentP2007_04.PAYMENT_P2007_04,
    PaymentP2007_05.PAYMENT_P2007_05,
    PaymentP2007_06.PAYMENT_P2007_06,
    Rental.RENTAL,
    RewardsReport.REWARDS_REPORT,
    SalesByFilmCategory.SALES_BY_FILM_CATEGORY,
    SalesByStore.SALES_BY_STORE,
    Staff.STAFF,
    StaffList.STAFF_LIST,
    Store.STORE
  )
}
