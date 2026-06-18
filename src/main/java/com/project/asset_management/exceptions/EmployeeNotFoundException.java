package com.project.asset_management.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Integer id) {
		super(String.format("Employee with id %d not found",id));
	}

}
