package me.yuieii.cosmetics.client;

import net.fabricmc.api.ClientModInitializer;

public final class UeCosmeticsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        UeCosmeticsClient.init();
    }
}