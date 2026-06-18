package com.project.asset_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.asset_management.DTO.AssetAssignmentResponseDTO;
import com.project.asset_management.DTO.AssetRequestDTO;
import com.project.asset_management.DTO.AssetResponseDTO;
import com.project.asset_management.entities.AssetStatus;
import com.project.asset_management.service.AssetService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/assets")
@AllArgsConstructor
public class AssetController {
	
	private final AssetService assetService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AssetResponseDTO createAsset(@RequestBody AssetRequestDTO assetRequestDTO) {
		return assetService.createAsset(assetRequestDTO);
	}
	
	
	@GetMapping(path = "/{assetId}")
	public AssetResponseDTO getAssetById(@PathVariable Integer id) {
		return assetService.getAssetById(id);
	}
	
	@PutMapping(path = "/{assetId}")
	public AssetResponseDTO updateAsset(@RequestBody AssetRequestDTO newAsset, @PathVariable Integer id) {
		return assetService.updateAsset(id, newAsset);
	}
	
	@DeleteMapping(path = "/{assetId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAsset(@PathVariable Integer id) {
		assetService.deleteAsset(id);
	}
	
	@GetMapping
	public List<AssetResponseDTO> getAssetsByStatus(@RequestParam(required = false) AssetStatus status){
		if(status == null) return assetService.getAllAssets();
		return assetService.getAssetsByStatus(status);
	}
	
	@GetMapping(path = "/{assetId}/assignments")
	public List<AssetAssignmentResponseDTO> getAssetAssignmentsForAnAsset(@PathVariable Integer id){
		return assetService.getAssetAssignmentsForAnAsset(id);
	}
}
