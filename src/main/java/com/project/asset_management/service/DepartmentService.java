package com.project.asset_management.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.repositories.DepartmentRepository;
import com.project.asset_management.DTO.DepartmentDTO;
import com.project.asset_management.DTO.EmployeeDTO;
import com.project.asset_management.entities.Department;
import com.project.asset_management.exceptions.DepartmentNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
	private DepartmentRepository departmentRepository;

	@Transactional
	public DepartmentDTO createDepartment(Department department) {
		Department savedDepartment = departmentRepository.save(department);
		return new DepartmentDTO(savedDepartment);
	}
	
	public List<DepartmentDTO> getAllDepartments(){
		return 	departmentRepository.findAll().stream().map(DepartmentDTO::new).toList();
	}
	
	public DepartmentDTO getDepartmentById(Integer id) {
		Department department = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
		return new DepartmentDTO(department);
	}
	
	@Transactional
	public DepartmentDTO updateDepartment(Department newDepartment, Integer id) {
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
		return new DepartmentDTO(oldDepartment);
	}
	
	@Transactional
	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}
	
	public List<EmployeeDTO> findEmployeesByDepartment(Integer id){
		return departmentRepository.findByDepartmentId(id).stream().map(EmployeeDTO::new).toList();
	}
}
