package Ecommerce.dto;

import Ecommerce.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NewProductRequest {
    private UUID id;
    private UUID categoryId;
    private String name;
    private String description;
    private Boolean isMen;
    private double price;
    private Product.ProductSize size;
    private Product.ProductColor color;
    private String imageUrl;
}
