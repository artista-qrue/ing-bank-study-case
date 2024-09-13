package org.ing.ingbankstudycase.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.ing.ingbankstudycase.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:08
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerIdAndCreateDateBetween(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

}
