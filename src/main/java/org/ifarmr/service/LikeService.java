package org.ifarmr.service;

public interface LikeService {
    void likeOrUnlikePost(Long postId, Long userId);
}