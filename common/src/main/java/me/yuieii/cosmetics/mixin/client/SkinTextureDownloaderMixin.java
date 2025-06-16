// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.extension.IReloadableTextureExtension;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.SkinTextureDownloader;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SkinTextureDownloader.class)
public class SkinTextureDownloaderMixin {
    @Inject(method = "method_65864", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;register(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/texture/AbstractTexture;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void ueCosmetics$injectDoLoad(ResourceLocation p_389574_, NativeImage bitmap, Minecraft minecraft, CallbackInfoReturnable<ResourceLocation> cir, DynamicTexture texture) {
        MixinUtils.tryCastFrom(texture, IReloadableTextureExtension.class).ifPresent(t -> {
            t.uecosmetics$loadForPotentialSkin(bitmap);
        });
    }
}
