package com.project.asset_management.DTO;

import com.project.asset_management.entities.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
	private Integer id;
	private String name;
	private String location;
	
	public DepartmentDTO(Department department) {
		id = department.getId();
		name = department.getName();
		location = department.getLocation();
	}
}
