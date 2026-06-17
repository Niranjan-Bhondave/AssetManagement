package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.DTO.EmployeeDTO;
import com.project.asset_management.DTO.Employee_AssetAssignmentDTO;
import com.project.asset_management.DTO.Employee_AssetDTO;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.exceptions.EmployeeNotFoundException;
import com.project.asset_management.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Transactional
	public EmployeeDTO createEmployee(Employee employee) {
		Employee newEmployee =  employeeRepository.save(employee);
		return new EmployeeDTO(newEmployee);
	}
	
	public List<EmployeeDTO> getAllEmployees(){
		return employeeRepository.findAll().stream().map(EmployeeDTO::new).toList();
	}
	
	public EmployeeDTO getEmployeeById(Integer id) {
		Employee employee =  employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
		return new EmployeeDTO(employee);
	}
	
	@Transactional
	public EmployeeDTO updateEmployeeDetails(Integer id, Employee newEmployeeDetails) {
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
		if(changes) {
			employeeRepository.save(currentEmployeeDetails);
		}
		return new EmployeeDTO(currentEmployeeDetails);
	}
	
	public List<EmployeeDTO> getAllEmployeesOfDepartment(Integer id){
		return employeeRepository.findByDepartmentId(id).stream().map(EmployeeDTO::new).toList();
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