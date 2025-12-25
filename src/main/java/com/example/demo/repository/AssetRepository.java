package com.example.demo.repository;

import com.example.demo.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    boolean existsByAssetTag(String assetTag);

    @Query("SELECT a FROM Asset a LEFT JOIN FETCH a.currentHolder")
    List<Asset> findAllWithHolder();
}
