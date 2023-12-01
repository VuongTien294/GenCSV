package com.example.gencsv.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateItem {
    private Integer product_id;
//    private Integer agent_customer_id;
    private Integer number_product_in_order;
}
