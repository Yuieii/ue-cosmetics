// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.util;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class OptionalBoolean {
    private static final OptionalBoolean EMPTY = new OptionalBoolean();
    private static final OptionalBoolean TRUE = new OptionalBoolean();
    private static final OptionalBoolean FALSE = new OptionalBoolean();

    private OptionalBoolean() {

    }

    public static OptionalBoolean empty() {
        return EMPTY;
    }

    public static OptionalBoolean trueValue() {
        return TRUE;
    }

    public static OptionalBoolean falseValue() {
        return FALSE;
    }

    public static OptionalBoolean of(boolean value) {
        return value ? TRUE : FALSE;
    }

    public static OptionalBoolean ofNullable(@Nullable Boolean value) {
        if (value == null) return EMPTY;
        return OptionalBoolean.of(value);
    }

    public static OptionalBoolean fromTriState(TriState triState) {
        return OptionalBoolean.ofNullable(triState.toBoxedBoolean());
    }

    public boolean isPresent() {
        return this != EMPTY;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isTrue() {
        return this == TRUE;
    }

    public boolean isFalse() {
        return this == FALSE;
    }

    public boolean get() {
        if (this.isEmpty()) throw new IllegalStateException("Value not present");
        return this == TRUE;
    }

    @Nullable
    public Boolean getBoxed() {
        return this.isEmpty() ? null : this == TRUE;
    }

    public <T> Optional<T> mapToObject(BooleanToObjectFunction<T> function) {
        if (this.isEmpty()) return Optional.empty();
        return Optional.ofNullable(function.apply(this.get()));
    }

    public OptionalBoolean flatMap(BooleanToObjectFunction<OptionalBoolean> function) {
        if (this.isEmpty()) return this;

        OptionalBoolean result = function.apply(this.get());
        if (result == null) {
            throw new IllegalStateException("OptionalBoolean .flatMap() result cannot be null.");
        }

        return result;
    }

    public OptionalBoolean or(Supplier<OptionalBoolean> supplier) {
        return this.isPresent() ? this : supplier.get();
    }

    public boolean orElse(boolean other) {
        return this.isPresent() ? this.get() : other;
    }

    public boolean orElseGet(BooleanSupplier supplier) {
        return this.isPresent() ? this.get() : supplier.getAsBoolean();
    }

    public TriState toTriState() {
        return this.mapToObject(TriState::fromBoolean).orElse(TriState.UNSET);
    }

    @FunctionalInterface
    public interface BooleanToObjectFunction<T> {
        T apply(boolean value);
    }
}
