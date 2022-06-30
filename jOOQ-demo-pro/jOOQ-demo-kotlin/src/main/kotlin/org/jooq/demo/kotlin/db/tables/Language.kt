/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import java.time.LocalDateTime
import java.util.function.Function

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Records
import org.jooq.Row3
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.keys.LANGUAGE_PKEY
import org.jooq.demo.kotlin.db.tables.records.LanguageRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Language(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, LanguageRecord>?,
    aliased: Table<LanguageRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<LanguageRecord>(
    alias,
    Public.PUBLIC,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
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
    val LAST_UPDATE: TableField<LanguageRecord, LocalDateTime?> = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(6).nullable(false).readonly(true).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "")

    private constructor(alias: Name, aliased: Table<LanguageRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<LanguageRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

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

    constructor(child: Table<out Record>, key: ForeignKey<out Record, LanguageRecord>): this(Internal.createPathAlias(child, key), child, key, LANGUAGE, null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<LanguageRecord, Long?> = super.getIdentity() as Identity<LanguageRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<LanguageRecord> = LANGUAGE_PKEY
    override fun `as`(alias: String): Language = Language(DSL.name(alias), this)
    override fun `as`(alias: Name): Language = Language(alias, this)
    override fun `as`(alias: Table<*>): Language = Language(alias.getQualifiedName(), this)

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
    override fun rename(name: Table<*>): Language = Language(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row3<Long?, String?, LocalDateTime?> = super.fieldsRow() as Row3<Long?, String?, LocalDateTime?>

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, String?, LocalDateTime?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, String?, LocalDateTime?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}
