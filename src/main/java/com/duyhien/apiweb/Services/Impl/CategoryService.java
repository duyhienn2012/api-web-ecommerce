package com.duyhien.apiweb.Services.Impl;

import com.duyhien.apiweb.DTO.Request.CategoryDTO;
import com.duyhien.apiweb.Entities.CategoryEntity;
import com.duyhien.apiweb.Entities.ProductEntity;
import com.duyhien.apiweb.Repositories.CategoryRepository;
import com.duyhien.apiweb.Repositories.ProductRepository;
import com.duyhien.apiweb.Services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CategoryEntity createCategory(CategoryDTO categoryDTO) {
        CategoryEntity newCategory = CategoryEntity
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public CategoryEntity getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public CategoryEntity updateCategory(long categoryId,
                                   CategoryDTO categoryDTO) {
        CategoryEntity existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    @Transactional
    public CategoryEntity deleteCategory(long id) throws Exception {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        List<ProductEntity> products = productRepository.findByCategory(category);
        if (!products.isEmpty()) {
            throw new IllegalStateException("Cannot delete category with associated products");
        } else {
            categoryRepository.deleteById(id);
            return  category;
        }
    }
}
