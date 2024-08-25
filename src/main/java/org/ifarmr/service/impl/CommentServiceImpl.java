package org.ifarmr.service.impl;

import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Comment;
import org.ifarmr.entity.Post;
import org.ifarmr.payload.request.CommentDto;
import org.ifarmr.repository.CommentRepository;
import org.ifarmr.repository.PostRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.CommentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void commentOnPost(Long postId, Long userId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        comment.setContent(commentDto.getContent());
        comment.setParentContentId(commentDto.getParentContentId());

        commentRepository.save(comment);
    }
}
