package org.ing.ingbankstudycase.controller;

import org.ing.ingbankstudycase.model.Asset;
import org.ing.ingbankstudycase.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:04:10
 */
@RestController
@RequestMapping("/customers")
public class AssetController {

    @Autowired
    private CustomerService assetService;

    @GetMapping("/{customerId}/assets")
    public List<Asset> listAssets(
        @PathVariable Long customerId,
        @RequestParam(required = false) String assetName,
        @RequestParam(required = false) Double minUsableSize
    ) {
        return assetService.getAssetsByCustomer(customerId, assetName, minUsableSize);
    }
}
