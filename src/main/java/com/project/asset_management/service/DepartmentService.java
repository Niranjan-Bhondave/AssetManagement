package com.project.asset_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.asset_management.repositories.DepartmentRepository;
import com.project.asset_management.entities.Department;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
	private DepartmentRepository departmentRepository;

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	
	public Department getDepartmentById(Integer id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
}
