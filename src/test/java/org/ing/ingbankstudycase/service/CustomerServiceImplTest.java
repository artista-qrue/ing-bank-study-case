package org.ing.ingbankstudycase.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.ing.ingbankstudycase.model.Customer;
import org.ing.ingbankstudycase.repository.CustomerRepository;
import org.ing.ingbankstudycase.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:03:41
 */
// CustomerServiceImplTest.java
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void testGetCustomerById() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerServiceImpl.getCustomer(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals("John Doe", result.getName());

    }

    @Test
    public void testGetCustomerByIdNotFound() {
        // Arrange
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Customer result = customerServiceImpl.getCustomer(customerId);

        // Assert
        assertNull(result);
    }

}
