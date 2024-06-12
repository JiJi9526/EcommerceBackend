package Ecommerce.controller;

import Ecommerce.dto.CategoryResponse;
import Ecommerce.entity.Category;
import Ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody Category category){
        CategoryResponse categoryResponse=categoryService.createCategory(category);
        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @RequestBody Category category){
        CategoryResponse categoryResponse= categoryService.updateCategory(id, category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category>categories= categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getOneCategory(@PathVariable UUID id){
        CategoryResponse categoryResponse= categoryService.getOneCategory(id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category deleted by id "+id , HttpStatus.NO_CONTENT);
    }
}
