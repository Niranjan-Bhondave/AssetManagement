package com.project.asset_management.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.project.asset_management.DTO.AssetAssignmentResponseDTO;
import com.project.asset_management.service.AssetAssignmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/asset-assignments")
@AllArgsConstructor
public class AssetAssignmentController {

    private AssetAssignmentService assetAssignmentService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AssetAssignmentResponseDTO createAssetAssignment(@RequestParam Integer employeeId,@RequestParam Integer assetId,@RequestParam(required = false) String remarks) {
    	return assetAssignmentService.createAssetAssignment(employeeId, assetId, remarks);        
    }

    @PutMapping("/{assignmentId}/return")
    public AssetAssignmentResponseDTO returnAsset(@PathVariable Integer assignmentId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
    	return assetAssignmentService.returnAsset(assignmentId, returnDate);
    }

    @GetMapping
    public List<AssetAssignmentResponseDTO> getAllAssignments() {
    	return assetAssignmentService.getAllAssetAssignments();
    }

    @GetMapping("/{id}")
    public AssetAssignmentResponseDTO getAssignmentById(@PathVariable Integer id) {
        return assetAssignmentService.getAssetAssignmentById(id);
    }

    @GetMapping("/active")
    public List<AssetAssignmentResponseDTO> getActiveAssignments() {
    	return assetAssignmentService.getAllActiveAssetAssignments();
    }

    @GetMapping("/returned")
    public List<AssetAssignmentResponseDTO> getReturnedAssignments() {
    	return assetAssignmentService.getReturnedAssignments();
    }
}