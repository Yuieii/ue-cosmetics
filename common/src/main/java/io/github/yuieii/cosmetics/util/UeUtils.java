package io.github.yuieii.cosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;

import java.awt.*;

public final class UeUtils {
    private UeUtils() {

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
}
