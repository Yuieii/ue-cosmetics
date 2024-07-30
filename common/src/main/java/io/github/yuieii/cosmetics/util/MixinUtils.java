package io.github.yuieii.cosmetics.util;

public final class MixinUtils {
    private MixinUtils() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T castSelf(Object target) {
        return (T) target;
    }
}
