package com.ravi.resourcemanagementservice.controller;

import com.ravi.resourcemanagementservice.exception.DepartmentNotFoundException;
import com.ravi.resourcemanagementservice.exception.EmployeeNotFoundException;
import com.ravi.resourcemanagementservice.model.*;
import com.ravi.resourcemanagementservice.service.RMSService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RMSController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RMSService rmsService;

    //Get All employees List
    @RequestMapping("/employees")
    public List<Employee> getAllEmployees()  {
        //...
        return rmsService.getAllEmployees();
    }

    //Get All employees which are in same department
    @RequestMapping("/employees/departments/{departmentId}")
    public List<Employee> getAllEmployeesByDepartment(@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {

        return rmsService.getAllEmployeesByDepartment(departmentId);

    }

    //Get a employee via ID
    @RequestMapping("employees/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") String employeeId) throws EmployeeNotFoundException {
        return rmsService.getEmployee(employeeId);
    }

    //Add New Employee Post method
    @RequestMapping(method = RequestMethod.POST, value = "/employees/departments/{departmentId}")
    public String addEmployee(@RequestBody Employee employee,@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {
        return rmsService.addEmployee(employee,departmentId);
    }

    //Update Existing Employee PUT method
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/departments/{departmentId}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException{
       rmsService.updateEmployee(employee,departmentId);

    }

    //Delete Existing Employee DELETE method
    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{employeeId}")
    public void deleteEmployee (@PathVariable("employeeId") String employeeId) throws EmployeeNotFoundException {
        //...
        rmsService.deleteEmployee(employeeId);
    }

    //-------- Department Microservice Calls----------

    //Get All Department List
    @RequestMapping("/departments")
    public List<Department> getAllDepartment() {
        //...
        return rmsService.getAllDepartment();
    }

    //Get a Department via ID
    @RequestMapping("departments/{departmentId}")
    @ApiOperation(value = "Find Department by id",
            notes = "Provide an id to look up specific contact from the Department Table",
            response = Department.class)
    public Department getDepartment(@ApiParam(value = "ID value for the Department you need to retrieve", required = true) @PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {
        return rmsService.getDepartment(departmentId);
    }

    //Add New Department Post method
    @RequestMapping(method = RequestMethod.POST, value = "/departments")
    public String addDepartment(@RequestBody Department department) {
        return rmsService.addDepartment(department);
    }

    //Update Existing Department PATCH method
    @RequestMapping(method = RequestMethod.PUT, value = "/departments")
    public void updateDepartment(@RequestBody Department department) {
        rmsService.updateDepartment(department);
    }

    //Delete Existing Employee DELETE method
    @RequestMapping(method = RequestMethod.DELETE, value = "departments/{departmentId}")
    public void deleteDepartment (@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {
        //...
        rmsService.deleteDepartment(departmentId);
    }


}
