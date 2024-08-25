package org.ifarmr.service.impl;

import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Like;
import org.ifarmr.entity.Post;
import org.ifarmr.repository.LikeRepository;
import org.ifarmr.repository.PostRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void likeOrUnlikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Like> existingLike = likeRepository.findByPostIdAndUserId(postId, userId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
        } else {
            Like like = new Like();
            like.setPost(post);
            like.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
            likeRepository.save(like);
        }
    }
}
