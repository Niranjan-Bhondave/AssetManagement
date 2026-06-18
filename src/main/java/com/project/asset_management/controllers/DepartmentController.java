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

import com.project.asset_management.DTO.DepartmentRequestDTO;
import com.project.asset_management.DTO.DepartmentResponseDTO;
import com.project.asset_management.DTO.EmployeeResponseDTO;
import com.project.asset_management.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

	private final DepartmentService departmentService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO department) {
		return departmentService.createDepartment(department);
	}

	@GetMapping
	public List<DepartmentResponseDTO> getAllDepartments(){
		return departmentService.getAllDepartments();
	}

	@GetMapping(path = "/{id}")
	public DepartmentResponseDTO getDepartmentById(@PathVariable Integer id) {
		return departmentService.getDepartmentById(id);
	}

	@PutMapping(path = "/{id}")
	public DepartmentResponseDTO updateDepartment(@RequestBody DepartmentRequestDTO newDepartment, @PathVariable Integer id) {
		return departmentService.updateDepartment(newDepartment, id);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartment(id);
	}
	
	@GetMapping(path = "/{id}/employees")
	public List<EmployeeResponseDTO> getAllEmployeesOfDepartment(@PathVariable Integer id){
		return departmentService.findEmployeesByDepartment(id);
	}
}
