package com.andersen.internetShop.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Product(String name, BigDecimal price, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(Integer id, String name, BigDecimal price, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
