package Ecommerce.controller;

import Ecommerce.dto.NewProductRequest;
import Ecommerce.dto.ProductGroup;
import Ecommerce.entity.Product;
import Ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody NewProductRequest newProductRequest) {
        Product updatedProduct = productService.updateProduct( newProductRequest, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProductGroup>> getAllProducts() {
        List<ProductGroup> productGroups = productService.getAllProducts();
        return new ResponseEntity<>(productGroups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGroup> getOneProduct(@PathVariable UUID id){
        ProductGroup productGroup=productService.getOneProduct(id);
        return new ResponseEntity<>(productGroup, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductGroup>> getProductsByCategory(@PathVariable UUID categoryId) {
        List<ProductGroup> productGroups = productService.getProductsByCategory(categoryId);
        return new ResponseEntity<>(productGroups, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductGroup>> searchProducts(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductGroup> productGroups = productService.searchProducts(name, pageable);
        return new ResponseEntity<>(productGroups.getContent(), HttpStatus.OK);
    }
}
