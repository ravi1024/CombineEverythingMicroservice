package com.ravi.employeemicroservice.repository;

import com.ravi.employeemicroservice.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,String> {
    public List<Employee> findByDepartmentId(String departmentId);
}
