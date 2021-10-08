package com.example.mongosearchproject.data.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "department")
public class Department implements Serializable {

    @Id
    private String id;

    @Indexed
    @Field(value = "name")
    private String name;

    @Field(value = "year_of_creation")
    private Integer yearOfCreation;

    private Address address;

    private List<Employee> employees;

}
