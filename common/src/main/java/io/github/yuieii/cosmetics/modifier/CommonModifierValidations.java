// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import io.github.yuieii.cosmetics.util.UeUtils;

import java.awt.*;
import java.util.function.Supplier;

public final class CommonModifierValidations {
    private CommonModifierValidations() {
        // Utility class.
    }

    private static boolean validateThreeColorsAtTopRight(NativeImage bitmap, Supplier<Color> c1, Supplier<Color> c2, Supplier<Color> c3) {
        if (!UeUtils.colorAtPixel(bitmap, 63, 0).equals(c1.get())) return false;
        if (!UeUtils.colorAtPixel(bitmap, 62, 0).equals(c2.get())) return false;
        return UeUtils.colorAtPixel(bitmap, 61, 0).equals(c3.get());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateTrafficSignalPalette(NativeImage bitmap) {
        return CommonModifierValidations.validateThreeColorsAtTopRight(bitmap,
                TrafficSignalPalette::green,
                TrafficSignalPalette::yellow,
                TrafficSignalPalette::red);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateEarthPalette(NativeImage bitmap) {
        return CommonModifierValidations.validateThreeColorsAtTopRight(bitmap,
                EarthPalette::darker,
                EarthPalette::medium,
                EarthPalette::lighter);
    }

    private static final class TrafficSignalPalette {
        public static final Color GREEN = new Color(0x3acb28);
        public static final Color YELLOW = new Color(0xf9ca8b);
        public static final Color RED = new Color(0xff859b);

        public static Color green() {
            return GREEN;
        }

        public static Color yellow() {
            return YELLOW;
        }

        public static Color red() {
            return RED;
        }
    }

    private static final class EarthPalette {
        public static final Color DARKER = new Color(0x51280c);
        public static final Color MEDIUM = new Color(0xc5a068);
        public static final Color LIGHTER = new Color(0xd8c5a1);

        public static Color darker() {
            return DARKER;
        }

        public static Color medium() {
            return MEDIUM;
        }

        public static Color lighter() {
            return LIGHTER;
        }
    }
}
