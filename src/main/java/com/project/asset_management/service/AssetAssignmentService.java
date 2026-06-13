package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetAssignment;
import com.project.asset_management.entities.AssetStatus;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.repositories.AssetAssignmentRepository;
import com.project.asset_management.repositories.AssetRepository;
import com.project.asset_management.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AssetAssignmentService {
	private AssetAssignmentRepository assetAssignmentRepository;
	private EmployeeRepository employeeRepository;
	private AssetRepository assetRepository;
	
	public AssetAssignment createAssetAssignment(Integer employeeId, Integer assetId,String remarks) {
		AssetAssignment assetAssignment = new AssetAssignment();
		assetAssignment.setAssignedDate(LocalDate.now());
		
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		Asset asset = assetRepository.findById(assetId).orElse(null);
		
		if(Objects.isNull(asset) || Objects.isNull(employee))return null;
		
		asset.setStatus(AssetStatus.ASSIGNED);
		assetAssignment.setAsset(asset);
		assetAssignment.setEmployee(employee);
		if(!Objects.isNull(remarks))
			assetAssignment.setRemarks(remarks);
		
		
		assetRepository.save(asset);
		return assetAssignmentRepository.save(assetAssignment);
	}
	
	public AssetAssignment returnAsset(Integer assetAssignmentId,LocalDate returnDate) {
		AssetAssignment assetAssignment = assetAssignmentRepository.findById(assetAssignmentId).orElse(null);
		if(Objects.isNull(assetAssignment))return null;
		
		Asset asset = assetAssignment.getAsset();
		asset.setStatus(AssetStatus.AVAILABLE);
		assetAssignment.setReturnedDate(returnDate);
		
		assetRepository.save(asset);
		return assetAssignmentRepository.save(assetAssignment);
	}
	
	public List<AssetAssignment> getAllDepartments(){
		return assetAssignmentRepository.findAll();
	}
	
	public AssetAssignment getDepartmentById(Integer id) {
		return assetAssignmentRepository.findById(id).orElse(null);
	}
	
	public List<AssetAssignment> getAllActiveAssetAssignments(){
		return assetAssignmentRepository.getActiveAssetAssignments();
	}
	
	public List<AssetAssignment> getReturnedAssignments(){
		return assetAssignmentRepository.getReturnedAssignments();
	}


}
