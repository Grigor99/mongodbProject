package com.example.mongosearchproject.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

    private String firstName;

    private String lastName;

    private String username;

    private Integer age;

    private Double salary;

}
