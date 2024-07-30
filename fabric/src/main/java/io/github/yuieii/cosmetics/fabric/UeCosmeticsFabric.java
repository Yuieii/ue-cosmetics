// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.fabric;

import net.fabricmc.api.ModInitializer;

import io.github.yuieii.cosmetics.fabriclike.UeCosmeticsFabricLike;

public final class UeCosmeticsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run the Fabric-like setup.
        UeCosmeticsFabricLike.init();
    }
}
