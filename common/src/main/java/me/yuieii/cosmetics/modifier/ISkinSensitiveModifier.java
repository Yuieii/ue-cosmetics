// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.extension.ISimpleTextureExtension;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.OptionalBoolean;
import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Predicate;

public interface ISkinSensitiveModifier {
    default boolean isApplicable(AbstractClientPlayer player) {
        OptionalBoolean preCheck = this.isPlayerApplicable(player);
        if (preCheck.isPresent()) {
            return preCheck.get();
        }

        ResourceLocation skinLocation = player.getSkinTextureLocation();
        TextureManager manager = Minecraft.getInstance().getTextureManager();
        AbstractTexture texture = manager.getTexture(skinLocation);
        if (!(texture instanceof SimpleTexture simple)) {
            return false;
        }

        SkinSensitiveModifierData data = MixinUtils.castFrom(simple, ISimpleTextureExtension.class)
                .uecosmetics$getData(this);
        return data.isApplicable();
    }

    default OptionalBoolean isPlayerApplicable(AbstractClientPlayer player) {
        return OptionalBoolean.empty();
    }

    /**
     * Determine if this skin texture is allowed to use this modifier.
     *
     * <p>
     *     Only called when the texture is about to be uploaded to the GPU, aka loading phase.
     *     <strong>This is not for usage at arbitrary timings.</strong>
     * </p>
     * @param texture The skin texture.
     * @return Whether the skin texture is allowed to use this modifier.
     */
    default boolean isSkinApplicable(NativeImage texture) {
        return false;
    }

    private static RuntimeException createIllegalPositionArrayArgumentException() {
        return new IllegalArgumentException("Invalid position array. Must have { x1, y1, x2, y2, ... } structure.");
    }

    private static boolean colorsAllMatch(int[] positions, NativeImage bitmap, Predicate<Color> predicate) {
        if (positions.length % 2 != 0) {
            throw ISkinSensitiveModifier.createIllegalPositionArrayArgumentException();
        }

        for (int i = 0; i < positions.length; i += 2) {
            int x = positions[i];
            int y = positions[i + 1];

            if (!predicate.test(UeUtils.colorAtPixel(bitmap, x, y))) return false;
        }

        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    static boolean colorsAllMatch(int[] positions, NativeImage bitmap, Color color) {
        // if (positions.length % 2 != 0) {
        //     throw SkinSensitiveModifier.createIllegalPositionArrayArgumentException();
        // }

        // for (int i = 0; i < positions.length; i += 2) {
        //     int x = positions[i];
        //     int y = positions[i + 1];

        //     if (!UeUtils.colorAtPixel(bitmap, x, y).equals(color)) return false;
        // }

        // return true;

        return ISkinSensitiveModifier.colorsAllMatch(positions, bitmap, c -> c.equals(color));
    }

    static boolean allTransparent(int[] positions, NativeImage bitmap) {
        // if (positions.length % 2 != 0) {
        //     throw SkinSensitiveModifier.createIllegalPositionArrayArgumentException();
        // }

        // for (int i = 0; i < positions.length; i += 2) {
        //     int x = positions[i];
        //     int y = positions[i + 1];

        //     if (UeUtils.colorAtPixel(bitmap, x, y).getAlpha() != 0) return false;
        // }

        // return true;
        return ISkinSensitiveModifier.colorsAllMatch(positions, bitmap, c -> c.getAlpha() == 0);
    }
}
