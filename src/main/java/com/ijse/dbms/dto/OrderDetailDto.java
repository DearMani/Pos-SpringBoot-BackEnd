package com.ijse.dbms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    @JsonProperty("productid")
    private Long  productid;
    private String qty;
}
