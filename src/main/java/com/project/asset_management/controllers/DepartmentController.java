package com.project.asset_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.asset_management.DTO.DepartmentRequestDTO;
import com.project.asset_management.DTO.DepartmentResponseDTO;
import com.project.asset_management.DTO.EmployeeResponseDTO;
import com.project.asset_management.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DepartmentController {

	DepartmentService departmentService;
	
	@PostMapping(path = "api/departments")
	public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO department) {
		return departmentService.createDepartment(department);
	}

	@GetMapping(path = "api/departments")
	public List<DepartmentResponseDTO> getAllDepartments(){
		return departmentService.getAllDepartments();
	}

	@GetMapping(path = "api/departments/{id}")
	public DepartmentResponseDTO getDepartmentById(@PathVariable Integer id) {
		return departmentService.getDepartmentById(id);
	}

	@PutMapping(path = "api/departments/{id}")
	public DepartmentResponseDTO updateDepartment(@RequestBody DepartmentRequestDTO newDepartment, @PathVariable Integer id) {
		return departmentService.updateDepartment(newDepartment, id);
	}

	@DeleteMapping(path = "api/departments/{id}")
	public void deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id);
	}
	
	@GetMapping(path = "api/departments/{id}/employees")
	public List<EmployeeResponseDTO> getAllEmployeesOfDepartment(@PathVariable Integer id){
		return departmentService.findEmployeesByDepartment(id);
	}
}
