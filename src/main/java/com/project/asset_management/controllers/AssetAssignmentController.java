package com.project.asset_management.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.asset_management.DTO.AssetAssignmentDTO;
import com.project.asset_management.service.AssetAssignmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/asset-assignments")
@AllArgsConstructor
public class AssetAssignmentController {

    private AssetAssignmentService assetAssignmentService;

    @PostMapping
    public ResponseEntity<AssetAssignmentDTO> createAssetAssignment(
            @RequestParam Integer employeeId,
            @RequestParam Integer assetId,
            @RequestParam(required = false) String remarks) {

        AssetAssignmentDTO response =
                assetAssignmentService.createAssetAssignment(employeeId, assetId, remarks);

        if (response == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{assignmentId}/return")
    public ResponseEntity<AssetAssignmentDTO> returnAsset(
            @PathVariable Integer assignmentId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate returnDate) {

        AssetAssignmentDTO response =
                assetAssignmentService.returnAsset(assignmentId, returnDate);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AssetAssignmentDTO>> getAllAssignments() {
        return ResponseEntity.ok(
                assetAssignmentService.getAllAssetAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetAssignmentDTO> getAssignmentById(
            @PathVariable Integer id) {

        AssetAssignmentDTO response =
                assetAssignmentService.getAssetAssignmentById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<List<AssetAssignmentDTO>> getActiveAssignments() {
        return ResponseEntity.ok(
                assetAssignmentService.getAllActiveAssetAssignments()
        );
    }

    @GetMapping("/returned")
    public ResponseEntity<List<AssetAssignmentDTO>> getReturnedAssignments() {
        return ResponseEntity.ok(
                assetAssignmentService.getReturnedAssignments()
        );
    }
}