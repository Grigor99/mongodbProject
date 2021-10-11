package com.example.mongosearchproject.service;

import com.example.mongosearchproject.data.document.Department;
import com.example.mongosearchproject.data.dto.DepartmentAddressDto;
import com.example.mongosearchproject.data.dto.EmployeeDto;
import com.example.mongosearchproject.data.search.dto.Search;

import java.util.List;

public interface DepartmentService {

    void createDepartment(DepartmentAddressDto dto);

    void createEmployee(EmployeeDto dto, String depId);

    List<Department> search(Search search);
}
