package com.andersen.internetShop.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@Entity
@Table(name = "bucket")
@Getter
@ToString
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private final Map<Product, Integer> products = new LinkedHashMap<>();

    public Bucket(Integer id, User user) {
        this.id = id;
        this.user = user;
    }
}


