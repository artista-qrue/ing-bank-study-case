package org.ing.ingbankstudycase.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.ing.ingbankstudycase.controller.request.OrderRequest;
import org.ing.ingbankstudycase.model.Order;
import org.ing.ingbankstudycase.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:29
 */

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(
            request.getCustomerId(),
            request.getAssetName(),
            request.getSide(),
            request.getSize(),
            request.getPrice()
        );
    }

    @GetMapping("/{customerId}")
    public List<Order> getOrders(@PathVariable Long customerId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return orderService.getOrders(customerId, startDate, endDate);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
    }
}
