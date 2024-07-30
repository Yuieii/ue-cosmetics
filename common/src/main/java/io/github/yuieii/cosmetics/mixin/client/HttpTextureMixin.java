package io.github.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import io.github.yuieii.cosmetics.client.UeCosmeticsClient;
import io.github.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HttpTexture.class)
public class HttpTextureMixin {
    @Inject(method = "upload", at = @At("HEAD"))
    public void ueCosmetics$injectDoLoad(NativeImage image, CallbackInfo ci) {
        HttpTexture self = MixinUtils.castSelf(this);
        UeCosmeticsClient.getTextureStore().register(self, image);
    }
}
