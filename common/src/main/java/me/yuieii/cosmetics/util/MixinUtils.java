// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public final class MixinUtils {
    private MixinUtils() {

    }

    /**
     * A convenient method for referencing {@code this} in Mixin classes.
     *
     * <p>Example usage:
     * <pre>
     *     // In a Mixin class
     *     PlayerRenderer self = MixinUtils.castFrom(this);
     *
     *     // It's same as the following statement but without data flow warnings
     *     // generated by IDEs
     *     PlayerRenderer self = (PlayerRenderer) (Object) this;
     *
     *     // Type can be automatically inferred from the declared variable type
     *     PlayerModel&lt;Piglin&gt; self = MixinUtils.castFrom(this);
     * </pre>
     *
     * @param target the object to be interpreted
     * @return the same object but of {@code T} type
     * @param <T> the returned type
     */
    @SuppressWarnings("unchecked")
    @Contract("null -> null; !null -> !null")
    public static <T> T castFrom(Object target) {
        return (T) target;
    }

    @Contract("null,_ -> null; !null,_ -> !null")
    public static <T> T castFrom(Object target, @NotNull Class<T> cls) {
        return cls.cast(target);
    }

    public static <T> Optional<T> tryCastFrom(Object target) {
        try {
            return Optional.ofNullable(MixinUtils.castFrom(target));
        } catch (ClassCastException ex) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> tryCastFrom(Object target, @NotNull Class<T> cls) {
        try {
            return Optional.ofNullable(MixinUtils.castFrom(target, cls));
        } catch (ClassCastException ex) {
            return Optional.empty();
        }
    }

    public static <T> void castThen(Object target, @NotNull Consumer<T> consumer) {
        if (target == null) {
            return;
        }

        consumer.accept(MixinUtils.castFrom(target));
    }

    public static <T> void castThen(Object target, @NotNull Class<T> cls, @NotNull Consumer<T> consumer) {
        if (target == null) {
            return;
        }

        consumer.accept(MixinUtils.castFrom(target));
    }

    @Contract("null,_ -> null; !null,_ -> !null")
    public static <T, R> R castThenReturn(Object target, @NotNull Function<T, R> function) {
        if (target == null) {
            return null;
        }

        return function.apply(MixinUtils.castFrom(target));
    }

    @Contract("null,_,_ -> null; !null,_,_ -> !null")
    public static <T, R> R castThenReturn(Object target, @NotNull Class<T> cls, @NotNull Function<T, R> function) {
        if (target == null) {
            return null;
        }

        return function.apply(MixinUtils.castFrom(target));
    }
}
