package com.project.asset_management.DTO;

import java.time.LocalDate;

import com.project.asset_management.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdation_DTO extends EmployeeDTO{
	private LocalDate joiningDate;
	public EmployeeUpdation_DTO(Employee employee) {
		super(employee);
		joiningDate = employee.getJoiningDate();
	}
}
