package com.andersen.functionalInterface;

@FunctionalInterface
public interface Converter<T, S> {
    T convert(S source);
}
