// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.client.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.ReloadableTexture;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReloadableTextureStore {
    private final Map<ReloadableTexture, NativeImage> imageMap = new ConcurrentHashMap<>();

    public @Nullable NativeImage get(ReloadableTexture texture) {
        return imageMap.get(texture);
    }

    // Make sure to call this on texture creation, so we are able to access the NativeImage object.
    public void register(ReloadableTexture texture, NativeImage image) {
        imageMap.put(texture, image);
    }

    // Make sure to call this on texture removal so our map doesn't become invalid.
    public void unregister(ReloadableTexture texture) {
        imageMap.remove(texture);
    }
}
