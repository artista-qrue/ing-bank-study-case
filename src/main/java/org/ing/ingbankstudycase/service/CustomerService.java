package org.ing.ingbankstudycase.service;

import java.util.List;

import org.ing.ingbankstudycase.model.Asset;
import org.ing.ingbankstudycase.model.Customer;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:21
 */
public interface CustomerService {

    Customer getCustomer(Long customerId);

    Customer deposit(Long customerId, Double amount);

    Customer withdraw(Long customerId, Double amount, String iban);

    Asset getCustomerTRYAsset(Long customerId);

    List<Asset> getAssetsByCustomer(Long customerId, String assetName, Double minUsableSize);
}
