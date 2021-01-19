package com.andersen.internetShop.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class IdentifiableObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
