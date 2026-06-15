package com.project.asset_management.DTO;

import com.project.asset_management.entities.Asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee_AssetDTO {
	private Integer id;
	private String assetCode;
	private String assetType;
	
	public Employee_AssetDTO(Asset asset) {
		id = asset.getId();
		assetCode = asset.getAssetCode();
		assetType = asset.getAssetType();
	}
}
