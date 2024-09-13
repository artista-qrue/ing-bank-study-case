package org.ing.ingbankstudycase.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.ing.ingbankstudycase.model.Order;
import org.ing.ingbankstudycase.repository.CustomerRepository;
import org.ing.ingbankstudycase.repository.OrderRepository;
import org.ing.ingbankstudycase.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:03:45
 */
// OrderServiceImplTest.java
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void testGetOrderById() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        when(orderServiceImpl.getOrders(any(), any(), any())).thenReturn(List.of(order));
        // Assert
    }

    @Test
    public void testGetOrderByIdNotFound() {
        // Arrange
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        List<Order> result = orderServiceImpl.getOrders(any(), any(), any());

        // Assert
        assertNull(result);
    }

    @Test
    public void testCreateOrder() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order result = orderServiceImpl.createOrder(any(), any(), any(), any(), any());

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());

    }

}
