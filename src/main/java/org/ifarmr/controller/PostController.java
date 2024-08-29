package org.ifarmr.controller;


import lombok.RequiredArgsConstructor;
import org.ifarmr.payload.request.CommentDto;
import org.ifarmr.payload.request.PostRequest;
import org.ifarmr.payload.response.PopularPostResponse;
import org.ifarmr.payload.response.PostResponse;
import org.ifarmr.service.PostService;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponse> createPost(@ModelAttribute PostRequest postRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract username from the Authentication object
        String userName = authentication.getName();

        // Call the service method to create the post
        PostResponse postResponse = postService.createPost(postRequest, userName);

        return ResponseEntity.ok(postResponse);
    }

    @PostMapping("/{postId}/like")
    public String likeOrUnlikePost(@PathVariable Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        return postService.likeOrUnlikePost(postId, currentUsername);
    }

    @PostMapping("/comment")
    public CommentDto commentOnPost(@RequestBody CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        return postService.commentOnPost(currentUsername, commentDto);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PopularPostResponse>> getPopularPosts() {
        List<PopularPostResponse> topPosts = postService.getPopularPosts();
        return ResponseEntity.ok(topPosts);
    }

    @GetMapping("/user-posts")
    public ResponseEntity<List<PostResponse>> getPostsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        List<PostResponse> userPosts = postService.getPostsByUser(currentUsername);
        return ResponseEntity.ok(userPosts);
    }
}
