package me.yuieii.cosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.extension.IReloadableTextureExtension;
import net.minecraft.client.renderer.texture.DynamicTexture;

public enum SkinTextureUtils {
    ;

    public static void loadPotentialSkin(NativeImage bitmap, DynamicTexture texture) {
        MixinUtils.tryCastFrom(texture, IReloadableTextureExtension.class).ifPresent(t -> {
            t.uecosmetics$loadForPotentialSkin(bitmap);
        });
    }
}
