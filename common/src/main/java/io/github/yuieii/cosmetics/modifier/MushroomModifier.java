// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import io.github.yuieii.cosmetics.util.OptionalBoolean;
import net.minecraft.client.player.AbstractClientPlayer;

import java.awt.*;

public class MushroomModifier extends Modifier implements ISkinSensitiveModifier {
    @Override
    public boolean isApplicable(AbstractClientPlayer player) {
        return ISkinSensitiveModifier.super.isApplicable(player);
    }

    @Override
    public OptionalBoolean isPlayerApplicable(AbstractClientPlayer player) {
        return ISkinSensitiveModifier.super.isPlayerApplicable(player);
    }

    @Override
    public boolean isSkinApplicable(NativeImage texture) {
        return MushroomModifier.validateYuieiiMushroomPattern(texture, false);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateYuieiiMushroomPattern(NativeImage bitmap, boolean checkBackground) {
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

        if (!ISkinSensitiveModifier.colorsAllMatch(redPos, bitmap, new Color(0xca6262))) return false;
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
