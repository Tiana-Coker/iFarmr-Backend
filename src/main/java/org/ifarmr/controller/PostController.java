package org.ifarmr.controller;

import lombok.RequiredArgsConstructor;
import org.ifarmr.payload.request.CommentDto;
import org.ifarmr.service.CommentService;
import org.ifarmr.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final LikeService likeService;
    private final CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/{postId}/like")
    public void likeOrUnlikePost(@PathVariable Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        likeService.likeOrUnlikePost(postId, currentUsername);
    }

    @PostMapping("/comment")
    public void commentOnPost(@RequestBody CommentDto commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        commentService.commentOnPost(currentUsername, commentDto);
    }
}
