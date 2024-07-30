// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.client;

import io.github.yuieii.cosmetics.util.ClientModRegistries;
import io.github.yuieii.cosmetics.util.SimpleTextureStore;

public final class UeCosmeticsClient {
    private static final SimpleTextureStore SIMPLE_TEXTURE_STORE = new SimpleTextureStore();

    public static void init() {
        // Don't call UeCosmetics.init()!
        // That initializer should be called by underlying entrypoint automatically
        // Do client-side only initializations here
        ClientModRegistries.init();
    }

    public static SimpleTextureStore getTextureStore() {
        return SIMPLE_TEXTURE_STORE;
    }
}
