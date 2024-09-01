package org.ifarmr.repository;

import org.ifarmr.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
    List<Like> findByUserIdOrderByDateCreatedDesc(Long userId);
}