// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;

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
     * </p>
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
    public static <T> T castFrom(Object target, Class<T> cls) {
        return cls.cast(target);
    }

    public static <T> Optional<T> tryCastFrom(Object target) {
        try {
            return Optional.of(MixinUtils.castFrom(target));
        } catch (ClassCastException ex) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> tryCastFrom(Object target, Class<T> cls) {
        try {
            return Optional.of(MixinUtils.castFrom(target, cls));
        } catch (ClassCastException ex) {
            return Optional.empty();
        }
    }

    public static <T> void castThen(Object target, Consumer<T> consumer) {
        consumer.accept(MixinUtils.castFrom(target));
    }

    public static <T> void castThen(Object target, Class<T> cls, Consumer<T> consumer) {
        consumer.accept(MixinUtils.castFrom(target));
    }

    public static <T, R> R castThenReturn(Object target,Function<T, R> function) {
        return function.apply(MixinUtils.castFrom(target));
    }

    public static <T, R> R castThenReturn(Object target, Class<T> cls, Function<T, R> function) {
        return function.apply(MixinUtils.castFrom(target));
    }
}
