package Ecommerce.service;

import Ecommerce.dto.CategoryResponse;
import Ecommerce.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponse createCategory(Category categoryRequest);
    CategoryResponse updateCategory(UUID id,Category categoryRequest);
    CategoryResponse getOneCategory(UUID id);
    List<Category> getAllCategory();
    void deleteCategory(UUID id);
}
