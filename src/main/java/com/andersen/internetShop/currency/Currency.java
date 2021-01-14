package com.andersen.internetShop.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Currency {
    protected String name;
    protected Double course;
    protected CurrencyCode code;
    protected Double multiplicity;
}

