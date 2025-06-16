// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.client.extension;

import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public interface IRenderTypeExtension {
    Optional<ResourceLocation> uecosmetics$boundTexture();

    void uecosmetics$setBoundTexture(ResourceLocation location);
}
