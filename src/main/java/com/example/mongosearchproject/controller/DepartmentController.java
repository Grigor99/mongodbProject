package com.example.mongosearchproject.controller;


import com.example.mongosearchproject.data.dto.DepartmentAddressDto;
import com.example.mongosearchproject.data.dto.EmployeeDto;
import com.example.mongosearchproject.data.search.dto.Search;
import com.example.mongosearchproject.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentAddressDto dto) {
        departmentService.createDepartment(dto);
        return ResponseEntity.ok("saved");
    }

    @PostMapping(value = "/{depId}/employees")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto dto,
                                            @PathVariable String depId){
        departmentService.createEmployee(dto,depId);
        return ResponseEntity.ok("saved");
    }
    @PostMapping(value = "/search")
    public ResponseEntity<?> search(@RequestBody Search search){
        return ResponseEntity.ok(departmentService.search(search));
    }
}
