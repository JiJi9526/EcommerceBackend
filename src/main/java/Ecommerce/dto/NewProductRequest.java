package Ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewProductRequest {
    private String name;
    private String description;
    private boolean isMen;
    private double price;
    private String size;
    private String color;
    private int quantity;
}
