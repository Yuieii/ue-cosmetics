package me.yuieii.cosmetics.mixin.client;

import net.minecraft.client.renderer.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NativeImage.class)
public interface INativeImageAccessor {
    @Accessor("pixels")
    long uecosmetics$getPixels();
}
