package com.project.asset_management.entities;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AssetAssignment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private LocalDate assignedDate;
	private LocalDate returnedDate;
	private String remarks;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "asset_id")
	@JsonBackReference(value = "asset_assetManagement")
	private Asset asset;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee_assetAssignment")
	private Employee employee;
}
