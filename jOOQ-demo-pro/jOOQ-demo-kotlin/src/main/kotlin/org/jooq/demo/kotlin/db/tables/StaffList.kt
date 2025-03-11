/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.kotlin.db.tables


import kotlin.collections.Collection

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.InverseForeignKey
import org.jooq.Name
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
import org.jooq.demo.kotlin.db.Public
import org.jooq.demo.kotlin.db.tables.records.StaffListRecord
import org.jooq.impl.DSL
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("warnings")
open class StaffList(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, StaffListRecord>?,
    parentPath: InverseForeignKey<out Record, StaffListRecord>?,
    aliased: Table<StaffListRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<StaffListRecord>(
    alias,
    Public.PUBLIC,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.view("""
    CREATE VIEW "staff_list" AS  SELECT s.staff_id AS id,
     (((s.first_name)::text || ' '::text) || (s.last_name)::text) AS name,
     a.address,
     a.postal_code AS "zip code",
     a.phone,
     city.city,
     country.country,
     s.store_id AS sid
    FROM (((staff s
      JOIN address a ON ((s.address_id = a.address_id)))
      JOIN city ON ((a.city_id = city.city_id)))
      JOIN country ON ((city.country_id = country.country_id)));
    """),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>public.staff_list</code>
         */
        val STAFF_LIST: StaffList = StaffList()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<StaffListRecord> = StaffListRecord::class.java

    /**
     * The column <code>public.staff_list.id</code>.
     */
    val ID: TableField<StaffListRecord, Long?> = createField(DSL.name("id"), SQLDataType.BIGINT, this, "")

    /**
     * The column <code>public.staff_list.name</code>.
     */
    val NAME: TableField<StaffListRecord, String?> = createField(DSL.name("name"), SQLDataType.CLOB, this, "")

    /**
     * The column <code>public.staff_list.address</code>.
     */
    val ADDRESS: TableField<StaffListRecord, String?> = createField(DSL.name("address"), SQLDataType.VARCHAR(50), this, "")

    /**
     * The column <code>public.staff_list.zip code</code>.
     */
    val `ZIP CODE`: TableField<StaffListRecord, String?> = createField(DSL.name("zip code"), SQLDataType.VARCHAR(10), this, "")

    /**
     * The column <code>public.staff_list.phone</code>.
     */
    val PHONE: TableField<StaffListRecord, String?> = createField(DSL.name("phone"), SQLDataType.VARCHAR(20), this, "")

    /**
     * The column <code>public.staff_list.city</code>.
     */
    val CITY: TableField<StaffListRecord, String?> = createField(DSL.name("city"), SQLDataType.VARCHAR(50), this, "")

    /**
     * The column <code>public.staff_list.country</code>.
     */
    val COUNTRY: TableField<StaffListRecord, String?> = createField(DSL.name("country"), SQLDataType.VARCHAR(50), this, "")

    /**
     * The column <code>public.staff_list.sid</code>.
     */
    val SID: TableField<StaffListRecord, Long?> = createField(DSL.name("sid"), SQLDataType.BIGINT, this, "")

    private constructor(alias: Name, aliased: Table<StaffListRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<StaffListRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<StaffListRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.staff_list</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.staff_list</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.staff_list</code> table reference
     */
    constructor(): this(DSL.name("staff_list"), null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun `as`(alias: String): StaffList = StaffList(DSL.name(alias), this)
    override fun `as`(alias: Name): StaffList = StaffList(alias, this)
    override fun `as`(alias: Table<*>): StaffList = StaffList(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): StaffList = StaffList(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): StaffList = StaffList(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): StaffList = StaffList(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): StaffList = StaffList(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): StaffList = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): StaffList = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): StaffList = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): StaffList = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): StaffList = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): StaffList = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): StaffList = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): StaffList = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): StaffList = where(DSL.notExists(select))
}
