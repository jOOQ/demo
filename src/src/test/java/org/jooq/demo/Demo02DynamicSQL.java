package org.jooq.demo;

import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.util.List;

import static org.jooq.demo.db.Tables.*;
import static org.jooq.impl.DSL.*;

public class Demo02DynamicSQL extends AbstractDemo {

    @Test
    public void testDynamicSQL() {
        title("Every jOOQ query is a dynamic SQL query. You just don't see it");
        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .from(ACTOR)
            .where(ACTOR.ACTOR_ID.in(1L, 2L, 3L))
            .orderBy(ACTOR.FIRST_NAME)
            .limit(5)
            .fetch();

        title("The above and the below are equivalent");
        List<SelectField<?>> select = List.of(ACTOR.FIRST_NAME, ACTOR.LAST_NAME);
        Table<?> from = ACTOR;
        Condition where = ACTOR.ACTOR_ID.in(1L, 2L, 3L);
        List<OrderField<?>> orderBy = List.of(ACTOR.FIRST_NAME);
        Field<Integer> limit = val(5);

        ctx.select(select)
           .from(from)
           .where(where)
           .orderBy(orderBy)
           .limit(limit)
           .fetch();

        title("Any 'static' query part can be replaced by an expression, function call, etc.");
        List<Integer> ids = List.of(1, 2, 3);

        ctx.select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
           .from(ACTOR)
           .where(ids.isEmpty()
                  ? noCondition()
                  : ACTOR.ACTOR_ID.in(ids.stream().map(Long::valueOf).map(DSL::val).toList()))
           .orderBy(ACTOR.FIRST_NAME)
           .limit(5)
           .fetch();
    }
}
