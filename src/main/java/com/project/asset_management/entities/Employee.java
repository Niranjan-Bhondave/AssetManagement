package com.project.asset_management.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@Email
	private String email;
	
	private String designation;
	
	private LocalDate joiningDate;
	
	@ManyToOne
	@JsonBackReference(value = "department_employee")
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "employee")
	@JsonManagedReference(value = "employee_employeeProfile")
	private EmployeeProfile employeeProfile;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
	@JsonManagedReference(value = "employee_assetManagement")
	private List<AssetAssignment> assetAssignments;
}
