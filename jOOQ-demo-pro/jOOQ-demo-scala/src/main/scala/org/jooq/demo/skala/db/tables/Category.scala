/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.skala.db.tables


import java.lang.Boolean
import java.lang.Class
import java.lang.Long
import java.lang.String
import java.time.LocalDateTime
import java.util.Collection

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.skala.db.Keys
import org.jooq.demo.skala.db.Public
import org.jooq.demo.skala.db.tables.Film.FilmPath
import org.jooq.demo.skala.db.tables.FilmCategory.FilmCategoryPath
import org.jooq.demo.skala.db.tables.records.CategoryRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object Category {

  /**
   * The reference instance of <code>public.category</code>
   */
  val CATEGORY = new Category

  /**
   * A subtype implementing {@link Path} for simplified path-based joins.
   */
  class CategoryPath(path: Table[? <: Record], childPath: ForeignKey[? <: Record, CategoryRecord], parentPath: InverseForeignKey[? <: Record, CategoryRecord]) extends Category(path, childPath, parentPath) with Path[CategoryRecord]
}

/**
 * This class is generated by jOOQ.
 */
class Category(
  alias: Name,
  path: Table[? <: Record],
  childPath: ForeignKey[? <: Record, CategoryRecord],
  parentPath: InverseForeignKey[? <: Record, CategoryRecord],
  aliased: Table[CategoryRecord],
  parameters: Array[ Field[?] ],
  where: Condition
)
extends TableImpl[CategoryRecord](
  alias,
  Public.PUBLIC,
  path,
  childPath,
  parentPath,
  aliased,
  parameters,
  DSL.comment(""),
  TableOptions.table,
  where
) {

  /**
   * The class holding records for this type
   */
  override def getRecordType: Class[CategoryRecord] = classOf[CategoryRecord]

  /**
   * The column <code>public.category.category_id</code>.
   */
  val CATEGORY_ID: TableField[CategoryRecord, Long] = createField(DSL.name("category_id"), SQLDataType.BIGINT.nullable(false).identity(true), "")

  /**
   * The column <code>public.category.name</code>.
   */
  val NAME: TableField[CategoryRecord, String] = createField(DSL.name("name"), SQLDataType.VARCHAR(25).nullable(false), "")

  /**
   * The column <code>public.category.last_update</code>.
   */
  val LAST_UPDATE: TableField[CategoryRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[CategoryRecord]) = this(alias, null, null, null, aliased, null, null)
  private def this(alias: Name, aliased: Table[CategoryRecord], where: Condition) = this(alias, null, null, null, aliased, null, where)

  /**
   * Create an aliased <code>public.category</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.Category.CATEGORY)

  /**
   * Create an aliased <code>public.category</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.Category.CATEGORY)

  /**
   * Create a <code>public.category</code> table reference
   */
  def this() = this(DSL.name("category"), null)

  def this(path: Table[? <: Record], childPath: ForeignKey[? <: Record, CategoryRecord], parentPath: InverseForeignKey[? <: Record, CategoryRecord]) = this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, org.jooq.demo.skala.db.tables.Category.CATEGORY, null, null)

  override def getSchema: Schema = if (super.aliased()) null else Public.PUBLIC

  override def getIdentity: Identity[CategoryRecord, Long] = super.getIdentity.asInstanceOf[ Identity[CategoryRecord, Long] ]

  override def getPrimaryKey: UniqueKey[CategoryRecord] = Keys.CATEGORY_PKEY

  /**
   * Get the implicit to-many join path to the <code>public.film_category</code>
   * table
   */
  lazy val filmCategory: FilmCategoryPath = { new FilmCategoryPath(this, null, Keys.FILM_CATEGORY__FILM_CATEGORY_CATEGORY_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit many-to-many join path to the <code>public.film</code>
   * table
   */
  def film: FilmPath = filmCategory.film
  override def as(alias: String): Category = new Category(DSL.name(alias), this)
  override def as(alias: Name): Category = new Category(alias, this)
  override def as(alias: Table[?]): Category = new Category(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): Category = new Category(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): Category = new Category(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[?]): Category = new Category(name.getQualifiedName(), null)

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Condition): Category = new Category(getQualifiedName(), if (super.aliased()) this else null, condition)

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Collection[? <: Condition]): Category = where(DSL.and(conditions))

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Condition*): Category = where(DSL.and(conditions*))

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Field[Boolean]): Category = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(condition: SQL): Category = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String): Category = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String, binds: AnyRef*): Category = where(DSL.condition(condition, binds*))

  /**
   * Create an inline derived table from this table
   */
  override def whereExists(select: Select[?]): Category = where(DSL.exists(select))

  /**
   * Create an inline derived table from this table
   */
  override def whereNotExists(select: Select[?]): Category = where(DSL.notExists(select))
}
