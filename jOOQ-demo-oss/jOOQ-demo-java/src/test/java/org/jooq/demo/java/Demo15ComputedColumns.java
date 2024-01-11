package org.jooq.demo.java;

import org.jooq.*;
import org.jooq.demo.AbstractDemo;
import org.jooq.demo.java.db.tables.Actor;
import org.jooq.demo.java.db.tables.FilmActor;
import org.jooq.demo.java.db.tables.records.ActorRecord;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.jooq.Records.intoMap;
import static org.jooq.Records.mapping;
import static org.jooq.demo.java.db.Tables.*;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.LOCALDATE;

public class Demo15ComputedColumns extends AbstractDemo {

    @Test
    public void fetchVirtualClientSideComputedColumns() {
        title("Client side computed columns emulate their server side equivalents by inlining the computation expression");

        // Computed columns are a commercial only feature

//
//        ctx.select(STAFF.FULL_NAME, STAFF.FULL_ADDRESS)
//            .from(STAFF)
//            .fetch();
//
//

        // More information here:
        // - https://www.jooq.org/doc/latest/manual/code-generation/codegen-advanced/codegen-config-database/codegen-database-forced-types/codegen-database-forced-types-computed/
    }
}
