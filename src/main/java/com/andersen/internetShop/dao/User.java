package com.andersen.internetShop.dao;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String login;
    private String password;
}
