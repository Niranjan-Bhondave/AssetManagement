package com.project.asset_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.asset_management.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
