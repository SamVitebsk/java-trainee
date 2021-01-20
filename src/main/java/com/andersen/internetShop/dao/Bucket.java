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
public class Bucket extends IdentifiableObject {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private final Map<Product, Integer> products = new LinkedHashMap<>();

    public Bucket(Integer id, User user) {
        this.setId(id);
        this.user = user;
    }
}


