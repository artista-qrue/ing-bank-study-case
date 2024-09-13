package org.ing.ingbankstudycase.repository;

import org.ing.ingbankstudycase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:19
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
