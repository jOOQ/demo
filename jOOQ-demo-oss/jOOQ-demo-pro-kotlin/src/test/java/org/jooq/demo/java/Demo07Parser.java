package org.jooq.demo.java;

import org.jetbrains.annotations.NotNull;
import org.jooq.*;
import org.jooq.conf.ParseWithMetaLookups;
import org.jooq.demo.AbstractDemo;
import org.jooq.impl.DSL;
import org.jooq.impl.ParserException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void parseMetaLookups() {
        title("The parser can validate your schema, too. There are different types of meta data sources");
        Meta meta = ctx.meta(
             """
             create table author (
               author_id int not null primary key, 
               first_name text not null, 
               last_name text not null
             );
             create table book (
               book_id int not null primary key,
               author_id int not null references author, 
               title text not null
             );
             """
        );

        DSLContext c = ctx
            .configuration()
            .derive(() -> meta)
            .deriveSettings(s -> s.withParseWithMetaLookups(ParseWithMetaLookups.THROW_ON_FAILURE))
            .dsl();

        title("Check the projection produced by the parsed query");
        println(c.parser().parseSelect("select * from book").getSelect());

        try {
            c.parser().parseSelect("select id from book");
        }
        catch (ParserException e) {
            title("Parse errors now include meta data lookup errors");
            e.printStackTrace();
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
