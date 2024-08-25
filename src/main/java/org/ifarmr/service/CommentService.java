package org.ifarmr.service;

import org.ifarmr.payload.request.CommentDto;

public interface CommentService {
    void commentOnPost(Long postId, Long userId, CommentDto commentDto);
}
