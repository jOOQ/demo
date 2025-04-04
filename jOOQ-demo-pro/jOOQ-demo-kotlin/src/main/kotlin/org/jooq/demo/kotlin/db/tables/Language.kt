/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import java.time.LocalDateTime

import kotlin.collections.Collection

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.QueryPart
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.keys.FILM__FILM_LANGUAGE_ID_FKEY
import org.jooq.demo.kotlin.db.keys.FILM__FILM_ORIGINAL_LANGUAGE_ID_FKEY
import org.jooq.demo.kotlin.db.keys.LANGUAGE_PKEY
import org.jooq.demo.kotlin.db.tables.Film.FilmPath
import org.jooq.demo.kotlin.db.tables.records.LanguageRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class Language(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, LanguageRecord>?,
    parentPath: InverseForeignKey<out Record, LanguageRecord>?,
    aliased: Table<LanguageRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<LanguageRecord>(
    alias,
    Public.PUBLIC,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table(),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>public.language</code>
         */
        val LANGUAGE: Language = Language()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<LanguageRecord> = LanguageRecord::class.java

    /**
     * The column <code>public.language.language_id</code>.
     */
    val LANGUAGE_ID: TableField<LanguageRecord, Long?> = createField(DSL.name("language_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.language.name</code>.
     */
    val NAME: TableField<LanguageRecord, String?> = createField(DSL.name("name"), SQLDataType.CHAR(20).nullable(false), this, "")

    /**
     * The column <code>public.language.last_update</code>.
     */
    val LAST_UPDATE: TableField<LanguageRecord, LocalDateTime?> = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "")

    private constructor(alias: Name, aliased: Table<LanguageRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<LanguageRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<LanguageRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.language</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.language</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.language</code> table reference
     */
    constructor(): this(DSL.name("language"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, LanguageRecord>?, parentPath: InverseForeignKey<out Record, LanguageRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, LANGUAGE, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class LanguagePath : Language, Path<LanguageRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, LanguageRecord>?, parentPath: InverseForeignKey<out Record, LanguageRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<LanguageRecord>): super(alias, aliased)
        override fun `as`(alias: String): LanguagePath = LanguagePath(DSL.name(alias), this)
        override fun `as`(alias: Name): LanguagePath = LanguagePath(alias, this)
        override fun `as`(alias: Table<*>): LanguagePath = LanguagePath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<LanguageRecord, Long?> = super.getIdentity() as Identity<LanguageRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<LanguageRecord> = LANGUAGE_PKEY

    private lateinit var _filmLanguageIdFkey: FilmPath

    /**
     * Get the implicit to-many join path to the <code>public.film</code> table,
     * via the <code>film_language_id_fkey</code> key
     */
    fun filmLanguageIdFkey(): FilmPath {
        if (!this::_filmLanguageIdFkey.isInitialized)
            _filmLanguageIdFkey = FilmPath(this, null, FILM__FILM_LANGUAGE_ID_FKEY.inverseKey)

        return _filmLanguageIdFkey;
    }

    val filmLanguageIdFkey: FilmPath
        get(): FilmPath = filmLanguageIdFkey()

    private lateinit var _filmOriginalLanguageIdFkey: FilmPath

    /**
     * Get the implicit to-many join path to the <code>public.film</code> table,
     * via the <code>film_original_language_id_fkey</code> key
     */
    fun filmOriginalLanguageIdFkey(): FilmPath {
        if (!this::_filmOriginalLanguageIdFkey.isInitialized)
            _filmOriginalLanguageIdFkey = FilmPath(this, null, FILM__FILM_ORIGINAL_LANGUAGE_ID_FKEY.inverseKey)

        return _filmOriginalLanguageIdFkey;
    }

    val filmOriginalLanguageIdFkey: FilmPath
        get(): FilmPath = filmOriginalLanguageIdFkey()
    override fun `as`(alias: String): Language = Language(DSL.name(alias), this)
    override fun `as`(alias: Name): Language = Language(alias, this)
    override fun `as`(alias: Table<*>): Language = Language(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Language = Language(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Language = Language(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Language = Language(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): Language = Language(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): Language = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): Language = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): Language = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): Language = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): Language = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): Language = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): Language = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): Language = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): Language = where(DSL.notExists(select))
}
