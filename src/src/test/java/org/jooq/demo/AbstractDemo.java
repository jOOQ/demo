package org.jooq.demo;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.jooq.demo.java.db.Tables.ACTOR;
import static org.jooq.impl.DSL.using;

/**
 * A common base class for demo code, taking care of setting up a JDBC connection and a {@link DSLContext}.
 */
public abstract class AbstractDemo {

    protected Connection connection;
    protected DSLContext ctx;

    // Utilities
    // -----------------------------------------------------------------------------------------------------------------

    @Before
    public void setup() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(AbstractDemo.class.getResourceAsStream("/config.properties"));
        connection = DriverManager.getConnection(
            properties.getProperty("db.cockroachdb.url"),
            properties.getProperty("db.cockroachdb.username"),
            properties.getProperty("db.cockroachdb.password")
        );

        ctx = using(new DefaultConfiguration()
                .set(connection)
                .set(SQLDialect.COCKROACHDB)
                .set(new Settings()
//                .withRenderFormatted(true)
                )

            // Activate this to get the output from different dialects
//            .set(ExecuteListener.onExecuteStart(e -> Stream
//                .of(MYSQL, ORACLE, POSTGRES, SQLSERVER)
//                .map(d -> using(d, new Settings().withRenderFormatted(true)))
//                .forEach(c -> {
//                    title(c.dialect());
//                    println(c.renderInlined(e.query()));
//                })))
        );

        // Initialise classes
        ctx.selectOne().toString();
    }

    @After
    public void teardown() throws SQLException {
        connection.close();
    }

    public void cleanup() {
        ctx.delete(ACTOR)
           .where(ACTOR.ACTOR_ID.gt(200L))
           .execute();
    }


    public static void title(Object title) {
        println("");
        println(title);
        println("-".repeat(("" + title).length()));
    }

    public static <T> T println(T t) {
        System.out.println(t);
        return t;
    }
}
