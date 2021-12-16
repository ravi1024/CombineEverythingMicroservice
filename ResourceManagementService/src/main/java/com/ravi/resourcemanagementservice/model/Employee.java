package com.ravi.resourcemanagementservice.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "The unique ID of the Employee")
    private String id;

    @ApiModelProperty(notes = "The Name of the Employee")
    private String name;

    @ApiModelProperty(notes = "The Email-id of the Employee")
    private String email;
    @ApiModelProperty(notes = "In which of the Department the Employee Belongs to")
    private String departmentId;


}
