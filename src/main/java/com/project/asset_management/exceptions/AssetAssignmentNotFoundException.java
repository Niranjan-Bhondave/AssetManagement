package com.project.asset_management.exceptions;

public class AssetAssignmentNotFoundException extends RuntimeException{
	public AssetAssignmentNotFoundException(Integer id) {
		super(String.format("Asset Assignment id %d not found",id));
	}
}
