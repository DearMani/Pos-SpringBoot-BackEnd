package com.ijse.dbms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
    private String stockId;

    private Integer qty;

    private String location;
}
