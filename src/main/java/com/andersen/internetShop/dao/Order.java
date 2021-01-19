package com.andersen.internetShop.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "accepted")
    private boolean accepted;
}
