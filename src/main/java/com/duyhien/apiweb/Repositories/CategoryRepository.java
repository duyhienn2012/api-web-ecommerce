package com.duyhien.apiweb.Repositories;

import com.duyhien.apiweb.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
