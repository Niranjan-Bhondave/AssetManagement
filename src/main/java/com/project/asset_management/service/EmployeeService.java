package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetAssignment;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllDepartments(){
		return employeeRepository.findAll();
	}
	
	public Employee getDepartmentById(Integer id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployeeDetails(Integer id, Employee newEmployeeDetails) {
		Employee currentEmployeeDetails = employeeRepository.findById(id).orElse(null);
		if(Objects.isNull(currentEmployeeDetails)) {
			System.out.println("Employee not found");
			return null;
		}
		boolean changes = false;

		String oldName = currentEmployeeDetails.getName();
		String newName = newEmployeeDetails.getName();

		String oldEmail = currentEmployeeDetails.getEmail();
		String newEmail = newEmployeeDetails.getEmail();

		String oldDesignation = currentEmployeeDetails.getDesignation();
		String newDesignation = newEmployeeDetails.getDesignation();

		LocalDate oldJoiningDate = currentEmployeeDetails.getJoiningDate();
		LocalDate newJoiningDate = newEmployeeDetails.getJoiningDate();
		
		if(oldName == null && newName != null  || oldName.compareTo(newName) == 0) {
		    currentEmployeeDetails.setName(newName);
		    changes = true;
		}
		
		if (!Objects.equals(oldName, newName)) {
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
			return employeeRepository.save(currentEmployeeDetails);
		}
		
		return null;
	}
	
	public List<Employee> getAllEmployeesOfDepartment(Integer id){
		return employeeRepository.findByDepartmentId(id);
	}
	
	public List<AssetAssignment> findAllAssetAssignmentsOfAnEmployee(Integer employeeId){
		return employeeRepository.findAllAssetAssignmentsOfAnEmployee(employeeId); 
	}
	
	public List<Asset> findAllAssetsAssignedToAnEmployee(Integer employeeId){
		return employeeRepository.findAllAssetsAssignedToAnEmployee(employeeId);
	}

}