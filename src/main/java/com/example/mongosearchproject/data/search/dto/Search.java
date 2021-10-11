package com.example.mongosearchproject.data.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Search implements Serializable {

    private String name = "AWS";

    private Integer yearFrom;

    private Integer yearTo;

    private String zipCode;

    private String street;

    private String city;

    private List<String> countryKeywords;

    private String textIndexItem1;

    private String textIndexItem2;

    private String username;

    private Integer age;

    private Double salaryFrom;
    private Double salaryTo;

}
