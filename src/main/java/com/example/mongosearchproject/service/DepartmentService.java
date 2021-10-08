package com.example.mongosearchproject.service;

import com.example.mongosearchproject.data.document.Employee;
import com.example.mongosearchproject.data.dto.DepartmentAddressDto;
import com.example.mongosearchproject.data.dto.EmployeeDto;
import com.example.mongosearchproject.data.search.dto.Search;
import com.example.mongosearchproject.data.search.response.SearchResult;
import com.github.cloudyrock.mongock.ChangeSet;

import java.util.List;

public interface DepartmentService {

    @ChangeSet(order = "001", id = "department_address_id", author = "dai")
    void createDepartment(DepartmentAddressDto dto);

    void createEmployee(EmployeeDto dto, String depId);

    List<SearchResult> search(Search search);
}
