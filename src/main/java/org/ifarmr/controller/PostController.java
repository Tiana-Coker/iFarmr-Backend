package org.ifarmr.controller;


import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Post;
import org.ifarmr.payload.request.PostRequest;
import org.ifarmr.payload.response.PopularPostResponse;
import org.ifarmr.payload.response.PostResponse;
import org.ifarmr.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@ModelAttribute PostRequest postRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract username from the Authentication object
        String userName = authentication.getName();

        // Call the service method to create the post
        PostResponse postResponse = postService.createPost(postRequest, userName);

        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PopularPostResponse>> getPopularPosts() {
        List<PopularPostResponse> topPosts = postService.getPopularPosts();
        return ResponseEntity.ok(topPosts);
    }



}
