package com.calliefox.jnca;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, V> {

    void apply(T t, U u, V v);
}