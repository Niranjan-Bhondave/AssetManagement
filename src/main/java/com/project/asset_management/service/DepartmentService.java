package com.project.asset_management.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.repositories.DepartmentRepository;
import com.project.asset_management.DTO.DepartmentRequestDTO;
import com.project.asset_management.DTO.DepartmentResponseDTO;
import com.project.asset_management.DTO.EmployeeResponseDTO;
import com.project.asset_management.entities.Department;
import com.project.asset_management.exceptions.DepartmentNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
	private DepartmentRepository departmentRepository;

	@Transactional
	public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
		Department department = new Department();
		department.setName(departmentRequestDTO.getName());
		department.setLocation(departmentRequestDTO.getLocation());
		return new DepartmentResponseDTO(departmentRepository.save(department));
	}
	
	public List<DepartmentResponseDTO> getAllDepartments(){
		return 	departmentRepository.findAll().stream().map(DepartmentResponseDTO::new).toList();
	}
	
	public DepartmentResponseDTO getDepartmentById(Integer id) {
		Department department = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
		return new DepartmentResponseDTO(department);
	}
	
	@Transactional
	public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO newDepartment, Integer id) {
		Department oldDepartment = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
		
		String oldName = oldDepartment.getName();
		String newName = newDepartment.getName();
		
		String oldLocation = oldDepartment.getLocation();
		String newLocation = newDepartment.getLocation();
		
		boolean changes = false;
		if(!Objects.equals(oldName,newName)) {
			changes = true;
			oldDepartment.setName(newName);
		}
		
		if(!Objects.equals(oldLocation,newLocation)) {
			changes = true;
			oldDepartment.setLocation(newLocation);
		}
		
		if(changes == true) {
			departmentRepository.save(oldDepartment);
		}
		return new DepartmentResponseDTO(oldDepartment);
	}
	
	@Transactional
	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}
	
	public List<EmployeeResponseDTO> findEmployeesByDepartment(Integer id){
		return departmentRepository.findByDepartmentId(id).stream().map(EmployeeResponseDTO::new).toList();
	}
}
