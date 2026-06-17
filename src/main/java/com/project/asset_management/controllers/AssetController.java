package com.project.asset_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.asset_management.DTO.AssetAssignmentDTO;
import com.project.asset_management.DTO.AssetDTO;
import com.project.asset_management.entities.Asset;
import com.project.asset_management.service.AssetService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AssetController {
	
	private AssetService assetService;
	@PostMapping(path = "api/assets")
	public AssetDTO createAsset(@RequestBody Asset asset) {
		return assetService.createAsset(asset);
	}
	
	@GetMapping(path = "api/assets")
	public List<AssetDTO> getAllAssets(){
		return assetService.getAllAssets();
	}
	
	@GetMapping(path = "api/assets/{id}")
	public AssetDTO getAssetById(@PathVariable Integer id) {
		return assetService.getAssetById(id);
	}
	
	@PutMapping(path = "api/assets/{id}")
	public AssetDTO updateAsset(@RequestBody Asset newAsset, @PathVariable Integer id) {
		return assetService.updateAsset(id, newAsset);
	}
	
	@DeleteMapping(path = "api/assets/{id}")
	public void deleteAsset(@PathVariable Integer id) {
		assetService.deleteAsset(id);
	}
	
	@GetMapping(path = "api/assets")
	public List<AssetDTO> getAssetsByStatus(@RequestParam String status){
		return assetService.getAssetsByStatus(status);
	}
	
	@GetMapping(path = "api/assets/{id}/assignments")
	public List<AssetAssignmentDTO> getAssetAssignmentsForAnAsset(@PathVariable Integer id){
		return assetService.getAssetAssignmentsForAnAsset(id);
	}
}
