package com.duyhien.apiweb.Repositories;

import com.duyhien.apiweb.Entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByUserIdAndProductId(@Param("userId") Long userId,
                                           @Param("productId") Long productId);
    List<CommentEntity> findByProductId(@Param("productId") Long productId);
}