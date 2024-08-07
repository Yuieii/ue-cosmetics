// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import io.github.yuieii.cosmetics.client.UeCosmeticsClient;
import io.github.yuieii.cosmetics.util.OptionalBoolean;
import io.github.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Predicate;

public interface ISkinSensitiveModifier {
    default boolean isApplicable(AbstractClientPlayer player) {
        OptionalBoolean preCheck = this.isPlayerApplicable(player);
        if (preCheck.isPresent()) {
            return preCheck.get();
        }

        PlayerSkin defaultSkin = DefaultPlayerSkin.get(player.getGameProfile());
        if (player.getSkin().equals(defaultSkin) && !this.isDefaultSkinApplicable()) {
            return false;
        }

        ResourceLocation skinLocation = player.getSkin().texture();
        TextureManager manager = Minecraft.getInstance().getTextureManager();
        AbstractTexture texture = manager.getTexture(skinLocation);
        if (!(texture instanceof SimpleTexture simple)) {
            return false;
        }

        NativeImage image = UeCosmeticsClient.getInstance().getTextureStore().get(simple);
        return this.isSkinApplicable(image);
    }

    default boolean isDefaultSkinApplicable() {
        return false;
    }

    default OptionalBoolean isPlayerApplicable(AbstractClientPlayer player) {
        return OptionalBoolean.empty();
    }

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
