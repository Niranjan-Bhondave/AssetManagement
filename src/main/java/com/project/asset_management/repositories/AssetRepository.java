package com.project.asset_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.asset_management.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer>{

}
