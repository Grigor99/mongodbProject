package com.example.mongosearchproject.repository;

import com.example.mongosearchproject.data.document.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends MongoRepository<Address,String> {
}
