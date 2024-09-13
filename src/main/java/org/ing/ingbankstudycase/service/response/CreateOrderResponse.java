package org.ing.ingbankstudycase.service.response;

import org.ing.ingbankstudycase.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:12
 */

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderResponse {

    private Order order;
}
