package com.duyhien.apiweb.Repositories;

import com.duyhien.apiweb.Entities.CategoryEntity;
import com.duyhien.apiweb.Entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
    Page<ProductEntity> findAll(Pageable pageable);
    List<ProductEntity> findByCategory(CategoryEntity category);
    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:categoryId IS NULL OR :categoryId = 0 OR p.category.id = :categoryId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%)")
    Page<ProductEntity> searchProducts
            (@Param("categoryId") Long categoryId,
             @Param("keyword") String keyword, Pageable pageable);
    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.productImages WHERE p.id = :productId")
    Optional<ProductEntity> getDetailProduct(@Param("productId") Long productId);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN :productIds")
    List<ProductEntity> findProductsByIds(@Param("productIds") List<Long> productIds);
}
