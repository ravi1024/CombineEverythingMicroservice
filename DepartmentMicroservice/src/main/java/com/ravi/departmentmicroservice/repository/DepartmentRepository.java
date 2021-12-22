package com.ravi.departmentmicroservice.repository;

import com.ravi.departmentmicroservice.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,String> {


    @Query("select CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END from Department d where d.id = ?1")
    public boolean searchById(String id);
}
