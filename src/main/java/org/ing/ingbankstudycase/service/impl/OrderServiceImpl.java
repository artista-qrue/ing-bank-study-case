package org.ing.ingbankstudycase.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.ing.ingbankstudycase.model.Asset;
import org.ing.ingbankstudycase.model.Order;
import org.ing.ingbankstudycase.repository.AssetRepository;
import org.ing.ingbankstudycase.repository.OrderRepository;
import org.ing.ingbankstudycase.service.CustomerService;
import org.ing.ingbankstudycase.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:10
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private CustomerService customerService;

    @Override
    public Order createOrder(Long customerId, String assetName, Order.Side side, Double size, Double price) {
        Asset tryAsset = customerService.getCustomerTRYAsset(customerId);
        if (side == Order.Side.BUY && tryAsset.getUsableSize() < price * size) {
            throw new RuntimeException("Insufficient TRY balance to place order");
        }

        if (side == Order.Side.BUY) {
            tryAsset.setUsableSize(tryAsset.getUsableSize() - price * size);
        } else {
            Asset asset = assetRepository.findByCustomerIdAndAssetName(customerId, assetName);
            if (asset.getUsableSize() < size) {
                throw new RuntimeException("Insufficient asset balance to sell");
            }
            asset.setUsableSize(asset.getUsableSize() - size);
        }

        Order order = new Order();
        order.setCustomer(customerService.getCustomer(customerId));
        order.setAssetName(assetName);
        order.setOrderSide(side);
        order.setSize(size);
        order.setPrice(price);
        order.setStatus(Order.Status.PENDING);

        return orderRepository.save(order);
    }


    @Override
    public List<Order> getOrders(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCustomerIdAndCreateDateBetween(customerId, startDate, endDate);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() != Order.Status.PENDING) {
            throw new RuntimeException("Only pending orders can be canceled");
        }
        order.setStatus(Order.Status.CANCELED);
        Asset tryAsset = customerService.getCustomerTRYAsset(order.getCustomer().getId());
        if (order.getOrderSide() == Order.Side.BUY) {
            tryAsset.setUsableSize(tryAsset.getUsableSize() + order.getPrice() * order.getSize());
        } else {
            Asset asset = assetRepository.findByCustomerIdAndAssetName(order.getCustomer().getId(), order.getAssetName());
            asset.setUsableSize(asset.getUsableSize() + order.getSize());
        }
        orderRepository.save(order);
    }
}
