package org.ifarmr.repository;

import org.ifarmr.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);
    List<Post> findByUserIdOrderByDateCreatedDesc(Long userId);

    @Query("SELECT p FROM Post p ORDER BY p.dateCreated DESC")
    Page<Post> findAll(Pageable pageable);
}
