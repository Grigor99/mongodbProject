package com.example.mongosearchproject.data.search.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResult implements Serializable {
    private String name;

    private Integer yearOfCreation;

    private String zipCode;

    private String street;

    private String city;

    private String country;

    private String firstName;

    private String lastName;

//    private String username;

    private Integer age;

//    private Double salary;
}
