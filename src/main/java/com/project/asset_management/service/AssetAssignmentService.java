package com.project.asset_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.asset_management.DTO.AssetAssignmentResponseDTO;
import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetAssignment;
import com.project.asset_management.entities.AssetStatus;
import com.project.asset_management.entities.Employee;
import com.project.asset_management.exceptions.AssetAssignmentNotFoundException;
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
	
	@Transactional
	public AssetAssignmentResponseDTO createAssetAssignment(Integer employeeId, Integer assetId,String remarks) {
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
		assetAssignmentRepository.save(assetAssignment);
		return new AssetAssignmentResponseDTO(assetAssignment);
	}
	
	@Transactional
	public AssetAssignmentResponseDTO returnAsset(Integer assetAssignmentId,LocalDate returnDate) {
		AssetAssignment assetAssignment = assetAssignmentRepository.findById(assetAssignmentId).orElse(null);
		
		Asset asset = assetAssignment.getAsset();
		asset.setStatus(AssetStatus.AVAILABLE);
		assetAssignment.setReturnedDate(returnDate);
		
		assetRepository.save(asset);
		assetAssignmentRepository.save(assetAssignment);
		
		return new AssetAssignmentResponseDTO(assetAssignment);
	}
	
	public List<AssetAssignmentResponseDTO> getAllAssetAssignments(){
		return assetAssignmentRepository.findAll().stream().map(AssetAssignmentResponseDTO::new).toList();
	}
	
	public AssetAssignmentResponseDTO getAssetAssignmentById(Integer id) {
		
		AssetAssignment assetAssignment = assetAssignmentRepository.findById(id).orElseThrow(()->new AssetAssignmentNotFoundException(id));
		return new AssetAssignmentResponseDTO(assetAssignment);
	}
	
	public List<AssetAssignmentResponseDTO> getAllActiveAssetAssignments(){
		return assetAssignmentRepository.getActiveAssetAssignments().stream().map(AssetAssignmentResponseDTO::new).toList();
	}
	
	public List<AssetAssignmentResponseDTO> getReturnedAssignments(){
		return assetAssignmentRepository.getReturnedAssignments().stream().map(AssetAssignmentResponseDTO::new).toList();
	}


}
