// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.renderer.texture.DownloadingTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DownloadingTexture.class)
public class HttpTextureMixin extends SimpleTextureMixin {
    @Inject(method = "upload", at = @At("HEAD"))
    public void ueCosmetics$injectDoLoad(NativeImage image, CallbackInfo ci) {
        MixinUtils.tryCastFrom(this, DownloadingTexture.class).ifPresent(t -> {
            UeCosmeticsClient.getInstance().getTextureStore().register(t, image);
            this.ueCosmetics$loadForPotentialSkin(image);
        });
    }
}
