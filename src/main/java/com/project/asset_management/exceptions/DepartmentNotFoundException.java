package com.project.asset_management.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentNotFoundException(Integer id) {
		super(String.format("Department with id %d not found"));
	}

}
