package com.ravi.departmentmicroservice.controller;

import com.ravi.departmentmicroservice.model.Department;
import com.ravi.departmentmicroservice.model.DepartmentList;
import com.ravi.departmentmicroservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentList departmentList;

    @RequestMapping("/testapi")
    public String testApi() {
        return "Api is Running";
    }

    @RequestMapping("/departments")
    public DepartmentList getAllDepartments() {
        //...
        List<Department> listOfDepartment;
        listOfDepartment = departmentService.getAllDepartments();
       departmentList.setDepartmentList(listOfDepartment);
       return departmentList;
    }


    @RequestMapping("/departments/{departmentId}")
    public Department getDepartment(@PathVariable("departmentId") String departmentId) {
        //...
        return departmentService.getDepartment(departmentId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/departments")
    public String addDepartment(@RequestBody Department department) {
        //...
        return departmentService.addDepartment(department);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/departments")
    public String updateDepartment(@RequestBody Department department) {
        //...
        return departmentService.updateDepartment(department);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/departments/{departmentId}")
    public String deleteDepartment (@PathVariable("departmentId") String departmentId) {
        //...
        return departmentService.deleteDepartment(departmentId);
    }
}
