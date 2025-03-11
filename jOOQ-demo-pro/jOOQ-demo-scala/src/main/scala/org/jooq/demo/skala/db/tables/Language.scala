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
import org.jooq.demo.skala.db.tables.records.LanguageRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

import scala.Array


object Language {

  /**
   * The reference instance of <code>public.language</code>
   */
  val LANGUAGE = new Language

  /**
   * A subtype implementing {@link Path} for simplified path-based joins.
   */
  class LanguagePath(path: Table[? <: Record], childPath: ForeignKey[? <: Record, LanguageRecord], parentPath: InverseForeignKey[? <: Record, LanguageRecord]) extends Language(path, childPath, parentPath) with Path[LanguageRecord]
}

/**
 * This class is generated by jOOQ.
 */
class Language(
  alias: Name,
  path: Table[? <: Record],
  childPath: ForeignKey[? <: Record, LanguageRecord],
  parentPath: InverseForeignKey[? <: Record, LanguageRecord],
  aliased: Table[LanguageRecord],
  parameters: Array[ Field[?] ],
  where: Condition
)
extends TableImpl[LanguageRecord](
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
  override def getRecordType: Class[LanguageRecord] = classOf[LanguageRecord]

  /**
   * The column <code>public.language.language_id</code>.
   */
  val LANGUAGE_ID: TableField[LanguageRecord, Long] = createField(DSL.name("language_id"), SQLDataType.BIGINT.nullable(false).identity(true), "")

  /**
   * The column <code>public.language.name</code>.
   */
  val NAME: TableField[LanguageRecord, String] = createField(DSL.name("name"), SQLDataType.CHAR(20).nullable(false), "")

  /**
   * The column <code>public.language.last_update</code>.
   */
  val LAST_UPDATE: TableField[LanguageRecord, LocalDateTime] = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), "")

  private def this(alias: Name, aliased: Table[LanguageRecord]) = this(alias, null, null, null, aliased, null, null)
  private def this(alias: Name, aliased: Table[LanguageRecord], where: Condition) = this(alias, null, null, null, aliased, null, where)

  /**
   * Create an aliased <code>public.language</code> table reference
   */
  def this(alias: String) = this(DSL.name(alias), org.jooq.demo.skala.db.tables.Language.LANGUAGE)

  /**
   * Create an aliased <code>public.language</code> table reference
   */
  def this(alias: Name) = this(alias, org.jooq.demo.skala.db.tables.Language.LANGUAGE)

  /**
   * Create a <code>public.language</code> table reference
   */
  def this() = this(DSL.name("language"), null)

  def this(path: Table[? <: Record], childPath: ForeignKey[? <: Record, LanguageRecord], parentPath: InverseForeignKey[? <: Record, LanguageRecord]) = this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, org.jooq.demo.skala.db.tables.Language.LANGUAGE, null, null)

  override def getSchema: Schema = if (super.aliased()) null else Public.PUBLIC

  override def getIdentity: Identity[LanguageRecord, Long] = super.getIdentity.asInstanceOf[ Identity[LanguageRecord, Long] ]

  override def getPrimaryKey: UniqueKey[LanguageRecord] = Keys.LANGUAGE_PKEY

  /**
   * Get the implicit to-many join path to the <code>public.film</code> table,
   * via the <code>film_language_id_fkey</code> key
   */
  lazy val filmLanguageIdFkey: FilmPath = { new FilmPath(this, null, Keys.FILM__FILM_LANGUAGE_ID_FKEY.getInverseKey()) }

  /**
   * Get the implicit to-many join path to the <code>public.film</code> table,
   * via the <code>film_original_language_id_fkey</code> key
   */
  lazy val filmOriginalLanguageIdFkey: FilmPath = { new FilmPath(this, null, Keys.FILM__FILM_ORIGINAL_LANGUAGE_ID_FKEY.getInverseKey()) }
  override def as(alias: String): Language = new Language(DSL.name(alias), this)
  override def as(alias: Name): Language = new Language(alias, this)
  override def as(alias: Table[?]): Language = new Language(alias.getQualifiedName(), this)

  /**
   * Rename this table
   */
  override def rename(name: String): Language = new Language(DSL.name(name), null)

  /**
   * Rename this table
   */
  override def rename(name: Name): Language = new Language(name, null)

  /**
   * Rename this table
   */
  override def rename(name: Table[?]): Language = new Language(name.getQualifiedName(), null)

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Condition): Language = new Language(getQualifiedName(), if (super.aliased()) this else null, condition)

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Collection[? <: Condition]): Language = where(DSL.and(conditions))

  /**
   * Create an inline derived table from this table
   */
  override def where(conditions: Condition*): Language = where(DSL.and(conditions*))

  /**
   * Create an inline derived table from this table
   */
  override def where(condition: Field[Boolean]): Language = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(condition: SQL): Language = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String): Language = where(DSL.condition(condition))

  /**
   * Create an inline derived table from this table
   */
  @PlainSQL override def where(@Stringly.SQL condition: String, binds: AnyRef*): Language = where(DSL.condition(condition, binds*))

  /**
   * Create an inline derived table from this table
   */
  override def whereExists(select: Select[?]): Language = where(DSL.exists(select))

  /**
   * Create an inline derived table from this table
   */
  override def whereNotExists(select: Select[?]): Language = where(DSL.notExists(select))
}
