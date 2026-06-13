package com.project.asset_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.repositories.AssetRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

public class DashboardService {
	private AssetRepository assetRepository;
	
	public Integer getAssetCount() {
		return assetRepository.getAssetCount();
	}
	
	public List<Asset> getAvailableAssets(){
		return assetRepository.getAvailableAssets();
	}
	
	public List<Asset> getAssignedAssets(){
		return assetRepository.getAssignedAssets();
	}

}
