package com.project.asset_management.entities;

import java.util.List;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String location;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "department", orphanRemoval = true)
	@JsonManagedReference(value = "department_employee")
	private List<Employee> employees;
	
}
