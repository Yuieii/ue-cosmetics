// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import java.util.function.Supplier;

public final class Cached<T> extends Lazy<T> {
    public Cached(Supplier<T> initializer) {
        super(initializer);
    }

    public void invalidate() {
        this.initialized = false;
        this.value = null;
    }
}
