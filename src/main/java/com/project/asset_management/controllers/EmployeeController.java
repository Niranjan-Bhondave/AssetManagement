package com.project.asset_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.asset_management.DTO.EmployeeDTO;
import com.project.asset_management.DTO.Employee_AssetAssignmentDTO;
import com.project.asset_management.DTO.Employee_AssetDTO;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeeController {
	private EmployeeService employeeService;
	
	@PostMapping(path = "api/employees")
	public EmployeeDTO createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping(path = "api/employees")
	public List<EmployeeDTO> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(path = "api/employees/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping(path = "api/employees/{id}")
	public EmployeeDTO updateEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {
		return employeeService.updateEmployeeDetails(id, newEmployee);
	}
	
	@DeleteMapping(path = "api/employees/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
	}
	
	@GetMapping(path = "api/employees/{id}/assets")
	public List<Employee_AssetDTO> findAllAssetsAssignedToAnEmployee(@PathVariable Integer id){
		return employeeService.findAllAssetsAssignedToAnEmployee(id);
	}
	
	@GetMapping(path = "api/employees/{id}/assignments")
	public List<Employee_AssetAssignmentDTO> findAllAssetAssignmentsOfAnEmployee(@PathVariable Integer id){
		return employeeService.findAllAssetAssignmentsOfAnEmployee(id);
	}
	
	
}
