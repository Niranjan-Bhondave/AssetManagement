package com.project.asset_management.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AssetAssignmentResponseDTO> createAssetAssignment(
            @RequestParam Integer employeeId,
            @RequestParam Integer assetId,
            @RequestParam(required = false) String remarks) {

        AssetAssignmentResponseDTO response =
                assetAssignmentService.createAssetAssignment(employeeId, assetId, remarks);

        if (response == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{assignmentId}/return")
    public ResponseEntity<AssetAssignmentResponseDTO> returnAsset(
            @PathVariable Integer assignmentId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate returnDate) {

        AssetAssignmentResponseDTO response =
                assetAssignmentService.returnAsset(assignmentId, returnDate);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AssetAssignmentResponseDTO>> getAllAssignments() {
        return ResponseEntity.ok(
                assetAssignmentService.getAllAssetAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetAssignmentResponseDTO> getAssignmentById(
            @PathVariable Integer id) {

        AssetAssignmentResponseDTO response =
                assetAssignmentService.getAssetAssignmentById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<List<AssetAssignmentResponseDTO>> getActiveAssignments() {
        return ResponseEntity.ok(
                assetAssignmentService.getAllActiveAssetAssignments()
        );
    }

    @GetMapping("/returned")
    public ResponseEntity<List<AssetAssignmentResponseDTO>> getReturnedAssignments() {
        return ResponseEntity.ok(
                assetAssignmentService.getReturnedAssignments()
        );
    }
}