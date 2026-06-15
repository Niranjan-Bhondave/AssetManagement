package com.project.asset_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.DTO.AssetAssignmentDTO;
import com.project.asset_management.DTO.AssetDTO;
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
	
	public List<AssetDTO> getAllAssets(){
		List<Asset> allAssets = assetRepository.findAll();
		List<AssetDTO> responseDTO = new ArrayList<AssetDTO>();
		for(Asset asset: allAssets) {
			responseDTO.add(new AssetDTO(asset));
		}
		
		return responseDTO;
	}
	
	public AssetDTO getAssetById(Integer id) {
		Asset asset = assetRepository.findById(id).orElse(null);
		if(Objects.isNull(asset))return null;
		return new AssetDTO(asset);
	}
	
	public List<AssetDTO> getAssetsByStatus(String assetStatus){
		if(!Objects.isNull(assetStatus)) 
			assetStatus = assetStatus.trim().toLowerCase();
		
		List<Asset> allAssets =  assetRepository.findAllAssetsByStatus(assetStatus);
		List<AssetDTO> responseDTO = new ArrayList<AssetDTO>();
		for(Asset asset: allAssets) {
			responseDTO.add(new AssetDTO(asset));
		}
		
		return responseDTO;
	}
	
	public List<AssetAssignmentDTO> getAssetAssignmentsForAnAsset(Integer assetId){
		List<AssetAssignment> assetAssignments = assetRepository.getAssetAssignmentsForAnAsset(assetId);
		List<AssetAssignmentDTO> responseDTO = new ArrayList<AssetAssignmentDTO>(); 
		for(AssetAssignment assetAssignment: assetAssignments) {
			responseDTO.add(new AssetAssignmentDTO(assetAssignment));
		}
		
		return responseDTO;
	}

}
