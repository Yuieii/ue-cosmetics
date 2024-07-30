// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.neoforge;

import net.neoforged.fml.common.Mod;

import io.github.yuieii.cosmetics.UeCosmetics;

@Mod(UeCosmetics.MOD_ID)
public final class UeCosmeticsNeoForge {
    public UeCosmeticsNeoForge() {
        // Run our common setup.
        UeCosmetics.init();
    }
}
