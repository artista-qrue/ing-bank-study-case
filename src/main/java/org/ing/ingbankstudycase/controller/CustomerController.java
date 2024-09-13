package org.ing.ingbankstudycase.controller;

import javax.validation.constraints.NotNull;

import org.ing.ingbankstudycase.controller.request.WithdrawRequest;
import org.ing.ingbankstudycase.model.Customer;
import org.ing.ingbankstudycase.service.CustomerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:27
 */
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/{customerId}/deposit")
    public Customer deposit(@PathVariable Long customerId, @RequestBody @NotNull Double amount) {
        return customerService.deposit(customerId, amount);
    }

    @PostMapping("/{customerId}/withdraw")
    public Customer withdraw(@PathVariable Long customerId, @RequestBody @NotNull WithdrawRequest request) {
        return customerService.withdraw(customerId, request.getAmount(), request.getIban());
    }
}
