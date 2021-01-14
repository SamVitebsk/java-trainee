package com.andersen.internetShop.currency;

public class CurrencyFactory {
    public static Currency getCurrency(CurrencyCode code) {
        switch (code) {
            case BYN:
                return new Currency("Belarusian ruble", 1.0, CurrencyCode.BYN, 1.0);
            case UAH:
                return new Currency("Hryvnia", 11.0, CurrencyCode.UAH, 20.0);
            case USD:
                return new Currency("US Dollar", 0.4, CurrencyCode.USD, 30.0);
            default:
                throw new RuntimeException("Currency not found");
        }
    }

    public static CurrencyCode convertCurrencyIndexToCurrencyCode(Integer index) {
        switch (index) {
            case 1:
                return CurrencyCode.BYN;
            case 2:
                return CurrencyCode.UAH;
            case 3:
                return CurrencyCode.USD;
            default:
                return null;
        }
    }
}