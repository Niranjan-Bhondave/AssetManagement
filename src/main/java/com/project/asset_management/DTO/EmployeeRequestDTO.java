package com.project.asset_management.DTO;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class EmployeeRequestDTO {
	private String name;
	private String email;
	private String designation;
	private LocalDate joiningDate;
	private Integer departmentId;
}
