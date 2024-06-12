package Ecommerce.service.serviceImpl;

import Ecommerce.dto.CategoryResponse;
import Ecommerce.entity.Category;
import Ecommerce.repo.CategoryRepo;
import Ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepo categoryRepo;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoryResponse createCategory(Category categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setCreateDate(LocalDate.now());
        category.setUpdateDate(LocalDate.now());
        categoryRepo.save(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(UUID id, Category categoryRequest) {
        Category category= categoryRepo.findCategoryById(id);
        category.setName(categoryRequest.getName());
        category.setUpdateDate(LocalDate.now());
        categoryRepo.save(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse getOneCategory(UUID id) {
        Category category= categoryRepo.findCategoryById(id);

        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public void deleteCategory(UUID id) {
        Category category=categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepo.delete(category);
    }
}
