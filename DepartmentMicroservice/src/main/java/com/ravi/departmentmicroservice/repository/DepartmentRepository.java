package com.ravi.departmentmicroservice.repository;

import com.ravi.departmentmicroservice.model.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,String> {
}
