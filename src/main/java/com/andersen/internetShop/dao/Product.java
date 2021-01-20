package com.andersen.internetShop.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Product extends NamedObject implements Serializable {

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Product(String name, BigDecimal price, ProductCategory category) {
        this.setName(name);
        this.price = price;
//        this.category = category;
    }

    public Product(String name, BigDecimal price) {
        this.setName(name);
        this.price = price;
    }

    public Product(Integer id, String name, BigDecimal price, ProductCategory category) {
        this(name, price, category);
        this.setId(id);
    }
}
