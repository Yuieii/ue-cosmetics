// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class Cached<T> extends Lazy<T> {
    @Contract(value = "null -> fail", pure = true)
    public Cached(Supplier<T> initializer) {
        super(initializer);
    }

    public void invalidate() {
        this.initialized = false;
        this.value = null;
    }
}
