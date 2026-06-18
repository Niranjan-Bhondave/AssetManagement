package com.project.asset_management.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssetRequestDTO {
	private String assetCode;
	private String assetType;
	private LocalDate purchaseDate;
	
}
