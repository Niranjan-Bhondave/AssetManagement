package com.project.asset_management.service;

import java.time.LocalDate;
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
	
	public AssetDTO createAsset(Asset asset) {
		return new AssetDTO(assetRepository.save(asset));
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
		if(Objects.isNull(assetStatus)) 
			return getAllAssets();
		
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
	
	public AssetDTO updateAsset(Integer id, Asset newAssetDetails) {
		Asset currentAssetDetails = assetRepository.findById(id).orElse(null);
		if(Objects.isNull(currentAssetDetails)) {
			System.out.println("asset not found");
			return null;
		}
		boolean changes = false;

		String oldAssetCode = currentAssetDetails.getAssetCode();
		String newAssetCode = newAssetDetails.getAssetCode();

		String oldAssetType = currentAssetDetails.getAssetType();
		String newAssetType = newAssetDetails.getAssetType();

		LocalDate oldPurchaseDate = currentAssetDetails.getPurchaseDate();
		LocalDate newPurchaseDate = newAssetDetails.getPurchaseDate();
		
		if (!Objects.equals(oldAssetCode, newAssetCode)) {
		    currentAssetDetails.setAssetCode(newAssetCode);
		    changes = true;
		}

		if (!Objects.equals(oldAssetType, newAssetType)) {
		    currentAssetDetails.setAssetType(newAssetType);
		    changes = true;
		}

		if (!Objects.equals(oldPurchaseDate, newPurchaseDate)) {
		    currentAssetDetails.setPurchaseDate(newPurchaseDate);
		    changes = true;
		}

		if(changes) {
			Asset newAsset =  assetRepository.save(currentAssetDetails);
			return new AssetDTO(newAsset);
		}
		
		return null;
	}
	
	public void deleteAsset(Integer id) {
		assetRepository.deleteById(id);
	}

}
