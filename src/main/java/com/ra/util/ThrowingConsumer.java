package com.ra.util;

@FunctionalInterface
public interface ThrowingConsumer<T , E extends Exception> {

    void accept(T t) throws E;
}
