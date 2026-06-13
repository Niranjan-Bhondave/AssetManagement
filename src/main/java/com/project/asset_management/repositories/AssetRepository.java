package com.project.asset_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetAssignment;

public interface AssetRepository extends JpaRepository<Asset, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM Asset WHERE LOWER(status)=:assetStatus ")
	public List<Asset> findAllAssetsByStatus(@Param("assetStatus") String assetStatus);
	
	@Query(nativeQuery = true, value = "SELECT * FROM AssetAssignment WHERE asset_id =: assetId")
	public List<AssetAssignment> getAssetAssignmentsForAnAsset(@Param("assetId")Integer assetId);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) AS ASSET_COUNT FROM ASSET")
	public Integer getAssetCount();
	
	@Query(nativeQuery = true, value = "SELECT * FROM ASSET WHERE STATUS = 'AVAILABLE'")
	public List<Asset> getAvailableAssets(); 
	
	@Query(nativeQuery = true, value = "SELECT * FROM ASSET WHERE STATUS = 'ASSIGNED'")
	public List<Asset> getAssignedAssets(); 

}
