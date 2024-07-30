// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import io.github.yuieii.cosmetics.fabriclike.UeCosmeticsFabricLike;

public final class UeCosmeticsQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Run the Fabric-like setup.
        UeCosmeticsFabricLike.init();
    }
}
