// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class OptionalBoolean {
    // OptionalBoolean has only 3 possible values, which are both boolean values (true and false) and an empty value.
    // Cache these value and make this 3 instances only.
    private static final OptionalBoolean EMPTY = new OptionalBoolean();
    private static final OptionalBoolean TRUE = new OptionalBoolean();
    private static final OptionalBoolean FALSE = new OptionalBoolean();

    private OptionalBoolean() {

    }

    /**
     * Returns an empty {@link OptionalBoolean} instance. No value is present for this {@link OptionalBoolean}.
     * @return an empty {@link OptionalBoolean}.
     */
    public static OptionalBoolean empty() {
        return EMPTY;
    }

    /**
     * Returns an {@link OptionalBoolean} indicating {@code true}.
     * @return an {@link OptionalBoolean} indicating {@code true}
     */
    public static OptionalBoolean trueValue() {
        return TRUE;
    }

    /**
     * Returns an {@link OptionalBoolean} indicating {@code false}.
     * @return an {@link OptionalBoolean} indicating {@code false}
     */
    public static OptionalBoolean falseValue() {
        return FALSE;
    }

    /**
     * Returns an {@link OptionalBoolean} indicating the given boolean value.
     * @param value the value to describe
     * @return an {@link OptionalBoolean} with the value present
     */
    public static OptionalBoolean of(boolean value) {
        return value ? TRUE : FALSE;
    }

    /**
     * Returns an {@link OptionalBoolean} indicating the given boolean value, if non-null, otherwise an empty {@link OptionalBoolean}.
     * @param value the possibly-{@code null} value to describe
     * @return an {@link OptionalBoolean} with the value present if the specified value is non-null, otherwise an empty {@link OptionalBoolean}
     */
    public static OptionalBoolean ofNullable(Boolean value) {
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

    /**
     * If a value is present, returns the value, otherwise throws {@link NoSuchElementException}.
     * @return the non-{@code null} value described by this {@link OptionalBoolean}
     * @throws NoSuchElementException if no value is present
     */
    public boolean get() {
        if (this.isEmpty()) throw new NoSuchElementException("Value not present");
        return this == TRUE;
    }

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
