package com.project.asset_management.DTO;

import com.project.asset_management.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	private Integer id;

	private String name;

	private String email;

	private String designation;
	private Integer departmentId;
	private String departmentName;
	
	public EmployeeDTO(Employee employee) {
		id = employee.getId();
		name = employee.getName();
		email = employee.getEmail();
		designation = employee.getDesignation();
		departmentId = employee.getDepartment().getId();
		departmentName = employee.getDepartment().getName();
	}
}
