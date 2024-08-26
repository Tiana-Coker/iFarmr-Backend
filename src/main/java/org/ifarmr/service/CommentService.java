package org.ifarmr.service;

import org.ifarmr.payload.request.CommentDto;

public interface CommentService {
    void commentOnPost(String username, CommentDto commentDto);
}
