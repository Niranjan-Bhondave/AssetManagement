package com.project.asset_management.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
	public EmployeeNotFoundException(Integer id) {
		super(String.format("Employee with id %d not found",id));
	}

}
