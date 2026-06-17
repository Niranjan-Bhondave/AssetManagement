package com.project.asset_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.asset_management.entities.Asset;
import com.project.asset_management.entities.AssetAssignment;
import com.project.asset_management.entities.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findByDepartmentId(Integer departmentId);
	
	@Query(nativeQuery = true,value = "SELECT * from AssetAssignment WHERE employee_id=:employeeId")
	public List<AssetAssignment> findAllAssetAssignmentsOfAnEmployee(@Param("employeeId") Integer employeeId);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM asset a JOIN assetAssignment aa ON aa.asset_id = a.id WHERE aa.employee_id = :employeeId AND aa.returnedDate = NULL")
	public List<Asset> findAllAssetsAssignedToAnEmployee(@Param("employeeId") Integer employeeId);
}
