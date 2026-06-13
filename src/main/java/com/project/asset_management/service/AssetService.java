package com.project.asset_management.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetAssignment;
import com.project.asset_management.repositories.AssetRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class AssetService {
	private AssetRepository assetRepository;
	
	public Asset createAsset(Asset asset) {
		return assetRepository.save(asset);
	}
	
	public List<Asset> getAllAssets(){
		return assetRepository.findAll();
	}
	
	public Asset getAssetById(Integer id) {
		return assetRepository.findById(id).orElse(null);
	}
	
	public List<Asset> getAssetsByStatus(String assetStatus){
		if(!Objects.isNull(assetStatus)) 
			assetStatus = assetStatus.trim().toLowerCase();
		
		return assetRepository.findAllAssetsByStatus(assetStatus);
	}
	
	public List<AssetAssignment> getAssetAssignmentsForAnAsset(Integer assetId){
		return assetRepository.getAssetAssignmentsForAnAsset(assetId);
	}

}
