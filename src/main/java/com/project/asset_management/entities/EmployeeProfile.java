package com.project.asset_management.entities;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String address;
	private String phoneNumber;
	private String emergencyContact;
	private String bloodGroup;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee_employeeProfile")
	private Employee employee;
	
}