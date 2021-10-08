package com.example.mongosearchproject.repository;

import com.example.mongosearchproject.data.document.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DepartmentRepo extends MongoRepository<Department,String> {
}
