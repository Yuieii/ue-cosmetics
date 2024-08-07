// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.mixin.client;

import io.github.yuieii.cosmetics.entities.client.layers.PlayerModifierLayer;
import io.github.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRendererMixin<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    @Inject(method = "<init>", at = @At("RETURN"))
    public void ueCosmetics$injectInit(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
        PlayerRenderer renderer = MixinUtils.castFrom(this);
        this.addLayer(new PlayerModifierLayer(renderer));
    }
}
