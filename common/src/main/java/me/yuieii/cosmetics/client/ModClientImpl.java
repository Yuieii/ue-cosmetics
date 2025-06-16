// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.client;

import me.yuieii.cosmetics.client.util.ReloadableTextureStore;

public final class ModClientImpl implements IModClient {
    private final ReloadableTextureStore textureStore = new ReloadableTextureStore();

    @Override
    public ReloadableTextureStore getTextureStore() {
        return this.textureStore;
    }
}
