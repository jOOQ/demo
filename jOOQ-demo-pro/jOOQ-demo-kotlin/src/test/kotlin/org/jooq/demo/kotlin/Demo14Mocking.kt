package org.jooq.demo.kotlin

import org.jooq.demo.AbstractDemo
import org.jooq.demo.kotlin.db.tables.references.ACTOR
import org.jooq.exception.DataAccessException
import org.jooq.impl.DSL.using
import org.jooq.tools.jdbc.MockConnection
import org.jooq.tools.jdbc.MockDataProvider
import org.jooq.tools.jdbc.MockFileDatabase
import org.jooq.tools.jdbc.MockResult
import org.junit.Test


class Demo14Mocking : AbstractDemo() {

    // Disclaimer: The general idea of mocking a JDBC connection with this jOOQ API is to provide quick workarounds,
    // injection points, etc. using a very simple JDBC abstraction. It is NOT RECOMMENDED to emulate an entire database
    // (including complex state transitions, transactions, locking, etc.) using this mock API. Once you have this
    // requirement, please consider using an actual database product instead for integration testing, rather than
    // implementing your test database inside of a MockDataProvider.
    @Test
    fun mockingJDBCProgrammatically() {

        // More details here: https://www.jooq.org/doc/latest/manual/sql-execution/mocking-connection/
        // A MockDataProvider is a database simulation that can intercept all database calls at the JDBC level
        val p = MockDataProvider { c ->
            arrayOf(
                if (c.sql().contains("select"))
                    MockResult(
                        ctx.newRecord(ACTOR)
                            .with(ACTOR.ACTOR_ID, 1L)
                            .with(ACTOR.FIRST_NAME, "A")
                            .with(ACTOR.LAST_NAME, "A"))
                else
                    MockResult(0)
            )
        }

        MockConnection(p).use { c ->
            c.createStatement().use { s ->
                s.executeQuery("select dummy from random_table").use { rs ->
                    title("Using jOOQ only to fetch and pretty print the JDBC ResultSet")
                    println(ctx.fetch(rs))
                }
            }
        }

        title("Of course, jOOQ being a JDBC based API, you can equally mock jOOQ")
        println(
            using(MockConnection(p), ctx.dialect())
                .selectFrom(ACTOR)
                .fetch()
        )
    }

    @Test
    fun mockingJDBCWithTheFileDatabase() {

        // More details here: https://www.jooq.org/doc/latest/manual/sql-execution/mock-file-database/
        val p = MockFileDatabase(
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
            """.trimIndent()
        )

        val c = using(MockConnection(p), ctx.dialect())
        title("This statement is matched by the MockFileDatabase")
        println(c.fetch("select 'A'"))

        try {
            title("This statement cannot be matched by the MockFileDatabase, so it fails")
            c.fetch("select 'B'")
        }
        catch (e: DataAccessException) {
            e.printStackTrace()
        }

        title("These statements can be matched again")
        println(c.fetch("select 'A', 'B' union all select 'C', 'D'"))
        println(c.execute("update t set x = 1"))

        try {
            title("This statement is matched, but throws an exception")
            c.fetch("select * from t")
        }
        catch (e: DataAccessException) {
            e.printStackTrace()
        }
    }
}
