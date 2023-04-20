package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Models.Post;
import com.example.demo.Models.users;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUser(users user);
    List<Post> findByUserId(String userId, PageRequest pageable);

    Page<Post> findByUser(users user, PageRequest pageable);
}