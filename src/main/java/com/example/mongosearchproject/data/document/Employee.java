package com.example.mongosearchproject.data.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Employee implements Serializable {

    @Id
    private String id;

    @Field(value = "first_name")
    @TextIndexed(weight = 2F)
    private String firstName;

    @Field(value = "last_name")
    @TextIndexed
    private String lastName;

    @Field(value = "username")
    private String username;

    @Field(value = "age")
    private Integer age;

    @Field(value = "salary")
    private Double salary;

    @Field(value = "department_id")
    private String departmentId;

    private boolean removed;
}
