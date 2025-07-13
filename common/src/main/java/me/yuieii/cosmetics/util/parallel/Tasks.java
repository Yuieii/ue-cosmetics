package me.yuieii.cosmetics.util.parallel;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public enum Tasks {
    ;

    public static <R> CompletableFuture<ILoopResult<R>> whileDo(
        BooleanSupplier condition,
        Supplier<CompletableFuture<ILoopResult<R>>> runnable
    ) {
        if (!condition.getAsBoolean())
            return CompletableFuture.completedFuture(ILoopResult.normalExit());

        return runnable.get().thenCompose((result) -> {
            if (!result.canContinue()) {
                return CompletableFuture.completedFuture(result);
            }

            return Tasks.whileDo(condition, runnable);
        });
    }

    public static <R> CompletableFuture<ILoopResult<R>> doWhile(
        Supplier<CompletableFuture<ILoopResult<R>>> runnable,
        BooleanSupplier condition
    ) {
        return runnable.get().thenCompose((result) -> {
            if (!result.canContinue()) {
                return CompletableFuture.completedFuture(result);
            }

            if (!condition.getAsBoolean())
                return CompletableFuture.completedFuture(ILoopResult.normalExit());

            return Tasks.doWhile(runnable, condition);
        });
    }

    public static CompletableFuture<Void> delay(Duration duration) {
        final CompletableFuture<Void> future = new CompletableFuture<>();
        final Thread delayThread = new Thread(() -> {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException ex) {
                // ...
            }

            future.complete(null);
        });

        delayThread.start();
        return future;
    }

    public static CompletableFuture<Void> delay(long millis) {
        final CompletableFuture<Void> future = new CompletableFuture<>();
        if (millis == -1) {
            // Delays indefinitely. Return the future immediately so it will not eventually complete.
            return future;
        }

        final Thread delayThread = new Thread(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ex) {
                // ...
            }

            future.complete(null);
        });

        delayThread.start();
        return future;
    }
}
