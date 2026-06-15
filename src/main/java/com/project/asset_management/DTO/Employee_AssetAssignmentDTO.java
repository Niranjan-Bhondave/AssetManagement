package com.project.asset_management.DTO;

import java.time.LocalDate;

import com.project.asset_management.entities.AssetAssignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee_AssetAssignmentDTO {
    private Integer assignmentId;

    private Integer assetId;
    private String assetCode;
    private String assetType;

    private LocalDate assignedDate;
    private LocalDate returnedDate;

    private String remarks;
    
    public Employee_AssetAssignmentDTO(AssetAssignment assetAssignment) {
    	assignedDate = assetAssignment.getAssignedDate();
    	assignmentId = assetAssignment.getId();
    	assetCode = assetAssignment.getAsset().getAssetCode();
    	assetType = assetAssignment.getAsset().getAssetType();
    	assetId = assetAssignment.getAsset().getId();
    	returnedDate = assetAssignment.getReturnedDate();
    	remarks = assetAssignment.getRemarks();
    }

}
