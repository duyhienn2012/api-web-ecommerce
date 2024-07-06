package com.duyhien.apiweb.Services;


import com.duyhien.apiweb.DTO.Request.CategoryDTO;
import com.duyhien.apiweb.Entities.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    CategoryEntity createCategory(CategoryDTO category);
    CategoryEntity getCategoryById(long id);
    List<CategoryEntity> getAllCategories();
    CategoryEntity updateCategory(long categoryId, CategoryDTO category);
    CategoryEntity deleteCategory(long id) throws Exception;

}
