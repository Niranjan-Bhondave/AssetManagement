package com.project.asset_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.repositories.DepartmentRepository;
import com.project.asset_management.DTO.DepartmentDTO;
import com.project.asset_management.entities.Department;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
	private DepartmentRepository departmentRepository;

	public DepartmentDTO createDepartment(Department department) {
		Department savedDepartment = departmentRepository.save(department);
		return new DepartmentDTO(savedDepartment);
	}
	
	public List<DepartmentDTO> getAllDepartments(){
		List<Department> departments = departmentRepository.findAll();
		List<DepartmentDTO> responseDTO = new ArrayList<DepartmentDTO>();
		for(Department department: departments) {
			responseDTO.add(new DepartmentDTO(department));
		}
		
		return responseDTO;

	}
	
	public DepartmentDTO getDepartmentById(Integer id) {
		Department department = departmentRepository.findById(id).orElse(null);
		if(Objects.isNull(department))return null;
		return new DepartmentDTO(department);
	}
	
}
