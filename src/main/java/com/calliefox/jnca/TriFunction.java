package com.calliefox.jnca;

@FunctionalInterface
public interface TriFunction<T, U, V> {

    void apply(T t, U u, V v);
}