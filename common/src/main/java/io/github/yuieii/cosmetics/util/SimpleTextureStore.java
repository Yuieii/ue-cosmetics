package io.github.yuieii.cosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleTextureStore {
    private final Map<SimpleTexture, NativeImage> imageMap = new ConcurrentHashMap<>();

    public @Nullable NativeImage get(SimpleTexture texture) {
        return imageMap.get(texture);
    }

    public void register(SimpleTexture texture, NativeImage image) {
        imageMap.put(texture, image);
    }

    public void unregister(SimpleTexture texture) {
        imageMap.remove(texture);
    }
}
