package com.example.demo.Repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.users;


@Repository
public interface userRepository extends MongoRepository<users, String> {
    
    List<users> findById(String id);

    
}
