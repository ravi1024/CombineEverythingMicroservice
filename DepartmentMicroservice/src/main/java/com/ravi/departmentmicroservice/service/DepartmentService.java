package com.ravi.departmentmicroservice.service;

import com.ravi.departmentmicroservice.model.Department;
import com.ravi.departmentmicroservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Department> getAllDepartments() {
        //...
        List<Department> departmentList = new ArrayList<>();
        departmentRepository.findAll().forEach(department -> departmentList.add(department));
        return departmentList;
    }

    public Department getDepartment(String departmentId) {
        //...
        return departmentRepository.findById(departmentId).get();
    }


    public String addDepartment(Department department) {
        //...
        departmentRepository.save(department);
        return "New Department saved successfully!";
    }


    public String updateDepartment(Department department) {
        //...
        departmentRepository.save(department);
        return "Department updated Successfully!";
    }

    public String deleteDepartment (String departmentId) {
        //...
        departmentRepository.deleteById(departmentId);
        return "Department Deleted!";
    }
}
