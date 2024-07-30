package io.github.yuieii.cosmetics.mixin.client;

import io.github.yuieii.cosmetics.client.UeCosmeticsClient;
import io.github.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractTexture.class)
public class AbstractTextureMixin {
    @Inject(method = "releaseId", at = @At("TAIL"))
    public void ueCosmetics$injectReleaseId(CallbackInfo ci) {
        AbstractTexture self = MixinUtils.castSelf(this);
        if (self instanceof SimpleTexture simple) {
            UeCosmeticsClient.getTextureStore().unregister(simple);
        }
    }
}
