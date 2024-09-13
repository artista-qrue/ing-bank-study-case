package org.ing.ingbankstudycase.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ing.ingbankstudycase.model.Order;
import org.ing.ingbankstudycase.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:03:30
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetAllOrders() throws Exception {

        Order order = new Order();
        order.setId(1L);
        order.setAssetName("dummy");

        Order orderTwo = new Order();
        order.setId(2L);
        order.setAssetName("dummy");
        // Arrange
        List<Order> orders = Arrays.asList(order, orderTwo);
        when(orderService.getOrders(any(), any(), any())).thenReturn(orders);

        // Act
        MvcResult result = mockMvc.perform(get("/api/orders"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        assertEquals("[{\"id\":1,\"name\":\"Order 1\"},{\"id\":2,\"name\":\"Order 2\"}]", content);
    }

    @Test
    public void testGetOrderById() throws Exception {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setAssetName("dummy");
        when(orderService.getOrders(any(), any(), any())).thenReturn(List.of(order));

        // Act
        MvcResult result = mockMvc.perform(get("/api/orders/1"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"name\":\"Order 1\"}", content);
    }

    @Test
    public void testCreateOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setAssetName("dummy");
        when(orderService.createOrder(any(), any(), any(), any(), any())).thenReturn(order);

        // Act
        MvcResult result = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(order)))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"name\":\"Order 1\"}", content);
    }

    @Test
    public void testUpdateOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setAssetName("dummy");
        when(orderService.createOrder(1L, any(), any(), any(), any())).thenReturn(order);

        // Act
        MvcResult result = mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(order)))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        assertEquals("{\"id\":1,\"name\":\"Order 1\"}", content);
    }

    @Test
    public void testDeleteOrder() throws Exception {
        // Act
        MvcResult result = mockMvc.perform(delete("/api/orders/1"))
            .andExpect(status().isOk())
            .andReturn();

        // Assert
        verify(orderService, times(1)).cancelOrder(1L);
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
