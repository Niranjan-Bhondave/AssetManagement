package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.DTO.AssetAssignmentResponseDTO;
import com.project.asset_management.DTO.AssetResponseDTO;
import com.project.asset_management.DTO.AssetRequestDTO;
import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetStatus;
import com.project.asset_management.exceptions.AssetNotFoundException;
import com.project.asset_management.repositories.AssetRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class AssetService {
	private AssetRepository assetRepository;
	
	@Transactional
	public AssetResponseDTO createAsset(AssetRequestDTO assetRequestDTO) {
		Asset asset = new Asset();
		asset.setAssetCode(assetRequestDTO.getAssetCode());
		asset.setAssetType(assetRequestDTO.getAssetType());
		asset.setPurchaseDate(assetRequestDTO.getPurchaseDate());
		asset.setStatus(AssetStatus.AVAILABLE);
		return new AssetResponseDTO(assetRepository.save(asset));
	}
	
	public List<AssetResponseDTO> getAllAssets(){		
		return assetRepository.findAll().stream().map(AssetResponseDTO::new).toList();
	}
	
	public AssetResponseDTO getAssetById(Integer id) {
		Asset asset = assetRepository.findById(id).orElseThrow(()->new AssetNotFoundException(id));
		return new AssetResponseDTO(asset);
	}
	
	public List<AssetResponseDTO> getAssetsByStatus(String assetStatus){
		if(Objects.isNull(assetStatus)) 
			return getAllAssets();
		
		assetStatus = assetStatus.trim().toLowerCase();
		return assetRepository.findAllAssetsByStatus(assetStatus).stream().map(AssetResponseDTO::new).toList();
	}
	
	public List<AssetAssignmentResponseDTO> getAssetAssignmentsForAnAsset(Integer assetId){
		return assetRepository.getAssetAssignmentsForAnAsset(assetId).stream().map(AssetAssignmentResponseDTO::new).toList();
	}
	
	@Transactional
	public AssetResponseDTO updateAsset(Integer id, Asset newAssetDetails) {
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
			return new AssetResponseDTO(newAsset);
		}
		
		return null;
	}
	
	public void deleteAsset(Integer id) {
		assetRepository.deleteById(id);
	}

}
