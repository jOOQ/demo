package org.jooq.demo.java;

import org.jooq.*;
import org.jooq.demo.java.db.tables.records.ActorRecord;
import org.jooq.demo.java.db.Tables;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import static org.jooq.Records.mapping;
import static org.jooq.demo.java.db.Tables.ACTOR;
import static org.jooq.demo.java.db.Tables.PAYMENT;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.DATE;

public class Demo08ImportExport extends AbstractDemo {

    @Test
    public void importExportCSV() throws IOException {
        title("Importing data from CSV content");
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
           .execute();

        title("Exporting data to CSV content");
        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)).formatCSV());
    }

    @Test
    public void importExportJSON() throws IOException {
        title("Importing data from JSON content");
        ctx.loadInto(ACTOR)
           .batchAll()
           .loadJSON(
               """
               [
                 { "id": 201, "firstName": "Doe", "lastName": "John" },
                 { "id": 202, "firstName": "Smith", "lastName": "Jane" },
                 { "id": 203, "firstName": "Colson", "lastName": "Wilda" },
                 { "id": 204, "firstName": "Abel", "lastName": "Izzy" },
                 { "id": 205, "firstName": "Langdon", "lastName": "Cayson" },
                 { "id": 206, "firstName": "Brooke", "lastName": "Deon" },
                 { "id": 207, "firstName": "Wolf", "lastName": "Gabriella" },
                 { "id": 208, "firstName": "Edie", "lastName": "Drew" },
                 { "id": 209, "firstName": "Rupert", "lastName": "Maeghan" },
                 { "id": 210, "firstName": "Coleman", "lastName": "Skyler" }
               ]
               """)
           .fields(ACTOR.ACTOR_ID, ACTOR.LAST_NAME, ACTOR.FIRST_NAME)
           .execute();

        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)).formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.header(false).format(true)));
    }

    @Test
    public void importExportJavaData() throws IOException {
        title("Importing data from Java in-memory content");
        ctx.loadInto(ACTOR)
           .batchAll()
           .loadArrays(new Object[][] {
               { 201, "Doe", "John" },
               { 202, "Smith", "Jane" },
               { 203, "Colson", "Wilda" },
               { 204, "Abel", "Izzy" },
               { 205, "Langdon", "Cayson" },
               { 206, "Brooke", "Deon" },
               { 207, "Wolf", "Gabriella" },
               { 208, "Edie", "Drew" },
               { 209, "Rupert", "Maeghan" },
               { 210, "Coleman", "Skyler" }
            })
           .fields(ACTOR.ACTOR_ID, ACTOR.LAST_NAME, ACTOR.FIRST_NAME)
           .execute();
        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)));
    }

    @Test
    public void exportXML() {
        // No import supported yet.
        Result<ActorRecord> result = ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L));
        XMLFormat xmlformat = XMLFormat.DEFAULT_FOR_RESULTS.header(false).format(true);

        title("XML export default record format is VALUE_ELEMENTS_WITH_FIELD_ATTRIBUTE");
        println(result.formatXML(xmlformat));

        title("COLUMN_NAME_ELEMENTS record format");
        println(result.formatXML(xmlformat.recordFormat(XMLFormat.RecordFormat.COLUMN_NAME_ELEMENTS)));

        title("VALUE_ELEMENTS record format");
        println(result.formatXML(xmlformat.recordFormat(XMLFormat.RecordFormat.VALUE_ELEMENTS)));
    }

    @Test
    public void exportText() {
        // No import supported yet.
        Result<ActorRecord> result = ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L));

        title("The default text format is also used when DEBUG logging things");
        println(result.format());

        title("Different ASCII table formatting styles are available");
        println(result.format(TXTFormat.DEFAULT
            .horizontalHeaderBorder(false)
            .intersectLines(false)
            .verticalTableBorder(false)
            .minColWidth(20)
        ));
    }

    @Test
    public void exportHTML() {
        title("Could be good enough");
        println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L)).formatHTML());
    }

    @Test
    public void exportChart() {
        Field<Date> date = cast(PAYMENT.PAYMENT_DATE, DATE);

        title("Hey, why not! 🤩");
        println(ctx
            .select(date, sum(PAYMENT.AMOUNT))
            .from(PAYMENT)
            .groupBy(date)
            .orderBy(date)
            .fetch()
            .formatChart()
        );

        title("The envy of MS Excel's pivot tables");
        println(ctx
            .select(
                date,
                sum(sum(PAYMENT.AMOUNT).filterWhere(PAYMENT.staff().STORE_ID.eq(1L))).over(orderBy(date)),
                sum(sum(PAYMENT.AMOUNT).filterWhere(PAYMENT.staff().STORE_ID.eq(2L))).over(orderBy(date)))
            .from(PAYMENT)
            .groupBy(date)
            .orderBy(date)
            .fetch()
            .formatChart(ChartFormat.DEFAULT.values(1, 2).display(ChartFormat.Display.STACKED))
        );
    }

    @After
    public void teardown() throws SQLException {
        cleanup();
        super.teardown();
    }
}
