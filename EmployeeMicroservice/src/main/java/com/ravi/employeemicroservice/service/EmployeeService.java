package com.ravi.employeemicroservice.service;

import com.ravi.employeemicroservice.model.Employee;
import com.ravi.employeemicroservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeesByDepartment(String departmentId) throws Exception {


        List<Employee> listOfEmployeeByDepartment = employeeRepository.findByDepartmentId(departmentId);
      if(listOfEmployeeByDepartment.size() != 0)
      {
          return listOfEmployeeByDepartment;
      }
      else
      {
          throw new Exception();
      }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }

    public Employee getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "New Employee saved successfully!";
    }

    public String updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Employee updated Successfully!";
    }

    public String deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Employee Deleted!";
    }
}
