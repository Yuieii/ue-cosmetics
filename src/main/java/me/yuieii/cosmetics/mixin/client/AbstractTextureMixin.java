// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.client.util.SimpleTextureStore;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.Texture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Texture.class)
public class AbstractTextureMixin {
    @Inject(method = "close", at = @At("HEAD"))
    public void ueCosmetics$close(CallbackInfo ci){
        MixinUtils.tryCastFrom(this, SimpleTexture.class).ifPresent(t -> {
            SimpleTextureStore store = UeCosmeticsClient.getInstance().getTextureStore();
            NativeImage image = store.get(t);
            if (image != null) {
                image.close();
                store.unregister(t);
            }
        });
    }

    @Inject(method = "reset", at = @At("HEAD"))
    public void ueCosmetics$reset(CallbackInfo ci){
        MixinUtils.tryCastFrom(this, SimpleTexture.class).ifPresent(t -> {
            SimpleTextureStore store = UeCosmeticsClient.getInstance().getTextureStore();
            NativeImage image = store.get(t);
            if (image != null) {
                image.close();
            }
        });
    }
}
