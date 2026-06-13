package com.project.asset_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.asset_management.entities.AssetAssignment;

public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM AsssetAssignment WHERE returnedDate IS NULL")
	public List<AssetAssignment> getActiveAssetAssignments();
	
	@Query(nativeQuery = true, value = "SELECT * FROM AsssetAssignment WHERE returnedDate IS NOT NULL")
	public List<AssetAssignment> getReturnedAssignments();

}
