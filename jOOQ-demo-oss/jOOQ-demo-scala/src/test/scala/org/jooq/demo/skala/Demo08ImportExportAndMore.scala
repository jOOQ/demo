package org.jooq.demo.skala

import org.jooq._
import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.{ACTOR, PAYMENT}
import org.jooq.impl.DSL._
import org.jooq.impl.SQLDataType.DATE
import org.junit.{After, Test}


class Demo08ImportExportAndMore extends AbstractDemo {

  @Test
  def importExportCSV(): Unit = {
    title("Importing data from CSV content")
    ctx.loadInto(ACTOR)

      // Throttling flags
      .bulkAfter(2)
      .batchAfter(2)

      // Duplicate handling flags
      .onDuplicateKeyError

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
      .execute

    title("Exporting data to CSV content")
    println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)).formatCSV)

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/exporting/exporting-csv/
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/importing-sources/importing-source-csv/
  }

  @Test
  def importExportJSON(): Unit = {
    title("Importing data from JSON content")
    ctx.loadInto(ACTOR)
      .batchAll
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
      .execute

    println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)).formatJSON(JSONFormat.DEFAULT_FOR_RESULTS.header(false).format(true)))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/exporting/exporting-json/
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/importing-sources/importing-source-json/
  }

  @Test
  def importExportJavaData(): Unit = {
    title("Importing data from Java in-memory content")
    ctx.loadInto(ACTOR)
      .batchAll
      .loadArrays(Array[Array[Object]](
        Array(201.asInstanceOf[Integer], "Doe", "John"),
        Array(202.asInstanceOf[Integer], "Smith", "Jane"),
        Array(203.asInstanceOf[Integer], "Colson", "Wilda"),
        Array(204.asInstanceOf[Integer], "Abel", "Izzy"),
        Array(205.asInstanceOf[Integer], "Langdon", "Cayson"),
        Array(206.asInstanceOf[Integer], "Brooke", "Deon"),
        Array(207.asInstanceOf[Integer], "Wolf", "Gabriella"),
        Array(208.asInstanceOf[Integer], "Edie", "Drew"),
        Array(209.asInstanceOf[Integer], "Rupert", "Maeghan"),
        Array(210.asInstanceOf[Integer], "Coleman", "Skyler")):_*)
      .fields(ACTOR.ACTOR_ID, ACTOR.LAST_NAME, ACTOR.FIRST_NAME)
      .execute

    println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.gt(200L)))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/importing-sources/importing-source-records/
    // https://www.jooq.org/doc/latest/manual/sql-execution/importing/importing-sources/importing-source-arrays/
  }

  @Test
  def exportXML(): Unit = {
    // No import supported yet.
    val result = ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L))
    val xmlformat = XMLFormat.DEFAULT_FOR_RESULTS.header(false).format(true)
    title("XML export default record format is VALUE_ELEMENTS_WITH_FIELD_ATTRIBUTE")
    println(result.formatXML(xmlformat))

    title("COLUMN_NAME_ELEMENTS record format")
    println(result.formatXML(xmlformat.recordFormat(XMLFormat.RecordFormat.COLUMN_NAME_ELEMENTS)))

    title("VALUE_ELEMENTS record format")
    println(result.formatXML(xmlformat.recordFormat(XMLFormat.RecordFormat.VALUE_ELEMENTS)))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/exporting/exporting-xml/
  }

  @Test
  def exportText(): Unit = {
    val result = ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L))

    title("The default text format is also used when DEBUG logging things")
    println(result.format)

    title("Different ASCII table formatting styles are available")
    println(result.format(TXTFormat.DEFAULT
      .horizontalHeaderBorder(false)
      .intersectLines(false)
      .verticalTableBorder(false)
      .minColWidth(20)))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/exporting/exporting-text/
  }

  @Test
  def exportHTML(): Unit = {
    title("Could be good enough")
    println(ctx.fetch(ACTOR, ACTOR.ACTOR_ID.lt(4L)).formatHTML)

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/exporting/exporting-html/
  }

  @Test
  def exportChart(): Unit = {
    val date = cast(PAYMENT.PAYMENT_DATE, DATE)
    title("Hey, why not! 🤩")
    println(ctx
      .select(date, sum(PAYMENT.AMOUNT))
      .from(PAYMENT)
      .groupBy(date)
      .orderBy(date)
      .fetch
      .formatChart)

    title("The envy of MS Excel's pivot tables")
    println(ctx
      .select(
        date,
        sum(sum(PAYMENT.AMOUNT).filterWhere(PAYMENT.staff.STORE_ID.eq(1L))).over(orderBy(date)),
        sum(sum(PAYMENT.AMOUNT).filterWhere(PAYMENT.staff.STORE_ID.eq(2L))).over(orderBy(date)))
      .from(PAYMENT)
      .groupBy(date)
      .orderBy(date)
      .fetch
      .formatChart(ChartFormat.DEFAULT.values(1, 2).display(ChartFormat.Display.STACKED)))

    // More information here:
    // https://www.jooq.org/doc/latest/manual/sql-execution/exporting/exporting-charts/
  }

  @After
  override def teardown(): Unit = {
    cleanup(ACTOR, ACTOR.ACTOR_ID)
    super.teardown()
  }
}
