// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.UeCosmetics;
import me.yuieii.cosmetics.mixin.client.INativeImageAccessor;

import java.awt.*;
import java.util.function.Consumer;

public final class UeUtils {
    private UeUtils() {
        // Utility class. Do not initialize.
    }

    public static Color colorFromHexInFormatABGR(int hex) {
        int a = (hex >> 24) & 0xff;
        int b = (hex >> 16) & 0xff;
        int g = (hex >> 8) & 0xff;
        int r = hex & 0xff;

        return new Color(r, g, b, a);
    }

    public static Color colorAtPixel(NativeImage image, int x, int y) {
        if (MixinUtils.castFrom(image, INativeImageAccessor.class).uecosmetics$getPixels() == 0) {
            // It is an error to read pixels after the texture is closed.
            // We only read the data right before the texture is uploaded to GPU!
            throw new IllegalStateException(
                    "The image has been deallocated. " +
                            "Make sure you only access the texture before it is automatically closed."
            );
        }

        return UeUtils.colorFromHexInFormatABGR(image.getPixelRGBA(x, y));
    }

    public static <T> T with(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
        return obj;
    }

    public static <T> UeOperationContext<T> with(T obj) {
        return UeOperationContext.with(obj);
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";

        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
