// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.renderer.texture.NativeImage;

import java.awt.*;

public final class CommonModifierValidations {
    private CommonModifierValidations() {
        // Utility class.
    }

    private static boolean validateThreeColorsAtTopRight(NativeImage bitmap, Color c1, Color c2, Color c3) {
        if (!UeUtils.colorAtPixel(bitmap, 63, 0).equals(c1)) return false;
        if (!UeUtils.colorAtPixel(bitmap, 62, 0).equals(c2)) return false;
        return UeUtils.colorAtPixel(bitmap, 61, 0).equals(c3);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateTrafficSignalPalette(NativeImage bitmap) {
        return CommonModifierValidations.validateThreeColorsAtTopRight(bitmap,
                TrafficSignalPalette.GREEN,
                TrafficSignalPalette.YELLOW,
                TrafficSignalPalette.RED);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateEeveePalette(NativeImage bitmap) {
        return CommonModifierValidations.validateThreeColorsAtTopRight(bitmap,
                EeveePalette.DARKEST,
                EeveePalette.DARKER,
                EeveePalette.MEDIUM);
    }

    private static final class TrafficSignalPalette {
        public static final Color GREEN = new Color(0x3acb28);
        public static final Color YELLOW = new Color(0xf9ca8b);
        public static final Color RED = new Color(0xff859b);
    }

    private static final class EeveePalette {
        public static final Color DARKEST  = new Color(0x332011);
        public static final Color DARKER   = new Color(0x633c15);
        public static final Color MEDIUM   = new Color(0xc5915d);
        public static final Color LIGHTER  = new Color(0xefdbb6);
        public static final Color LIGHTEST = new Color(0xfcf3e4);
    }
}
