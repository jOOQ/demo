package org.jooq.demo.kotlin

import org.jooq.ChartFormat
import org.jooq.JSONFormat
import org.jooq.TXTFormat
import org.jooq.XMLFormat
import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.jooq.demo.kotlin.db.tables.references.PAYMENT
import org.jooq.impl.DSL
import org.jooq.impl.DSL.sum
import org.jooq.impl.SQLDataType
import org.junit.After
import org.junit.Test

class Demo08ImportExportAndMore : AbstractDemo() {

    @Test
    fun importExportCSV() {
        title("Importing data from CSV content")
        ctx.loadInto(ACTOR)

            // Throttling flags
            .bulkAfter(2)
            .batchAfter(2)

            // Duplicate handling flags
            .onDuplicateKeyError()

            // Source specification
            .loadCSV(
               """
               id;last_name;first_name
               201;Doe;John
               202;Smith;Jane
               203;Colson;Wilda
               204;Abel;Izzy
               205;Langdon;Cayson
               206;Brooke;Deon
               207;Wolf;Gabriella
               208;Edie;Drew
               209;Rupert;Maeghan
               210;Coleman;Skyler
               """)
            .fields(ACTOR.ACTOR_ID, ACTOR.LAST_NAME, ACTOR.FIRST_NAME)
            .ignoreRows(1)
            .separator(';')
            .quote('"')
            .execute()

        title("Exporting data to CSV content")
        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)).formatCSV())
    }

    @Test
    fun importExportJSON() {
        title("Importing data from JSON content")
        ctx.loadInto(ACTOR)
            .batchAll()
            .loadJSON(
               """
               [
                 { "id": 201, "lastName": "Doe", "firstName": "John" },
                 { "id": 202, "lastName": "Smith", "firstName": "Jane" },
                 { "id": 203, "lastName": "Colson", "firstName": "Wilda" },
                 { "id": 204, "lastName": "Abel", "firstName": "Izzy" },
                 { "id": 205, "lastName": "Langdon", "firstName": "Cayson" },
                 { "id": 206, "lastName": "Brooke", "firstName": "Deon" },
                 { "id": 207, "lastName": "Wolf", "firstName": "Gabriella" },
                 { "id": 208, "lastName": "Edie", "firstName": "Drew" },
                 { "id": 209, "lastName": "Rupert", "firstName": "Maeghan" },
                 { "id": 210, "lastName": "Coleman", "firstName": "Skyler" }
               ]
               """)
            .fields(ACTOR.ACTOR_ID, ACTOR.LAST_NAME, ACTOR.FIRST_NAME)
            .execute()

        println(
            ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L))
                .formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.header(false).format(true))
        )
    }

    @Test
    fun importExportJavaData() {
        title("Importing data from Java in-memory content")
        ctx.loadInto(ACTOR)
            .batchAll()
            .loadArrays(
                arrayOf(201, "Doe", "John"),
                arrayOf(202, "Smith", "Jane"),
                arrayOf(203, "Colson", "Wilda"),
                arrayOf(204, "Abel", "Izzy"),
                arrayOf(205, "Langdon", "Cayson"),
                arrayOf(206, "Brooke", "Deon"),
                arrayOf(207, "Wolf", "Gabriella"),
                arrayOf(208, "Edie", "Drew"),
                arrayOf(209, "Rupert", "Maeghan"),
                arrayOf(210, "Coleman", "Skyler")
            )
            .fields(ACTOR.ACTOR_ID, ACTOR.LAST_NAME, ACTOR.FIRST_NAME)
            .execute()

        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)))
    }

    @Test
    fun exportXML() {
        // No import supported yet.
        val result = ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L))
        val xmlformat = XMLFormat.DEFAULT_FOR_RESULTS.header(false).format(true)

        title("XML export default record format is VALUE_ELEMENTS_WITH_FIELD_ATTRIBUTE")
        println(result.formatXML(xmlformat))

        title("COLUMN_NAME_ELEMENTS record format")
        println(result.formatXML(xmlformat.recordFormat(XMLFormat.RecordFormat.COLUMN_NAME_ELEMENTS)))

        title("VALUE_ELEMENTS record format")
        println(result.formatXML(xmlformat.recordFormat(XMLFormat.RecordFormat.VALUE_ELEMENTS)))
    }

    @Test
    fun exportText() {
        // No import supported yet.
        val result = ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L))
        title("The default text format is also used when DEBUG logging things")
        println(result.format())

        title("Different ASCII table formatting styles are available")
        println(
            result.format(
                TXTFormat.DEFAULT
                    .horizontalHeaderBorder(false)
                    .intersectLines(false)
                    .verticalTableBorder(false)
                    .minColWidth(20)
            )
        )
    }

    @Test
    fun exportHTML() {
        title("Could be good enough")
        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L)).formatHTML())
    }

    @Test
    fun exportChart() {
        val date = DSL.cast(PAYMENT.PAYMENT_DATE, SQLDataType.DATE)
        title("Hey, why not! 🤩")
        println(
            ctx
                .select(date, sum(PAYMENT.AMOUNT))
                .from(PAYMENT)
                .groupBy(date)
                .orderBy(date)
                .fetch()
                .formatChart()
        )
        title("The envy of MS Excel's pivot tables")
        println(
            ctx
                .select(
                    date,
                    sum(sum(PAYMENT.AMOUNT).filterWhere(PAYMENT.staff.STORE_ID.eq(1L)))
                        .over(DSL.orderBy(date)),
                    sum(sum(PAYMENT.AMOUNT).filterWhere(PAYMENT.staff.STORE_ID.eq(2L)))
                        .over(DSL.orderBy(date))
                )
                .from(PAYMENT)
                .groupBy(date)
                .orderBy(date)
                .fetch()
                .formatChart(ChartFormat.DEFAULT
                    .values(1, 2)
                    .display(ChartFormat.Display.STACKED)
                    .shades('#', '.')
                )
        )
    }

    @After
    override fun teardown() {
        cleanup()
        super.teardown()
    }
}