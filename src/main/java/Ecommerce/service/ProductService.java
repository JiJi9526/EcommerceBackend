package Ecommerce.service;

import Ecommerce.dto.ProductGroup;
import Ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Product product);
    List<ProductGroup> getAllProducts();
    List<ProductGroup> getProductsByCategory(UUID category);

    Page<ProductGroup> searchProducts(String name, Pageable pageable);
}
