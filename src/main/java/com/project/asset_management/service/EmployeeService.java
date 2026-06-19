package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.DTO.EmployeeRequestDTO;
import com.project.asset_management.DTO.EmployeeResponseDTO;
import com.project.asset_management.DTO.Employee_AssetAssignmentDTO;
import com.project.asset_management.DTO.Employee_AssetDTO;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.exceptions.DepartmentNotFoundException;
import com.project.asset_management.exceptions.EmployeeNotFoundException;
import com.project.asset_management.repositories.DepartmentRepository;
import com.project.asset_management.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;
	
	@Transactional
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee) {
		Employee newEmployee =  new Employee();
		newEmployee.setDesignation(employee.getDesignation());
		newEmployee.setDepartment(departmentRepository.findById(employee.getDepartmentId()).orElseThrow(()->new DepartmentNotFoundException(employee.getDepartmentId())));
		newEmployee.setName(employee.getName());
		newEmployee.setEmail(employee.getEmail());
		newEmployee.setJoiningDate(employee.getJoiningDate());
	
		return new EmployeeResponseDTO(employeeRepository.save(newEmployee));
	}
	
	public List<EmployeeResponseDTO> getAllEmployees(){
		return employeeRepository.findAll().stream().map(EmployeeResponseDTO::new).toList();
	}
	
	public EmployeeResponseDTO getEmployeeById(Integer id) {
		Employee employee =  employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
		return new EmployeeResponseDTO(employee);
	}
	
	@Transactional
	public EmployeeResponseDTO updateEmployeeDetails(Integer id, EmployeeRequestDTO newEmployeeDetails) {
		Employee currentEmployeeDetails = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
		boolean changes = false;

		String oldName = currentEmployeeDetails.getName();
		String newName = newEmployeeDetails.getName();

		String oldEmail = currentEmployeeDetails.getEmail();
		String newEmail = newEmployeeDetails.getEmail();

		String oldDesignation = currentEmployeeDetails.getDesignation();
		String newDesignation = newEmployeeDetails.getDesignation();

		LocalDate oldJoiningDate = currentEmployeeDetails.getJoiningDate();
		LocalDate newJoiningDate = newEmployeeDetails.getJoiningDate();
		
		Integer oldDepartmentId = currentEmployeeDetails.getDepartment().getId();
		Integer newDepartmentId = newEmployeeDetails.getDepartmentId();
		
		if (!Objects.equals(oldName, newName)) {
		    currentEmployeeDetails.setName(newName);
		    changes = true;
		}

		if (!Objects.equals(oldEmail, newEmail)) {
		    currentEmployeeDetails.setEmail(newEmail);
		    changes = true;
		}

		if (!Objects.equals(oldDesignation, newDesignation)) {
		    currentEmployeeDetails.setDesignation(newDesignation);
		    changes = true;
		}

		if (!Objects.equals(oldJoiningDate, newJoiningDate)) {
		    currentEmployeeDetails.setJoiningDate(newJoiningDate);
		    changes = true;
		}
		
		if (!Objects.equals(oldDepartmentId, newDepartmentId) && newDepartmentId != null && departmentRepository.findById(newDepartmentId) != null) {
		    currentEmployeeDetails.setDepartment(departmentRepository.findById(newDepartmentId).orElse(null));
		    changes = true;
		}
 
		if(changes) {
			employeeRepository.save(currentEmployeeDetails);
		}
		
		
		return new EmployeeResponseDTO(currentEmployeeDetails);
	}
	
	public List<EmployeeResponseDTO> getAllEmployeesOfDepartment(Integer id){
		return employeeRepository.findByDepartmentId(id).stream().map(EmployeeResponseDTO::new).toList();
	}
	
	public List<Employee_AssetAssignmentDTO> findAllAssetAssignmentsOfAnEmployee(Integer employeeId){
		return employeeRepository.findAllAssetAssignmentsOfAnEmployee(employeeId).stream().map(Employee_AssetAssignmentDTO::new).toList();
	}
	
	public List<Employee_AssetDTO> findAllAssetsAssignedToAnEmployee(Integer employeeId){		
		return employeeRepository.findAllAssetsAssignedToAnEmployee(employeeId).stream().map(Employee_AssetDTO::new).toList();
	}
	
	@Transactional
	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

}