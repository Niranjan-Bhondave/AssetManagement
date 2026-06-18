package com.project.asset_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.asset_management.DTO.EmployeeRequestDTO;
import com.project.asset_management.DTO.EmployeeResponseDTO;
import com.project.asset_management.DTO.Employee_AssetAssignmentDTO;
import com.project.asset_management.DTO.Employee_AssetDTO;
import com.project.asset_management.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping
	public List<EmployeeResponseDTO> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(path = "/{id}")
	public EmployeeResponseDTO getEmployeeById(@PathVariable Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping(path = "/{id}")
	public EmployeeResponseDTO updateEmployee(@RequestBody EmployeeRequestDTO newEmployee, @PathVariable Integer id) {
		return employeeService.updateEmployeeDetails(id, newEmployee);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
	}
	
	@GetMapping(path = "/{id}/assets")
	public List<Employee_AssetDTO> findAllAssetsAssignedToAnEmployee(@PathVariable Integer id){
		return employeeService.findAllAssetsAssignedToAnEmployee(id);
	}
	
	@GetMapping(path = "/{id}/assignments")
	public List<Employee_AssetAssignmentDTO> findAllAssetAssignmentsOfAnEmployee(@PathVariable Integer id){
		return employeeService.findAllAssetAssignmentsOfAnEmployee(id);
	}
	
	
}
