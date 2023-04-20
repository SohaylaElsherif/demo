package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationTypeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Post;
import com.example.demo.Models.users;
import com.example.demo.Repo.PostRepository;
import com.example.demo.Repo.userRepository;

@Service
public class UserService {

    @Autowired
    private userRepository UserRepository;

    @Autowired
    private PostRepository postRepository;

    public users createUser(users user) {
        return UserRepository.save(user);
    }
    public List<Post> listPostsByUser(String userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Optional<users> optionalUser = userRepository.
        if (optionalUser.isPresent()) {
            users user = optionalUser.get();
        return postRepository.findByUserId(userId, pageable);
} else {                        throw new ResourceNotFoundException("user", "id", userId);

}}}