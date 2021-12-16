package com.ravi.resourcemanagementservice.controller;


import com.ravi.resourcemanagementservice.exception.DepartmentNotFoundException;
import com.ravi.resourcemanagementservice.exception.EmployeeNotFoundException;
import com.ravi.resourcemanagementservice.model.DepartmentError;
import com.ravi.resourcemanagementservice.model.EmployeeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeError> handleEmployeeNotFoundException(EmployeeNotFoundException ex)
    {
        EmployeeError employeeError = new EmployeeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<EmployeeError>(employeeError,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<DepartmentError> handleDepartmentNotFoundException(DepartmentNotFoundException ex)
    {
        DepartmentError departmentError = new DepartmentError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<DepartmentError>(departmentError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
