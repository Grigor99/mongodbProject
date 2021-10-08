package com.example.mongosearchproject.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentAddressDto implements Serializable {

    private String id;
    private String name;
    private Integer yearOfCreation;
    private String zipCode;
    private String street;
    private String city;
    private String country;
    private String firstName;
    private String lastName;
    private String username;
    private Integer age;
    private Double salary;
}
