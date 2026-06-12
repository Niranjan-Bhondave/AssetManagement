package com.project.asset_management.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.entities.Department;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.repositories.EmployeeProfileRepository;
import com.project.asset_management.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeService {

    private final EmployeeProfileRepository employeeProfileRepository;
	private EmployeeRepository employeeRepository;

    EmployeeService(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllDepartments(){
		return employeeRepository.findAll();
	}
	
	public Employee getDepartmentById(Integer id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployeeDetails(Integer id, Employee newEmployeeDetails) {
		Employee currentEmployeeDetails = employeeRepository.findById(id).orElse(null);
		if(Objects.isNull(currentEmployeeDetails)) {
			System.out.println("Employee not found");
			return null;
		}
		
		currentEmployeeDetails.se
		
	}
}
