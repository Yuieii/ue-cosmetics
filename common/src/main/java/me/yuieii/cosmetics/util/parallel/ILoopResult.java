package me.yuieii.cosmetics.util.parallel;

public sealed interface ILoopResult<T> {
    static <T> Continue<T> doContinue() {
        return new Continue<>();
    }

    static <T> Return<T> doReturn(T value) {
        return new Return<>(value);
    }

    static <T> Break<T> doBreak() {
        return new Break<>();
    }

    static <T> NormalExit<T> normalExit() {
        return new NormalExit<>();
    }

    default boolean canContinue() {
        return this instanceof Continue<T> || this instanceof Return<T>;
    }

    final class NormalExit<T> implements ILoopResult<T> {

    }

    final class Continue<T> implements ILoopResult<T> {

    }

    final class Break<T> implements ILoopResult<T> {

    }

    final class Return<T> implements ILoopResult<T> {
        private final T value;

        public Return(T value) {
            this.value = value;
        }

        public T value() {
            return value;
        }
    }
}
