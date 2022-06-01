package org.jooq.demo.skala

import org.jooq.demo.AbstractDemo
import org.jooq.demo.AbstractDemo._
import org.jooq.demo.skala.db.Tables.ACTOR
import org.jooq.exception.DataAccessException
import org.jooq.impl.DSL.using
import org.jooq.tools.jdbc.{MockConnection, MockDataProvider, MockFileDatabase, MockResult}
import org.junit.{After, Test}

import scala.util.Using


class Demo14Mocking extends AbstractDemo {

  // Disclaimer: The general idea of mocking a JDBC connection with this jOOQ API is to provide quick workarounds,
  // injection points, etc. using a very simple JDBC abstraction. It is NOT RECOMMENDED to emulate an entire database
  // (including complex state transitions, transactions, locking, etc.) using this mock API. Once you have this
  // requirement, please consider using an actual database product instead for integration testing, rather than
  // implementing your test database inside of a MockDataProvider.

  @Test
  def mockingJDBCProgrammatically(): Unit = {

    // More details here: https://www.jooq.org/doc/latest/manual/sql-execution/mocking-connection/
    // A MockDataProvider is a database simulation that can intercept all database calls at the JDBC level
    val p: MockDataProvider = c => Array[MockResult](
      if (c.sql.contains("select"))
        new MockResult(ctx.newRecord(ACTOR).values(1L, "A", "A", null))
      else
        new MockResult(0)
    )

    // It can be used with any JDBC based application. Just replace the actual connection by this MockConnection
    Using(new MockConnection(p)) { c =>
      // These statements aren't actually executed. They all just run against the above MockDataProvider

      Using(c.createStatement()) { s =>
        Using(s.executeQuery("select dummy from random_table")) { rs =>
          title("Using jOOQ only to fetch and pretty print the JDBC ResultSet")
          println(ctx.fetch(rs))
        }
      }
    }

    title("Of course, jOOQ being a JDBC based API, you can equally mock jOOQ")
    println(using(new MockConnection(p), ctx.dialect).selectFrom(ACTOR).fetch)
  }

  @Test
  def mockingJDBCWithTheFileDatabase(): Unit = {

    // More details here: https://www.jooq.org/doc/latest/manual/sql-execution/mock-file-database/
    val p = new MockFileDatabase(
      """
            # All lines with a leading hash are ignored. This is the MockFileDatabase comment syntax
            select 'A';
            > A
            > -
            > A
            @ rows: 1

            select 'A', 'B' union all select 'C', 'D';
            > A B
            > - -
            > A B
            > C D
            @ rows: 2

            # Statements without result sets just leave that section empty
            update t set x = 1;
            @ rows: 3

            # Statements producing specific exceptions can indicate them as such
            select * from t;
            @ exception: ACCESS TO TABLE T FORBIDDEN
            """.stripIndent())

    val c = using(new MockConnection(p), ctx.dialect)

    title("This statement is matched by the MockFileDatabase")
    println(c.fetch("select 'A'"))

    try {
      title("This statement cannot be matched by the MockFileDatabase, so it fails")
      c.fetch("select 'B'")
    } catch {
      case e: DataAccessException =>
        e.printStackTrace()
    }

    title("These statements can be matched again")
    println(c.fetch("select 'A', 'B' union all select 'C', 'D'"))
    println(c.execute("update t set x = 1"))

    try {
      title("This statement is matched, but throws an exception")
      c.fetch("select * from t")
    } catch {
      case e: DataAccessException =>
        e.printStackTrace()
    }
  }
}
