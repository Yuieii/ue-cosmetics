// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.client;

import io.github.yuieii.cosmetics.util.SimpleTextureStore;

public final class ModClientImpl implements IModClient {
    private final SimpleTextureStore textureStore = new SimpleTextureStore();

    @Override
    public SimpleTextureStore getTextureStore() {
        return this.textureStore;
    }
}
