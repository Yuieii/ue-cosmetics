// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.client;

import me.yuieii.cosmetics.util.ClientModRegistries;
import me.yuieii.cosmetics.util.SimpleTextureStore;

public final class UeCosmeticsClient {
    private static IModClient INSTANCE = null;

    public static void init() {
        // Don't call UeCosmetics.init()!
        // That initializer should be called by underlying entrypoint automatically
        // Do client-side only initializations here
        UeCosmeticsClient.INSTANCE = new ModClientImpl();
        ClientModRegistries.init();
    }

    public static IModClient getInstance() {
        return UeCosmeticsClient.INSTANCE;
    }
}
