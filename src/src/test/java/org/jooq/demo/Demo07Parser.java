package org.jooq.demo;

import org.jooq.Field;
import org.jooq.ParseListener;
import org.jooq.Select;
import org.jooq.impl.DSL;
import org.jooq.impl.ParserException;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.jooq.demo.db.Tables.ACTOR;

public class Demo07Parser extends AbstractDemo {

    @Test
    public void parser() {
        title("jOOQ can also parse SQL strings to jOOQ expression trees");
        Select<?> select = ctx.parser().parseSelect("select instr('abcd', 'bc'), instr('a a a a a', 'a', 3) from dual");
        println(select);
        select.fetch();
    }

    @Test
    public void parsingConnection() throws SQLException {
        title("You can use jOOQ behind the scenes to parse your legacy SQL statements in order to benefit a migration");

        // Replace your existing JDBC Connection by this one transparently, and suddenly, most of your
        // SQL queries written for dialect A now work on any jOOQ dialect
        try (Connection c = ctx.parsingConnection();
             PreparedStatement s = c.prepareStatement("select level from dual connect by level <= ?")) {
            s.setInt(1, 10);

            try (ResultSet rs = s.executeQuery()) {
                while (rs.next())
                    println(rs.getInt(1));
            }
        }
    }

    @Test
    public void parseListener() {
        title("The jOOQ parser might not support all of your vendor specific syntax. In some cases, you can simply extend it");

        try {
            ctx.parser().parseSelect("select approx_avg(length) from film").fetch();
        }
        catch (ParserException e) {
            e.printStackTrace();
        }

        title("Handle a few additional functions per dialect");
        ctx.configuration().set(ParseListener.onParseField(c1 -> {
            if (c1.parseFunctionNameIf("APPROX_SUM"))
                return c1.parseParenthesised(c2 -> DSL.sum((Field) c2.parseField()));
            else if (c1.parseFunctionNameIf("APPROX_AVG"))
                return c1.parseParenthesised(c2 -> DSL.avg((Field) c2.parseField()));
            // TODO: The rest

            return null;
        }));

        ctx.parser().parseSelect("select approx_avg(length) from film").fetch();
    }
}
