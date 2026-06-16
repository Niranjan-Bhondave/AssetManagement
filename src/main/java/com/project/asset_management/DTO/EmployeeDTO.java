package com.project.asset_management.DTO;

import java.time.LocalDate;

import com.project.asset_management.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String name;
    private String email;
    private String designation;
    private LocalDate joiningDate;

    private Integer departmentId;
    private String departmentName;

    public EmployeeDTO(Employee employee) {

        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.designation = employee.getDesignation();
        this.joiningDate = employee.getJoiningDate();

        if(employee.getDepartment() != null) {
            this.departmentId = employee.getDepartment().getId();
            this.departmentName = employee.getDepartment().getName();
        }
    }
}