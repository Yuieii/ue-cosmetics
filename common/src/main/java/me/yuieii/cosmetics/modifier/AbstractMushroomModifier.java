package me.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;

import java.awt.*;

public abstract class AbstractMushroomModifier extends AbstractBlockStateOnHeadModifier {

    public static final Color RED_MUSHROOM_COLOR = new Color(0xca6262);
    public static final Color BROWN_MUSHROOM_COLOR = new Color(0xe5ae8f);

    @Override
    public boolean isSkinApplicable(NativeImage texture) {
        return AbstractMushroomModifier.validateYuieiiMushroomPattern(texture, false, this.getPrimaryMushroomColor());
    }

    public abstract Color getPrimaryMushroomColor();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateYuieiiMushroomPattern(NativeImage bitmap, boolean checkBackground, Color primaryColor) {
        // For this we want an exact same mushroom pattern placed at the most top left.
        // @formatter:off
        int[] redPos = {
                3, 4, 4, 4,
                2, 5,       4, 5, 5, 5,
                2, 6, 3, 6, 4, 6, 5, 6
        };

        int[] whitePos = {
                3, 5
        };

        int[] stemPos = {
                3, 7, 4, 7
        };
        // @formatter:on

        if (!ISkinSensitiveModifier.colorsAllMatch(redPos, bitmap, primaryColor)) return false;
        if (!ISkinSensitiveModifier.colorsAllMatch(whitePos, bitmap, new Color(0xffffff))) return false;
        if (!ISkinSensitiveModifier.colorsAllMatch(stemPos, bitmap, new Color(0xffd4bb))) return false;

        if (!checkBackground) return true;

        // @formatter:off
        int[] backPos = {
                0, 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0,
                0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1,
                0, 2, 1, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6, 2, 7, 2,
                0, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3, 6, 3, 7, 3,
                0, 4, 1, 4, 2, 4,             5, 4, 6, 4, 7, 4,
                0, 5, 1, 5,                         6, 5, 7, 5,
                0, 6, 1, 6,                         6, 6, 7, 6,
                0, 7, 1, 7, 2, 7,             5, 7, 6, 7, 7, 7,
        };
        // @formatter:on

        return ISkinSensitiveModifier.allTransparent(backPos, bitmap);
    }
}
