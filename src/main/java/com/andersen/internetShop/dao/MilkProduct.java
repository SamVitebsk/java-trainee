package com.andersen.internetShop.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MilkProduct extends Product implements Serializable {

    @Column(name = "category", columnDefinition = "varchar(255) default 'MILK'")
    private ProductCategory category;
}
