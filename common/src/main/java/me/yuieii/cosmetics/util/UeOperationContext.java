// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import java.util.function.Consumer;
import java.util.stream.Stream;

// Who likes DSL? This is for you.
public class UeOperationContext<T> {
    private final T target;

    private UeOperationContext(T target) {
        this.target = target;
    }

    public static <T> UeOperationContext<T> with(T target) {
        return new UeOperationContext<>(target);
    }

    public <O> UeOperationContext<T> ifInstanceOf(Class<O> clz, Consumer<O> action) {
        if (clz.isInstance(this.target)) {
            action.accept(clz.cast(this.target));
        }
        return this;
    }

    public <O> UeOperationContext<O> castTo(Class<O> clz) {
        return UeOperationContext.with(clz.cast(this.target));
    }
}
