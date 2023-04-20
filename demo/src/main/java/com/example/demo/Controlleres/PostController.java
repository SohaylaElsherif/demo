package com.example.demo.Controlleres;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Post;
import com.example.demo.Models.Reviews;
import com.example.demo.Services.PostService;
@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    @Autowired
    private PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> listPosts(@RequestParam(defaultValue = "0") int pageNo,
                                                @RequestParam(defaultValue = "10") int pageSize) {
        List<Post> posts = postService.listPosts(pageNo, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<List<Post>> listTopPosts(@RequestParam(defaultValue = "0") int pageNo,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        List<Post> topPosts = postService.listTopPosts(pageNo, pageSize);
        return new ResponseEntity<>(topPosts, HttpStatus.OK);
    }

    @PostMapping("/{postId}/reviews")
    public ResponseEntity<Reviews> addReviewToPost(@PathVariable String postId, @RequestBody Reviews review) {
        Reviews addedReview = postService.addReviewToPost(postId, review);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }
}