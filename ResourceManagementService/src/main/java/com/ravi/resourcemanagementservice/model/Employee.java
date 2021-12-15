package com.ravi.resourcemanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.Entity;
//import javax.persistence.Id;

//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

//    @Id
    private String id;
    private String name;
    private String email;
    private String departmentId;


}
