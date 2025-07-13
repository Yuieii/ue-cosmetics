package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public sealed interface IResult<T, E extends Throwable> {
    T unwrap() throws E;

    E unwrapError();

    default T expect(String message) {
        return this.ok().orElseThrow(() -> new RuntimeException(message));
    }

    default E expectError(String message) {
        return this.error().orElseThrow(() -> new RuntimeException(message));
    }

    default Stream<T> stream() {
        return this.ok().stream();
    }

    Optional<T> ok();

    Optional<E> error();

    default boolean isOk() {
        return this instanceof Success<?, ?>;
    }

    @SuppressWarnings("OptionalIsPresent")
    default boolean isOkAnd(Predicate<T> predicate) {
        Optional<T> value = this.ok();
        if (value.isEmpty()) return false;
        return predicate.test(value.get());
    }

    default boolean isError() {
        return this instanceof Error<?, ?>;
    }

    @SuppressWarnings("OptionalIsPresent")
    default boolean isErrorAnd(Predicate<E> predicate) {
        Optional<E> value = this.error();
        if (value.isEmpty()) return false;
        return predicate.test(value.get());
    }

    <R> IResult<R, E> map(Function<T, R> mapper);

    <R extends Throwable> IResult<T, R> mapError(Function<E, R> mapper);

    default void inspect(Consumer<T> inspector) {
        this.ok().ifPresent(inspector);
    }

    default void inspectError(Consumer<E> inspector) {
        this.error().ifPresent(inspector);
    }

    // aka flatMap
    <R> IResult<R, E> andThen(Function<T, IResult<R, E>> mapper);

    // aka flatMapError
    <R extends Throwable> IResult<T, R> orElse(Function<E, IResult<T, R>> mapper);

    default <R> IResult<R, E> and(IResult<R, E> res) {
        return this.andThen(v -> res);
    }

    default <R extends Throwable> IResult<T, R> or(IResult<T, R> res) {
        return this.orElse(v -> res);
    }

    default T unwrapOr(T def) {
        return this.ok().orElse(def);
    }

    default T unwrapOrElse(Supplier<T> def) {
        return this.ok().orElseGet(def);
    }

    default <R> R match(Function<T, R> okMatch, Function<E, R> errMatch) {
        return this.ok().map(okMatch)
            .or(() -> this.error().map(errMatch))
            .orElseThrow();
    }

    // ===========================================================================================================

    static <T, E extends Throwable> IResult<T, E> ok(@NotNull T value) {
        return new Success<>(value);
    }

    static <T, E extends Throwable> IResult<T, E> error(@NotNull E throwable) {
        return new Error<>(throwable);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static <T, E extends Throwable> IResult<T, E> fromOptional(Optional<T> value, Supplier<E> thrower) {
        if (value.isPresent()) {
            return IResult.ok(value.get());
        }

        E throwable = thrower.get();
        return IResult.error(throwable);
    }

    // ===========================================================================================================

    static <T, E extends Throwable> IResult<T, E> flatten(IResult<IResult<T, E>, E> res) {
        return res.andThen(v -> v);
    }

    static <T, E extends Throwable> Optional<IResult<T, E>> transpose(IResult<Optional<T>, E> res) {
        return res.error().map(IResult::<T, E>error)
            .or(() -> res.ok().flatMap(o -> o).map(IResult::<T, E>ok));
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static <T, E extends Throwable> IResult<Optional<T>, E> transpose(Optional<IResult<T, E>> res) {
        return res.map(r -> r.map(Optional::of))
            .orElseGet(() -> IResult.ok(Optional.empty()));
    }

    static <T> T intoOk(IResult<T, Never> res) {
        return ((Success<T, Never>) res).value();
    }

    static <E extends Throwable> E intoError(IResult<Never, E> res) {
        return ((Error<Never, E>) res).throwable();
    }

    // ===========================================================================================================

    record Success<T, E extends Throwable>(@NotNull T value) implements IResult<T, E> {
        @Override
        @Contract("-> !null")
        public T unwrap() {
            return this.value();
        }

        @Override
        @Contract("-> fail")
        public E unwrapError() {
            throw new IllegalStateException("Called unwrapErr() on Success");
        }

        @Override
        public Optional<T> ok() {
            return Optional.of(this.value());
        }

        @Override
        public Optional<E> error() {
            return Optional.empty();
        }

        @Override
        public <R> IResult<R, E> map(Function<T, R> mapper) {
            return IResult.ok(mapper.apply(this.value()));
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R extends Throwable> IResult<T, R> mapError(Function<E, R> mapper) {
            return (IResult<T, R>) this;
        }

        @Override
        public <R> IResult<R, E> andThen(Function<T, IResult<R, E>> mapper) {
            return mapper.apply(this.value());
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R extends Throwable> IResult<T, R> orElse(Function<E, IResult<T, R>> mapper) {
            return (IResult<T, R>) this;
        }
    }

    record Error<T, E extends Throwable>(E throwable) implements IResult<T, E> {
        @Override
        @Contract("-> fail")
        public T unwrap() throws E {
            throw this.throwable();
        }

        @Override
        @Contract("-> !null")
        public E unwrapError() {
            return this.throwable();
        }

        @Override
        public Optional<T> ok() {
            return Optional.empty();
        }

        @Override
        public Optional<E> error() {
            return Optional.of(this.throwable());
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R> IResult<R, E> map(Function<T, R> mapper) {
            return (IResult<R, E>) this;
        }

        @Override
        public <R extends Throwable> IResult<T, R> mapError(Function<E, R> mapper) {
            return IResult.error(mapper.apply(this.throwable()));
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R> IResult<R, E> andThen(Function<T, IResult<R, E>> mapper) {
            return (IResult<R, E>) this;
        }

        @Override
        public <R extends Throwable> IResult<T, R> orElse(Function<E, IResult<T, R>> mapper) {
            return mapper.apply(this.throwable());
        }
    }
}
