package org.ing.ingbankstudycase.service;

import java.time.LocalDateTime;
import java.util.List;

import org.ing.ingbankstudycase.model.Order;
import org.ing.ingbankstudycase.service.request.CreateOrderRequest;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:09
 */
public interface OrderService {

    Order createOrder(Long customerId, String assetName, Order.Side side, Double size, Double price);

    List<Order> getOrders(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    void cancelOrder(Long orderId);
}
