package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.UeCosmetics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(NativeImage.class)
public class NativeImageMixin {
    @Redirect(method = "_upload", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/NativeImage;close()V"))
    public void ueCosmetics$noAutoClose(NativeImage instance) {
        // UeCosmetics.LOGGER.info("{} auto close suppressed.", this);
    }
}
