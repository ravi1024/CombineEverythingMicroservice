package com.ravi.employeemicroservice.controller;

import com.ravi.employeemicroservice.model.*;
import com.ravi.employeemicroservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeList employeeList;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeListByDepartment employeeListByDepartment;

    @RequestMapping("/testapi")
    public String testApi() {
        return "Api is Running";
    }

  /*  @RequestMapping("/employees")
    public List<Employee> getAllEmployees() {
        //...
        List<Employee> employeeList;
        employeeList = employeeService.getAllEmployees();
        return employeeList;
    }
*/
    @RequestMapping("/employees")
    public EmployeeList getAllEmployees() {

        List<Employee> listOfEmployee;
        listOfEmployee = employeeService.getAllEmployees();
        employeeList.setEmployeeList(listOfEmployee);
        return employeeList;
        /*I Left Here..*/

//        employeeList = employeeService.getAllEmployees();
//        return employeeList;
    }


    @RequestMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") String employeeId) {
        //...
        return employeeService.getEmployee(employeeId);
    }

    //Add new Employee
   /* @RequestMapping(method = RequestMethod.POST, value = "/employees")
    public String addEmployee(@RequestBody Employee employee) {
        //...
        return employeeService.addEmployee(employee);
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/employees/departments/{departmentId}")
    public String addEmployee(@RequestBody Employee employee, @PathVariable("departmentId") String departmentId) throws Exception {
        //...
        DepartmentList departmentList = restTemplate.getForObject("http://Department-Microservice/departments/", DepartmentList.class);

        List<Department> listOfDepartment;
        listOfDepartment = departmentList.getDepartmentList();
        List<String> listOfDepartmentIds = new ArrayList<>();
        listOfDepartment.forEach(department -> listOfDepartmentIds.add(department.getId()));

        if(listOfDepartmentIds.contains(departmentId))
        {
            employee.setDepartmentId(departmentId);
        return employeeService.addEmployee(employee);
        }
        else
        {
            throw new Exception();
        }
    }

    //Get All employees which are in same department
    @RequestMapping("/employees/departments/{departmentId}")
    public EmployeeListByDepartment getAllEmployeesByDepartment(@PathVariable("departmentId") String departmentId) throws Exception {
        List<Employee> listOfEmployeesByDepartmentId;
        listOfEmployeesByDepartmentId = employeeService.getAllEmployeesByDepartment(departmentId);
        employeeListByDepartment.setEmployeeListByDepartment(listOfEmployeesByDepartmentId);
        return employeeListByDepartment;
    }


    //Update Existing Employee
    @RequestMapping(method = RequestMethod.PUT, value = "/employees/departments/{departmentId}")
    public String updateEmployee(@RequestBody Employee employee, @PathVariable("departmentId") String departmentId) throws Exception {
        //...

        DepartmentList departmentList = restTemplate.getForObject("http://Department-Microservice/departments/", DepartmentList.class);

        List<Department> listOfDepartment;
        listOfDepartment = departmentList.getDepartmentList();
        List<String> listOfDepartmentIds = new ArrayList<>();
        listOfDepartment.forEach(department -> listOfDepartmentIds.add(department.getId()));

        if(listOfDepartmentIds.contains(departmentId))
        {
            employee.setDepartmentId(departmentId);
            return employeeService.updateEmployee(employee);
        }
        else
        {
            throw new Exception();
        }


    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{employeeId}")
    public String deleteEmployee (@PathVariable("employeeId") String employeeId) {
        //...
        return employeeService.deleteEmployee(employeeId);
    }


}
