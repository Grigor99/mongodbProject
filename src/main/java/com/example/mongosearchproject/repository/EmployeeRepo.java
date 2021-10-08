package com.example.mongosearchproject.repository;

import com.example.mongosearchproject.data.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee,String> {
}
