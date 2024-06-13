package Ecommerce.service.serviceImpl;

import Ecommerce.dto.NewProductRequest;
import Ecommerce.dto.ProductGroup;
import Ecommerce.entity.Category;
import Ecommerce.entity.Product;
import Ecommerce.repo.CategoryRepo;
import Ecommerce.repo.ProductRepo;
import Ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;

    @Override
    public Product createProduct(NewProductRequest newProductRequest) {
        Product newProduct = new Product();
        Category category = categoryRepo.findById(newProductRequest.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        newProduct.setCategory(category);
        newProduct.setName(newProductRequest.getName());
        newProduct.setDescription(newProductRequest.getDescription());
        newProduct.setIsMen(newProductRequest.getIsMen());
        newProduct.setPrice(newProductRequest.getPrice());
        newProduct.setSize(newProductRequest.getSize());
        newProduct.setColor(newProductRequest.getColor());
        newProduct.setImageUrl(newProductRequest.getImageUrl());
        newProduct.setCreateDate(new Date());
        newProduct.setUpdateDate(new Date());
        return productRepo.save(newProduct);
    }

    @Override
    public Product updateProduct(NewProductRequest newProductRequest, UUID id) {
        Product retrievedProduct = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepo.findById(newProductRequest.getCategoryId()).orElseThrow(null);
        retrievedProduct.setCategory(newProductRequest.getCategoryId() != null ? category : retrievedProduct.getCategory());
        retrievedProduct.setName(newProductRequest.getName() != null ? newProductRequest.getName() : retrievedProduct.getName());
        retrievedProduct.setDescription(newProductRequest.getDescription() != null ? newProductRequest.getDescription() : retrievedProduct.getDescription());
        retrievedProduct.setIsMen(newProductRequest.getIsMen() != null ? newProductRequest.getIsMen() : retrievedProduct.getIsMen());
        retrievedProduct.setPrice(newProductRequest.getPrice() != 0 ? newProductRequest.getPrice() : retrievedProduct.getPrice());
        retrievedProduct.setSize(newProductRequest.getSize() != null ? newProductRequest.getSize() : retrievedProduct.getSize());
        retrievedProduct.setColor(newProductRequest.getColor() != null ? newProductRequest.getColor() : retrievedProduct.getColor());
        retrievedProduct.setImageUrl(newProductRequest.getImageUrl() != null ? newProductRequest.getImageUrl() : retrievedProduct.getImageUrl());
        retrievedProduct.setUpdateDate(new Date());

        return productRepo.save(retrievedProduct);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }

    @Override
    public List<ProductGroup> getAllProducts() {
        List<Product> products = productRepo.findAll();
        Map<String, List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(p -> p.getName() + p.getName()));
        List<ProductGroup> productGroups = new ArrayList<>();
        for (Map.Entry<String, List<Product>> entry : groupedProducts.entrySet()) {
            ProductGroup productGroup = new ProductGroup();
            productGroup.setProduct(entry.getValue().get(0));
            productGroup.setQuantity(entry.getValue().size());
            productGroups.add(productGroup);
        }
        return productGroups;
    }

    @Override
    public ProductGroup getOneProduct(UUID id) {
        Product product=productRepo.findProductById(id);
        return modelMapper.map(product, ProductGroup.class);
    }

    @Override
    public List<ProductGroup> getProductsByCategory(UUID categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        Map<String, List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(p -> p.getName() + p.getName()));
        List<ProductGroup> productGroups = new ArrayList<>();
        for (Map.Entry<String, List<Product>> entry : groupedProducts.entrySet()) {
            ProductGroup productGroup = new ProductGroup();
            productGroup.setProduct(entry.getValue().get(0));
            productGroup.setQuantity(entry.getValue().size());
            productGroups.add(productGroup);
        }
        return productGroups;
    }

    @Override
    public Page<ProductGroup> searchProducts(String name, Pageable pageable) {
        Pageable sortedByName = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));

        Page<ProductGroup> products = productRepo.findByNameContaining(name, sortedByName);

        return products.map(product -> {
            ProductGroup productGroup = new ProductGroup();
            productGroup.setProduct(modelMapper.map(product, Product.class));
            productGroup.setQuantity(1);
            return productGroup;
        });
    }
}
