package com.ravi.resourcemanagementservice.service;

import com.ravi.resourcemanagementservice.exception.DepartmentNotFoundException;
import com.ravi.resourcemanagementservice.exception.EmployeeNotFoundException;
import com.ravi.resourcemanagementservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RMSService {

    @Autowired
    private RestTemplate restTemplate;

    //Get All employees List

    public List<Employee> getAllEmployees() {
        //...
        EmployeeList employeeList = restTemplate.getForObject("http://Employee-Microservice/employees", EmployeeList.class);

        List<Employee> listOfEmployee;
        listOfEmployee = employeeList.getEmployeeList();
        return listOfEmployee;
    }

    //Get All employees which are in same department

    public List<Employee> getAllEmployeesByDepartment(@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException{

        try {
            EmployeeListByDepartment employeeListByDepartment = restTemplate
                    .getForObject("http://Employee-Microservice/employees/departments/" + departmentId, EmployeeListByDepartment.class);

            return employeeListByDepartment.getEmployeeListByDepartment();
        }
        catch (Exception ex)
        {
            throw new DepartmentNotFoundException("Invalid Department Id!! No department exists with Id: " + departmentId + " Or There is no employee in this Department: " + departmentId);
        }

    }

    //Get a employee via ID

    public Employee getEmployee(@PathVariable("employeeId") String employeeId) throws EmployeeNotFoundException {

        try {
            return restTemplate.getForObject("http://Employee-Microservice/employees/" + employeeId, Employee.class);
        }
        catch (Exception ex)
        {
            throw new EmployeeNotFoundException("Invalid Employee Id!! No Employee exists with Id: " + employeeId);
        }
    }

    //Add New Employee Post method

    public String addEmployee(@RequestBody Employee employee, @PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {
        try {
            return restTemplate.postForObject("http://Employee-Microservice/employees/departments/" + departmentId, employee, String.class);
        }
        catch (Exception ex)
        {
            throw new DepartmentNotFoundException("Invalid Department Id!! No department exists with Id: " + departmentId);
        }
    }

    //Update Existing Employee PUT method

    public void updateEmployee(@RequestBody Employee employee,@PathVariable("departmentId") String departmentId)  throws DepartmentNotFoundException{
        try {
            restTemplate.put("http://Employee-Microservice/employees/departments/" + departmentId, employee);
        }
        catch (Exception ex)
        {
            throw new DepartmentNotFoundException("Invalid Department Id!! No department exists with Id: " + departmentId);
        }

    }

    //Delete Existing Employee DELETE method

    public void deleteEmployee (@PathVariable("employeeId") String employeeId) throws EmployeeNotFoundException {
        //...
        try {
            restTemplate.delete("http://Employee-Microservice/employees/" + employeeId, String.class);
        }
        catch (Exception ex) {
            throw new EmployeeNotFoundException("Invalid Employee Id!! No Employee exists with Id: " + employeeId);
        }
    }

    //-------- Department Microservice Calls----------

    //Get All Department List

    public List<Department> getAllDepartment() {
        //...
        DepartmentList departmentList = restTemplate.getForObject("http://Department-Microservice/departments/", DepartmentList.class);

        List<Department> listOfDepartment;
        listOfDepartment = departmentList.getDepartmentList();
        return listOfDepartment;
    }

    //Get a Department via ID

    public Department getDepartment(@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {
        try {
            return restTemplate.getForObject("http://Department-Microservice/departments/" + departmentId, Department.class);
        }
        catch (Exception ex)
        {
            throw new DepartmentNotFoundException("Invalid Department Id!! No department exists with Id: " + departmentId);
        }
    }

    //Add New Department Post method

    public String addDepartment(@RequestBody Department department) {
        return restTemplate.postForObject("http://Department-Microservice/departments/",department,String.class);
    }

    //Update Existing Department PATCH method

    public void updateDepartment(@RequestBody Department department) {
        restTemplate.put("http://Department-Microservice/departments/",department);

    }

    //Delete Existing Employee DELETE method

    public void deleteDepartment (@PathVariable("departmentId") String departmentId) throws DepartmentNotFoundException {
        //...
        try {
            restTemplate.delete("http://Department-Microservice/departments/" + departmentId, String.class);
        }
        catch (Exception ex)
        {
            throw new DepartmentNotFoundException("Invalid Department Id!! No department exists with Id: " + departmentId);
        }
    }

}
