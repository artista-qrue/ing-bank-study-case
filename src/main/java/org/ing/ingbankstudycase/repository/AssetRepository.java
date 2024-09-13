package org.ing.ingbankstudycase.repository;

import java.util.List;

import org.ing.ingbankstudycase.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:19
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByCustomerId(Long customerId);

    Asset findByCustomerIdAndAssetName(Long customerId, String assetName);
}
