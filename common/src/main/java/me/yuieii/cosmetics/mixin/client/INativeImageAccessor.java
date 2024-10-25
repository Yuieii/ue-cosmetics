package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NativeImage.class)
public interface INativeImageAccessor {
    @Accessor("pixels")
    long uecosmetics$getPixels();
}
