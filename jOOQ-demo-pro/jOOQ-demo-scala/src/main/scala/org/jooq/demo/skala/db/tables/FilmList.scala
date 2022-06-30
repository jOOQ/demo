/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables


import java.lang.Class
import java.lang.Long
import java.lang.Short
import java.lang.String
import java.math.BigDecimal
import java.util.function.Function

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row8
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.demo.skala.db.Public
import org.jooq.demo.skala.db.enums.MpaaRating
import org.jooq.demo.skala.db.tables.records.FilmListRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object FilmList {

  /**
   * The reference instance of <code>public.film_list</code>
   */
  val FILM_LIST = new FilmList
}

/**
 * This class is generated by jOOQ.
 */
class FilmList(
  alias: Name,
  child: Table[_ <: Record],
  path: ForeignKey[_ <: Record, FilmListRecord],
  aliased: Table[FilmListRecord],
  parameters: Array[ Field[_] ]
)
extends TableImpl[FilmListRecord](
  alias,
  Public.PUBLIC,
  child,
  path,
  aliased,
  parameters,
  DSL.comment(""),
  TableOptions.view("""
  create view "film_list" as  SELECT film.film_id AS fid,
    film.title,
    film.description,
    category.name AS category,
    film.rental_rate AS price,
    film.length,
    film.rating,
    group_concat((((actor.first_name)::text || ' '::text) || (actor.last_name)::text)) AS actors
   FROM ((((category
     LEFT JOIN film_category ON ((category.category_id = film_category.category_id)))
     LEFT JOIN film ON ((film_category.film_id = film.film_id)))
     JOIN film_actor ON ((film.film_id = film_actor.film_id)))
     JOIN actor ON ((film_actor.actor_id = actor.actor_id)))
  GROUP BY film.film_id, film.title, film.description, category.name, film.rental_rate, film.length, film.rating;
  """)
) {

  /**
   * The class holding records for this type
   */
  override def getRecordType: Class[FilmListRecord] = classOf[FilmListRecord]

  /**
   * The column <code>public.film_list.fid</code>.
   */
  val FID: TableField[FilmListRecord, Long] = createField(DSL.name("fid"), SQLDataType.BIGINT, "")

  /**
   * The column <code>public.film_list.title</code>.
   */
  val TITLE: TableField[FilmListRecord, String] = createField(DSL.name("title"), SQLDataType.VARCHAR(255), "")

  /**
   * The column <code>public.film_list.description</code>.
   */
  val DESCRIPTION: TableField[FilmListRecord, String] = createField(DSL.name("description"), SQLDataType.CLOB, "")

  /**
   * The column <code>public.film_list.category</code>.
   */
  val CATEGORY: TableField[FilmListRecord, String] = createField(DSL.name("category"), SQLDataType.VARCHAR(25), "")

  /**
   * The column <code>public.film_list.price</code>.
   */
  val PRICE: TableField[FilmListRecord, BigDecimal] = createField(DSL.name("price"), SQLDataType.NUMERIC(4, 2), "")

  /**
   * The column <code>public.film_list.length</code>.
   */
  val LENGTH: TableField[FilmListRecord, Short] = createField(DSL.name("length"), SQLDataType.SMALLINT, "")

  /**
   * The column <code>public.film_list.rating</code>.
   */
  val RATING: TableField[FilmListRecord, MpaaRating] = createField(DSL.name("rating"), SQLDataType.VARCHAR.asEnumDataType(classOf[org.jooq.demo.skala.db.enums.MpaaRating]), "")

  /**
   * The column <code>public.film_list.actors</code>.
   */
  val ACTORS: TableField[FilmListRecord, String] = createField(DSL.name("actors"), SQLDataType.CLOB, "")

  private def this(alias: Name, aliased: Table[FilmListRecord]) = this(alias, null, null, aliased, null)

  /**
   * Create an aliased <code>public.film_list</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.FilmList.FILM_LIST)

  /**
   * Create an aliased <code>public.film_list</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.FilmList.FILM_LIST)

  /**
   * Create a <code>public.film_list</code> table reference
   */
  def this() = this(DSL.name("film_list"), null)

  def this(child: Table[_ <: Record], key: ForeignKey[_ <: Record, FilmListRecord]) = this(Internal.createPathAlias(child, key), child, key, org.jooq.demo.skala.db.tables.FilmList.FILM_LIST, null)

  override def getSchema: Schema = if (aliased()) null else Public.PUBLIC
  override def as(alias: String): FilmList = new FilmList(DSL.name(alias), this)
  override def as(alias: Name): FilmList = new FilmList(alias, this)
  override def as(alias: Table[_]): FilmList = new FilmList(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): FilmList = new FilmList(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): FilmList = new FilmList(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[_]): FilmList = new FilmList(name.getQualifiedName(), null)

  // -------------------------------------------------------------------------
  // Row8 type methods
  // -------------------------------------------------------------------------
  override def fieldsRow: Row8[Long, String, String, String, BigDecimal, Short, MpaaRating, String] = super.fieldsRow.asInstanceOf[ Row8[Long, String, String, String, BigDecimal, Short, MpaaRating, String] ]

  /**
   * Convenience mapping calling {@link #convertFrom(Function)}.
   */
  def mapping[U](from: (Long, String, String, String, BigDecimal, Short, MpaaRating, String) => U): SelectField[U] = convertFrom(r => from.apply(r.value1(), r.value2(), r.value3(), r.value4(), r.value5(), r.value6(), r.value7(), r.value8()))

  /**
   * Convenience mapping calling {@link #convertFrom(Class, Function)}.
   */
  def mapping[U](toType: Class[U], from: (Long, String, String, String, BigDecimal, Short, MpaaRating, String) => U): SelectField[U] = convertFrom(toType,r => from.apply(r.value1(), r.value2(), r.value3(), r.value4(), r.value5(), r.value6(), r.value7(), r.value8()))
}
