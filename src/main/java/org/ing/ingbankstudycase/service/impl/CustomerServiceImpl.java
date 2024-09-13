package org.ing.ingbankstudycase.service.impl;

import org.ing.ingbankstudycase.model.Asset;
import org.ing.ingbankstudycase.model.Customer;
import org.ing.ingbankstudycase.repository.AssetRepository;
import org.ing.ingbankstudycase.repository.CustomerRepository;
import org.ing.ingbankstudycase.service.CustomerService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:21
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AssetRepository assetRepository;

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer deposit(Long customerId, Double amount) {
        Asset tryAsset = getCustomerTRYAsset(customerId);
        tryAsset.setUsableSize(tryAsset.getUsableSize() + amount);
        return customerRepository.save(tryAsset.getCustomer());
    }

    @Override
    public Customer withdraw(Long customerId, Double amount, String iban) {
        Asset tryAsset = getCustomerTRYAsset(customerId);
        if (tryAsset.getUsableSize() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        tryAsset.setUsableSize(tryAsset.getUsableSize() - amount);
        return customerRepository.save(tryAsset.getCustomer());
    }

    @Override
    public Asset getCustomerTRYAsset(Long customerId) {
        return assetRepository.findByCustomerIdAndAssetName(customerId, "TRY");
    }
}
