// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {
    protected boolean initialized;
    protected T value;

    private final Supplier<T> initializer;

    @Contract(value = "null -> fail", pure = true)
    public Lazy(Supplier<T> initializer) {
        if (initializer == null) {
            throw new IllegalArgumentException("initializer must not be null");
        }

        this.initializer = initializer;
    }

    @Override
    @Nullable
    public T get() {
        if (this.initialized) {
            return this.value;
        }

        this.value = this.initializer.get();
        this.initialized = true;

        return this.value;
    }
}
