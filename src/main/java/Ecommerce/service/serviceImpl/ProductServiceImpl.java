package Ecommerce.service.serviceImpl;

import Ecommerce.dto.ProductGroup;
import Ecommerce.entity.Product;
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

    @Override
    public Product createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setCategory(product.getCategory());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setMen(product.isMen());
        newProduct.setPrice(product.getPrice());
        newProduct.setSize(product.getSize());
        newProduct.setColor(product.getColor());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setCreateDate(new Date());
        newProduct.setUpdateDate(new Date());
        return newProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        Product retrievedProduct = productRepo.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        retrievedProduct.setCategory(product.getCategory());
        retrievedProduct.setName(product.getName());
        retrievedProduct.setDescription(product.getDescription());
        retrievedProduct.setMen(product.isMen());
        retrievedProduct.setPrice(product.getPrice());
        retrievedProduct.setSize(product.getSize());
        retrievedProduct.setColor(product.getColor());
        retrievedProduct.setQuantity(product.getQuantity());
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
    public List<ProductGroup> getProductsByCategory(UUID category) {
        List<Product> products = productRepo.findByCategoryId(category);
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
