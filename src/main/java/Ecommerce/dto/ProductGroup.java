package Ecommerce.dto;

import Ecommerce.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGroup {
    private Product product;
    private int quantity;
}
