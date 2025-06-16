// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.client.extension;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.modifier.ISkinSensitiveModifier;
import me.yuieii.cosmetics.modifier.SkinSensitiveModifierData;

public interface IReloadableTextureExtension {
    SkinSensitiveModifierData uecosmetics$getData(ISkinSensitiveModifier modifier);
    void uecosmetics$loadForPotentialSkin(NativeImage image);
}
