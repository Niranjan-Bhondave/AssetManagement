package com.project.asset_management.DTO;

import java.time.LocalDate;

import com.project.asset_management.entities.AssetAssignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetAssignmentDTO {
    private Integer assignmentId;

    private Integer employeeId;
    private String employeeName;

    private LocalDate assignedDate;
    private LocalDate returnedDate;

    private String remarks;
    
    public AssetAssignmentDTO(AssetAssignment assetAssignment) {
    	assignmentId = assetAssignment.getId();
    	employeeId = assetAssignment.getEmployee().getId();
    	employeeName = assetAssignment.getEmployee().getName();
    	assignedDate = assetAssignment.getAssignedDate();
    	returnedDate = assetAssignment.getReturnedDate();
    	remarks = assetAssignment.getRemarks();
    }
}
