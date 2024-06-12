package Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String description;
    private boolean isMen;
    private double price;
    private String size;
    private String color;
    private int quantity;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;
}
