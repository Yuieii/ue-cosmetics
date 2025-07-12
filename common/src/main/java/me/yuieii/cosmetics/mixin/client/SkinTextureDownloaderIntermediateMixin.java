// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.extension.IReloadableTextureExtension;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.SkinTextureUtils;
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
public class SkinTextureDownloaderIntermediateMixin {
    // method_65864 => lambda$registerTextureInManager$2(...)
    @Inject(method = "method_65864", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;register(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/texture/AbstractTexture;)V"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private static void ueCosmetics$injectDoLoad_intermediate(ResourceLocation p_389574_, NativeImage bitmap, Minecraft minecraft, CallbackInfoReturnable<ResourceLocation> cir, DynamicTexture texture) {
        SkinTextureUtils.loadPotentialSkin(bitmap, texture);
    }
}
