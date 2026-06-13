package com.project.asset_management.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String assetCode;
	private String assetType;
	private LocalDate purchaseDate;
	
	@Enumerated(EnumType.STRING)
	private AssetStatus status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "asset")
	@JsonManagedReference(value = "asset_assetAssignment")
	private List<AssetAssignment> assetAssignments;
}
