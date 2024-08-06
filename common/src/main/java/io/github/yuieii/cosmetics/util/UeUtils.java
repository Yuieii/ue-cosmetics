// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;

import java.awt.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class UeUtils {
    private UeUtils() {
        // Utility class. Do not initialize.
    }

    public static Color colorFromHexInFormatABGR(int hex) {
        int a = (hex >> 24) & 0xff;
        int r = (hex >> 16) & 0xff;
        int g = (hex >> 8) & 0xff;
        int b = hex & 0xff;

        return new Color(r, g, b, a);
    }

    public static Color colorAtPixel(NativeImage image, int x, int y) {
        return UeUtils.colorFromHexInFormatABGR(image.getPixelRGBA(x, y));
    }

    public static <T> void with(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
    }

    public static <T> UeOperationContext<T> with(T obj) {
        return UeOperationContext.with(obj);
    }
}
