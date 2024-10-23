// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.util.ARGB;

import java.awt.*;
import java.util.function.Consumer;

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
        // return UeUtils.colorFromHexInFormatABGR(image.getPixelRGBA(x, y));
        int argb = image.getPixel(x, y);
        return new Color(ARGB.red(argb), ARGB.green(argb), ARGB.blue(argb), ARGB.alpha(argb));
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
