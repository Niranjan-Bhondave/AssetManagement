package com.project.asset_management.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.asset_management.entities.Department;
import com.project.asset_management.entities.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
//	@Query("SELECT e FROM EMPLOYEE e WHERE e.department.id =: id")
//	public List<Employee> getAEmployeesOfDepartment(@Param("id")Integer id);
	
	/*
	 * @Query(
    value = """
        SELECT e.*
        FROM employee e
        JOIN department d
            ON e.department_id = d.department_id
        WHERE d.department_id = :id
        """,
    nativeQuery = true
)
List<Employee> getEmployeesOfDepartment(@Param("id") Integer id);
	 */
	
	/*
	 * Option 3: Let Spring Data Generate the Query

This is the cleanest approach.

If `Employee` contains:

```java
@ManyToOne
private Department department;
```

then simply write:

```java
List<Employee> findByDepartmentId(Integer id);
```

No `@Query` needed at all. Spring Data JPA will generate the query automatically.
	 */
	List<Employee> findByDepartmentId(Integer id);
}
