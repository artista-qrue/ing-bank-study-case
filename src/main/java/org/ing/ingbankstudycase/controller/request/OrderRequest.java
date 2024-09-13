package org.ing.ingbankstudycase.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private Long customerId;

    @NotEmpty
    private String assetName;

    @NotNull
    private Order.Side side;
    @NotNull
    private Double size;
    @NotNull
    private Double price;
}
