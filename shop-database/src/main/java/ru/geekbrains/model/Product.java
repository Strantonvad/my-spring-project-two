package ru.geekbrains.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, unique = true, nullable = false)
    private String title;

    @Column(length = 22, nullable = false)
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public Product(Long id, String title, BigDecimal cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
