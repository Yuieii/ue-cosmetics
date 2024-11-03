// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public enum TriState {
    UNSET,
    TRUE,
    FALSE;

    public boolean isTrue() {
        return this == TRUE;
    }

    public boolean isFalse() {
        return this == FALSE;
    }

    public boolean isUnset() {
        return this == UNSET;
    }

    public boolean isSet() {
        return this != UNSET;
    }

    public Boolean toBoxedBoolean() {
        switch (this) {
            case UNSET: return null;
            case TRUE: return Boolean.TRUE;
            case FALSE: return Boolean.FALSE;
            default: throw new IllegalStateException("Unsupported variant");
        }
    }

    public boolean toBoolean() {
        switch (this) {
            case UNSET: throw new IllegalStateException("The tri-state must be present to use .toBoolean()");
            case TRUE: return true;
            case FALSE: return false;
            default: throw new IllegalStateException("Unsupported variant");
        }
    }

    public static TriState fromBoxedBoolean(Boolean value) {
        if (value == null) return UNSET;
        return value ? TRUE : FALSE;
    }

    public static TriState fromBoolean(boolean value) {
        return value ? TRUE : FALSE;
    }

    public TriState ifTrue(Runnable runnable) {
        if (this.isTrue()) runnable.run();
        return this;
    }

    public TriState ifFalse(Runnable runnable) {
        if (this.isFalse()) runnable.run();
        return this;
    }

    public TriState ifSet(Runnable runnable) {
        if (this.isSet()) runnable.run();
        return this;
    }

    public TriState ifUnset(Runnable runnable) {
        if (this.isUnset()) runnable.run();
        return this;
    }

    public TriState ifSetOrElse(BooleanConsumer consumer, Runnable runnable) {
        if (this.isSet()) {
            consumer.consume(this.toBoolean());
        } else {
            runnable.run();
        }

        return this;
    }

    public boolean orElse(boolean other) {
        return this.isSet() ? this.toBoolean() : other;
    }

    public boolean orElseGet(BooleanSupplier supplier) {
        return this.isSet() ? this.toBoolean() : supplier.getAsBoolean();
    }

    public TriState or(Supplier<TriState> supplier) {
        if (this.isTrue()) return this;

        TriState other = supplier.get();
        if (other.isUnset()) return this;

        return other;
    }

    public TriState and(Supplier<TriState> supplier) {
        if (this.isFalse()) return this;

        TriState other = supplier.get();
        if (other.isUnset()) return this;

        return other;
    }

    @FunctionalInterface
    public interface BooleanConsumer {
        void consume(boolean value);
    }
}
