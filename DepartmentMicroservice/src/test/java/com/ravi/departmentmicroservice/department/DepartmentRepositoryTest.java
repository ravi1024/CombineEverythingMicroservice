package com.ravi.departmentmicroservice.department;


import com.ravi.departmentmicroservice.model.Department;
import com.ravi.departmentmicroservice.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository underTestObjOfDepartmentRepository;

    @AfterEach
    void tearDown() {
        underTestObjOfDepartmentRepository.deleteAll();
    }

    @Test
    void itShouldCheckWhenDepartmentIdExists() {
        //given
        String id="testDep01";
        Department department =new Department(id,"TestName");
        underTestObjOfDepartmentRepository.save(department);

        //when
        boolean isExists =  underTestObjOfDepartmentRepository.searchById(id);

        //then
        assertThat(isExists).isTrue();
    }

    @Test
    void itShouldCheckDepartmentIdDoesNotExists() {
        //given
        String id="testDep01";
        //when
        boolean isExists =  underTestObjOfDepartmentRepository.searchById(id);
        //then
        assertThat(isExists).isFalse();
    }
}
