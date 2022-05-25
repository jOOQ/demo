package org.jooq.demo;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.JooqLogger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

import static org.jooq.SQLDialect.*;
import static org.jooq.impl.DSL.using;

/**
 * A common base class for demo code, taking care of setting up a JDBC connection and a {@link DSLContext}.
 */
public abstract class AbstractDemo {

    protected static JooqLogger          log = JooqLogger.getLogger(AbstractDemo.class);
    protected static PostgreSQLContainer db;
    protected static Connection          connection;
    protected static DSLContext    ctx;
    protected static Configuration configuration;

    // Utilities
    // -----------------------------------------------------------------------------------------------------------------

    @BeforeClass
    public static void beforeClass() throws SQLException {
        log.info("Setting up testcontainers");
        db = new PostgreSQLContainer("postgres:latest")
            .withUsername("postgres")
            .withDatabaseName("postgres")
            .withPassword("postgres");

        db.start();

        log.info("Connecting");
        connection = DriverManager.getConnection(
            db.getJdbcUrl(),
            db.getUsername(),
            db.getPassword()
        );
        ctx = using(configuration = new DefaultConfiguration()
            .set(connection)
            .set(POSTGRES)
            .set(new Settings()
                .withRenderFormatted(true)
            )

//            // Activate this to get the output from different dialects
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
        log.info("Finished setup");

        log.info("Flyway migration");
        Flyway.configure()
              .locations("filesystem:../../sakila/postgres")
              .dataSource(db.getJdbcUrl(), db.getUsername(), db.getPassword())
              .load()
              .migrate();
    }

    @AfterClass
    public static void afterClass() {
        db.close();
    }

    @Before
    public void setup() throws SQLException {
    }
    
    @After
    public void teardown() throws SQLException {
    }

    public void cleanup(Table<?> actor, Field<Long> actorId) {
        ctx.delete(actor)
           .where(actorId.gt(200L))
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
