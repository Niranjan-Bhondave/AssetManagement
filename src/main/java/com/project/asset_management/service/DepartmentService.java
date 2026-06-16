package com.project.asset_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.repositories.DepartmentRepository;
import com.project.asset_management.DTO.DepartmentDTO;
import com.project.asset_management.DTO.EmployeeDTO;
import com.project.asset_management.entities.Department;
import com.project.asset_management.entities.Employee;

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
	
	public DepartmentDTO updateDepartment(Department newDepartment, Integer id) {
		Department oldDepartment = departmentRepository.findById(id).orElse(null);
		if(oldDepartment == null)return null;
		
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
			return new DepartmentDTO(oldDepartment);
		}
		
		return null;
	}
	
	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}
	
	public List<EmployeeDTO> findEmployeesByDepartment(Integer id){
		List<Employee> employeesOfDepartment = departmentRepository.findByDepartmentId(id);
		List<EmployeeDTO> responseDTO = new ArrayList<EmployeeDTO>();
		for(Employee employee: employeesOfDepartment) {
			responseDTO.add(new EmployeeDTO(employee));
		}
		
		return responseDTO;
	}
}
