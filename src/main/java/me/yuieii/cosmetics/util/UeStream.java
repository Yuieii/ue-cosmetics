// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public class UeStream<T> implements Stream<T> {
    private final Stream<T> stream;

    private UeStream(Stream<T> stream) {
        this.stream = stream;
    }

    public static <T> UeStream<T> stream(Stream<T> stream) {
        return new UeStream<>(stream);
    }

    public <O> UeStream<O> instanceOf(Class<O> clz) {
        return new UeStream<>(this.stream.filter(c -> clz.isAssignableFrom(c.getClass())).map(clz::cast));
    }

    @Override
    public UeStream<T> filter(Predicate<? super T> predicate) {
        return new UeStream<>(this.stream.filter(predicate));
    }

    @Override
    public <R> UeStream<R> map(Function<? super T, ? extends R> mapper) {
        return new UeStream<>(this.stream.map(mapper));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return this.stream.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return this.stream.mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return this.stream.mapToDouble(mapper);
    }

    @Override
    public <R> UeStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new UeStream<>(this.stream.flatMap(mapper));
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return this.stream.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return this.stream.flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return this.stream.flatMapToDouble(mapper);
    }

    @Override
    public UeStream<T> distinct() {
        return new UeStream<>(this.stream.distinct());
    }

    @Override
    public UeStream<T> sorted() {
        return new UeStream<>(this.stream.sorted());
    }

    @Override
    public UeStream<T> sorted(Comparator<? super T> comparator) {
        return new UeStream<>(this.stream.sorted(comparator));
    }

    @Override
    public UeStream<T> peek(Consumer<? super T> action) {
        return new UeStream<>(this.stream.peek(action));
    }

    @Override
    public UeStream<T> limit(long maxSize) {
        return new UeStream<>(this.stream.limit(maxSize));
    }

    @Override
    public UeStream<T> skip(long n) {
        return new UeStream<>(this.stream.skip(n));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.stream.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        this.stream.forEachOrdered(action);
    }

    
    @Override
    public Object[] toArray() {
        return this.stream.toArray();
    }

    
    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return this.stream.toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return this.stream.reduce(identity, accumulator);
    }

    
    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return this.stream.reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return this.stream.reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return this.stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return this.stream.collect(collector);
    }

    
    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return this.stream.min(comparator);
    }

    
    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return this.stream.max(comparator);
    }

    @Override
    public long count() {
        return this.stream.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return this.stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return this.stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return this.stream.noneMatch(predicate);
    }

    
    @Override
    public Optional<T> findFirst() {
        return this.stream.findFirst();
    }

    
    @Override
    public Optional<T> findAny() {
        return this.stream.findAny();
    }

    
    @Override
    public Iterator<T> iterator() {
        return this.stream.iterator();
    }

    
    @Override
    public Spliterator<T> spliterator() {
        return this.stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return this.stream.isParallel();
    }

    
    @Override
    public UeStream<T> sequential() {
        return new UeStream<>(this.stream.sequential());
    }

    
    @Override
    public UeStream<T> parallel() {
        return new UeStream<>(this.stream.parallel());
    }

    
    @Override
    public UeStream<T> unordered() {
        return new UeStream<>(this.stream.unordered());
    }

    
    @Override
    public UeStream<T> onClose( Runnable closeHandler) {
        return new UeStream<>(this.stream.onClose(closeHandler));
    }

    @Override
    public void close() {
        this.stream.close();
    }
}
