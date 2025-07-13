// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;

import java.util.function.Supplier;

public final class Cached<T> implements Supplier<T> {
    private final Supplier<T> initializer;
    private Lazy<T> lazy;

    @Contract(value = "null -> fail", pure = true)
    public Cached(Supplier<T> initializer) {
        this.initializer = initializer;
        this.constructLazy();
    }

    private void constructLazy() {
        this.lazy = new Lazy<>(this.initializer);
    }

    public void invalidate() {
        this.constructLazy();
    }

    @Override
    public T get() {
        return this.lazy.get();
    }

    public boolean isValueCreated() {
        return this.lazy.isValueCreated();
    }
}
