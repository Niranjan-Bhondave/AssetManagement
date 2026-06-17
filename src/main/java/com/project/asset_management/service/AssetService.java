package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.DTO.AssetAssignmentDTO;
import com.project.asset_management.DTO.AssetDTO;
import com.project.asset_management.entities.Asset;
import com.project.asset_management.exceptions.AssetNotFoundException;
import com.project.asset_management.repositories.AssetRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class AssetService {
	private AssetRepository assetRepository;
	
	@Transactional
	public AssetDTO createAsset(Asset asset) {
		return new AssetDTO(assetRepository.save(asset));
	}
	
	public List<AssetDTO> getAllAssets(){		
		return assetRepository.findAll().stream().map(AssetDTO::new).toList();
	}
	
	public AssetDTO getAssetById(Integer id) {
		Asset asset = assetRepository.findById(id).orElseThrow(()->new AssetNotFoundException(id));
		return new AssetDTO(asset);
	}
	
	public List<AssetDTO> getAssetsByStatus(String assetStatus){
		if(Objects.isNull(assetStatus)) 
			return getAllAssets();
		
		assetStatus = assetStatus.trim().toLowerCase();
		return assetRepository.findAllAssetsByStatus(assetStatus).stream().map(AssetDTO::new).toList();
	}
	
	public List<AssetAssignmentDTO> getAssetAssignmentsForAnAsset(Integer assetId){
		return assetRepository.getAssetAssignmentsForAnAsset(assetId).stream().map(AssetAssignmentDTO::new).toList();
	}
	
	@Transactional
	public AssetDTO updateAsset(Integer id, Asset newAssetDetails) {
		Asset currentAssetDetails = assetRepository.findById(id).orElseThrow(()->new AssetNotFoundException(id));
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
