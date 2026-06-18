package com.project.asset_management.DTO;

import java.time.LocalDate;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetResponseDTO {
	private Integer id;
	private String assetCode;
	private String assetType;
    private LocalDate purchaseDate;
    private AssetStatus status;

	public AssetResponseDTO(Asset asset) {
		id = asset.getId();
		assetCode = asset.getAssetCode();
		assetType = asset.getAssetType();
		purchaseDate = asset.getPurchaseDate();
		status = asset.getStatus();
	}
}
