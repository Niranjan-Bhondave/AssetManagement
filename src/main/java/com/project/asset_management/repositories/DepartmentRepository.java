package com.project.asset_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.asset_management.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
