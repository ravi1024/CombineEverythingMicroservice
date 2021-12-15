package com.ravi.resourcemanagementservice.controller;

import com.ravi.resourcemanagementservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RMSController {

    @Autowired
    private RestTemplate restTemplate;


    //Get All employees List
    @RequestMapping("/employees")
    public List<Employee> getAllEmployees() {
        //...
        EmployeeList  employeeList = restTemplate.getForObject("http://Employee-Microservice/employees", EmployeeList.class);

        List<Employee> listOfEmployee;
        listOfEmployee = employeeList.getEmployeeList();
        return listOfEmployee;
    }

    //Get All employees which are in same department
    @RequestMapping("/employees/departments/{departmentId}")
    public List<Employee> getAllEmployeesByDepartment(@PathVariable("departmentId") String departmentId) {

        EmployeeListByDepartment employeeListByDepartment = restTemplate
                .getForObject("http://Employee-Microservice/employees/departments/"+departmentId, EmployeeListByDepartment.class);

        return employeeListByDepartment.getEmployeeListByDepartment();

    }

    //Get a employee via ID
    @RequestMapping("employees/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") String employeeId) {
        return restTemplate.getForObject("http://Employee-Microservice/employees/" + employeeId,Employee.class);
    }

    //Add New Employee Post method
    @RequestMapping(method = RequestMethod.POST, value = "/employees/departments/{departmentId}")
    public String addEmployee(@RequestBody Employee employee,@PathVariable("departmentId") String departmentId) {
        return restTemplate.postForObject("http://Employee-Microservice/employees/departments/"+departmentId,employee,String.class);
    }

    //Update Existing Employee PUT method
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/departments/{departmentId}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable("departmentId") String departmentId) {
       restTemplate.put("http://Employee-Microservice/employees/departments/"+departmentId,employee);

    }

    //Delete Existing Employee DELETE method
    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{employeeId}")
    public void deleteEmployee (@PathVariable("employeeId") String employeeId) {
        //...
        restTemplate.delete("http://Employee-Microservice/employees/" + employeeId, String.class);
    }

    //-------- Department Microservice Calls----------

    //Get All Department List
    @RequestMapping("/departments")
    public List<Department> getAllDepartment() {
        //...
        DepartmentList departmentList = restTemplate.getForObject("http://Department-Microservice/departments/", DepartmentList.class);

        List<Department> listOfDepartment;
        listOfDepartment = departmentList.getDepartmentList();
        return listOfDepartment;
    }

    //Get a Department via ID
    @RequestMapping("departments/{departmentId}")
    public Department getDepartment(@PathVariable("departmentId") String departmentId) {
        return restTemplate.getForObject("http://Department-Microservice/departments/" + departmentId,Department.class);
    }

    //Add New Department Post method
    @RequestMapping(method = RequestMethod.POST, value = "/departments")
    public String addDepartment(@RequestBody Department department) {
        return restTemplate.postForObject("http://Department-Microservice/departments/",department,String.class);
    }

    //Update Existing Department PATCH method
    @RequestMapping(method = RequestMethod.PUT, value = "/departments")
    public void updateDepartment(@RequestBody Department department) {
        restTemplate.put("http://Department-Microservice/departments/",department);

    }

    //Delete Existing Employee DELETE method
    @RequestMapping(method = RequestMethod.DELETE, value = "departments/{departmentId}")
    public void deleteDepartment (@PathVariable("departmentId") String departmentId) {
        //...
        restTemplate.delete("http://Department-Microservice/departments/" + departmentId, String.class);
    }


}
