// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SimpleTexture.class)
public class SimpleTextureMixin {
    @Inject(method = "doLoad", at = @At("HEAD"))
    public void ueCosmetics$injectDoLoad(NativeImage image, boolean blur, boolean clamp, CallbackInfo ci) {
        MixinUtils.tryCastFrom(this, SimpleTexture.class).ifPresent(t -> {
            UeCosmeticsClient.getInstance().getTextureStore().register(t, image);
        });
    }
}
