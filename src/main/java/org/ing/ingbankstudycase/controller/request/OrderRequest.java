package org.ing.ingbankstudycase.controller.request;

import org.ing.ingbankstudycase.model.Order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:30
 */

@Getter
@Setter
@RequiredArgsConstructor
public class OrderRequest {

    private Long customerId;
    private String assetName;
    private Order.Side side;
    private Double size;
    private Double price;
}
