// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.client.extension.IReloadableTextureExtension;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.renderer.texture.ReloadableTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReloadableTexture.class)
public class ReloadableTextureMixin extends AbstractTextureMixin implements IReloadableTextureExtension {
    @Inject(method = "doLoad", at = @At("HEAD"))
    public void ueCosmetics$injectDoLoad(NativeImage image, boolean blur, boolean clamp, CallbackInfo ci) {
        MixinUtils.tryCastFrom(this, SimpleTexture.class).ifPresent(t -> {
            UeCosmeticsClient.getInstance().getTextureStore().register(t, image);

            // TODO: Check if a constant exists
            if (!(image.getWidth() == 64 && image.getHeight() == 64)) return;
            this.uecosmetics$loadForPotentialSkin(image);
        });
    }
}
