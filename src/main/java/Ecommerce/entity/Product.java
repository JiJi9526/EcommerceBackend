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
    private Boolean isMen;
    private double price;

    @Enumerated(EnumType.STRING)
    public ProductSize size;
    public enum ProductSize {
        XS, S, M, L, XL,
    }

    @Enumerated(EnumType.STRING)
    public ProductColor color;
    public enum ProductColor {
        Black, White, Grey, Blue,
    }

    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    private String imageUrl;
    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;
}
