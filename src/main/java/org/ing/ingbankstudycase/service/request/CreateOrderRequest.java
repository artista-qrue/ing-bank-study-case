package org.ing.ingbankstudycase.service.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:11
 */
@Getter
@Setter
@AllArgsConstructor
public class CreateOrderRequest {

    private String customerId;
    private String asset;
    private String side;
    private BigDecimal size;
    private BigDecimal price;
}
