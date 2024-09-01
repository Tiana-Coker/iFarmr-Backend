package org.ifarmr.repository;

import org.ifarmr.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserIdOrderByDateCreatedDesc(Long userId);
}