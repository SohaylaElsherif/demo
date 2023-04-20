package com.example.demo.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;
import javax.management.relation.RelationTypeNotFoundException;

import org.springframework.stereotype.Service;

import com.example.demo.Models.Post;
import com.example.demo.Models.Reviews;
import com.example.demo.Models.users;
import com.example.demo.Repo.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(users user) {
        return postRepository.findByUser(user);
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> listPosts(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Page<Post> pageResult = postRepository.findAll(pageable);
        return pageResult.getContent();
    }

    public List<Post> listTopPosts(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by("avgRating").descending());
        Page<Post> pageResult = postRepository.findAll(pageable);
        return pageResult.getContent();
    }
    public Reviews addReviewToPost(String postId, Reviews review) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            List<Reviews> reviews = post.getReviews();
            reviews.add(review);
            post.setReviews(reviews);
            int totalRating = post.getReviews().stream().mapToInt(Reviews::getRating).sum();
            double avgRating = (double) totalRating / post.getReviews().size();
            post.setAvgRating(avgRating);
            postRepository.save(post);
            return review;
        } else {                        throw new ResourceNotFoundException("Post", "id", postId);

        }
    }

	

}