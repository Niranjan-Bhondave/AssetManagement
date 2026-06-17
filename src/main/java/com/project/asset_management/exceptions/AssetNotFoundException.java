package com.project.asset_management.exceptions;

public class AssetNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssetNotFoundException(Integer id) {
		super(String.format("Asset with id %d not found",id));
	}
}
