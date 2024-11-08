// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.client.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SimpleTexture;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleTextureStore {
    private final Map<SimpleTexture, NativeImage> imageMap = new ConcurrentHashMap<>();

    public @Nullable NativeImage get(SimpleTexture texture) {
        return imageMap.get(texture);
    }

    // Make sure to call this on texture creation, so we are able to access the NativeImage object.
    public void register(SimpleTexture texture, NativeImage image) {
        imageMap.put(texture, image);
    }

    // Make sure to call this on texture removal so our map doesn't become invalid.
    public void unregister(SimpleTexture texture) {
        imageMap.remove(texture);
    }
}
