// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.fabric.client;

import io.github.yuieii.cosmetics.fabriclike.client.UeCosmeticsFabricLikeClient;
import net.fabricmc.api.ClientModInitializer;

public final class UeCosmeticsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        UeCosmeticsFabricLikeClient.init();
    }
}
