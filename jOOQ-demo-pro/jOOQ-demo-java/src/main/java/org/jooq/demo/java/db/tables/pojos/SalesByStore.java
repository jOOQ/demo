/*
 * This file is generated by jOOQ.
 */
package org.jooq.demo.java.db.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public record SalesByStore(
    String store,
    String manager,
    BigDecimal totalSales
) implements Serializable {

    private static final long serialVersionUID = 1L;


    public SalesByStore(SalesByStore value) {
        this(
            value.store,
            value.manager,
            value.totalSales
        );
    }
}
