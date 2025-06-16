// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import me.yuieii.cosmetics.client.extension.IRenderTypeExtension;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(RenderType.class)
public class RenderTypeMixin implements IRenderTypeExtension {
    private @Nullable ResourceLocation uecosmetics$boundTexture;

    @Inject(method = "entityTranslucent(Lnet/minecraft/resources/ResourceLocation;Z)Lnet/minecraft/client/renderer/RenderType;", at = @At(("RETURN")))
    private static void uecosmetics$storeEntityTranslucentTexture(ResourceLocation location, boolean outline, CallbackInfoReturnable<RenderType> cir) {
        RenderType self = cir.getReturnValue();
        MixinUtils.tryCastFrom(self, IRenderTypeExtension.class).ifPresent(r -> {
            r.uecosmetics$setBoundTexture(location);
        });
    }

    @Override
    public Optional<ResourceLocation> uecosmetics$boundTexture() {
        return Optional.ofNullable(this.uecosmetics$boundTexture);
    }

    @Override
    public void uecosmetics$setBoundTexture(ResourceLocation location) {
        this.uecosmetics$boundTexture = location;
    }
}
