package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.DTO.EmployeeDTO;
import com.project.asset_management.DTO.Employee_AssetAssignmentDTO;
import com.project.asset_management.DTO.Employee_AssetDTO;
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
	
	public EmployeeDTO createEmployee(Employee employee) {
		Employee newEmployee =  employeeRepository.save(employee);
		return new EmployeeDTO(newEmployee);
	}
	
	public List<EmployeeDTO> getAllEmployees(){
		List<Employee> employees =  employeeRepository.findAll();
		List<EmployeeDTO> responseDTO = new ArrayList<EmployeeDTO>();
		for(Employee employee: employees) {
			responseDTO.add(new EmployeeDTO(employee));
		}
		
		return responseDTO;

	}
	
	public EmployeeDTO getEmployeeById(Integer id) {
		Employee employee =  employeeRepository.findById(id).orElse(null);
		return new EmployeeDTO(employee);
	}
	
	public EmployeeDTO updateEmployeeDetails(Integer id, Employee newEmployeeDetails) {
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
			Employee newEmployee =  employeeRepository.save(currentEmployeeDetails);
			return new EmployeeDTO(newEmployee);
		}
		
		return null;
	}
	
	public List<EmployeeDTO> getAllEmployeesOfDepartment(Integer id){
		List<Employee> employees = employeeRepository.findByDepartmentId(id);
		List<EmployeeDTO> responseDTO = new ArrayList<EmployeeDTO>(); 
		for(Employee employee: employees) {
			responseDTO.add(new EmployeeDTO(employee));
		}
		
		return responseDTO;
	}
	
	public List<Employee_AssetAssignmentDTO> findAllAssetAssignmentsOfAnEmployee(Integer employeeId){
		
		List<AssetAssignment> assetAssignments =  employeeRepository.findAllAssetAssignmentsOfAnEmployee(employeeId); 
		
		List<Employee_AssetAssignmentDTO> responseDTO = new ArrayList<Employee_AssetAssignmentDTO>();
		for(AssetAssignment assetAssignment: assetAssignments) {
			responseDTO.add(new Employee_AssetAssignmentDTO(assetAssignment));
		}
		
		return responseDTO;
	}
	
	public List<Employee_AssetDTO> findAllAssetsAssignedToAnEmployee(Integer employeeId){
		
		List<Asset> assets = employeeRepository.findAllAssetsAssignedToAnEmployee(employeeId);
		List<Employee_AssetDTO> responseDTO = new ArrayList<Employee_AssetDTO>();
		for(Asset asset: assets) {
			responseDTO.add(new Employee_AssetDTO(asset));
		}
		
		return responseDTO;

	}

}