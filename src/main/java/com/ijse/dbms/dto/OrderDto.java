package com.ijse.dbms.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long customerId;
    private Double total;
    private Double discount;
    private Double tax;
    private List<OrderDetailDto> orderDetails =new ArrayList<>();

}
