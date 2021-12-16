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
public class Department {

//    @Id
    @ApiModelProperty(notes = "The unique ID of the Department")
    private String id;
    @ApiModelProperty(notes = "The Name of the Department")
    private String name;

}
