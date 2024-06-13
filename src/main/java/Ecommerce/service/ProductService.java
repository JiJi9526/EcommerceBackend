package Ecommerce.service;

import Ecommerce.dto.NewProductRequest;
import Ecommerce.dto.ProductGroup;
import Ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(NewProductRequest newProductRequest);
    Product updateProduct(NewProductRequest newProductRequest, UUID id);
    void deleteProduct(Product product);
    List<ProductGroup> getAllProducts();
    ProductGroup getOneProduct(UUID id);
    List<ProductGroup> getProductsByCategory(UUID categoryId);

    Page<ProductGroup> searchProducts(String name, Pageable pageable);
}
