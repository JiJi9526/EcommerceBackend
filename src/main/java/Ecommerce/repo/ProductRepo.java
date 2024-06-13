package Ecommerce.repo;

import Ecommerce.dto.ProductGroup;
import Ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    List<Product> findByCategoryId(UUID categoryId);
    Page<ProductGroup> findByNameContaining(String name, Pageable pageable);
}
