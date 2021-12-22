package com.ravi.departmentmicroservice.department;


import com.ravi.departmentmicroservice.repository.DepartmentRepository;
import com.ravi.departmentmicroservice.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService underTestDepartmentServiceObj;

    @BeforeEach
    void setUp() {
        underTestDepartmentServiceObj = new DepartmentService(departmentRepository);
    }

    @Test
    void canGetAllDepartments() {
        //when
        underTestDepartmentServiceObj.getAllDepartments();
        //then
        verify(departmentRepository).findAll();
    }


}
