// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import me.yuieii.cosmetics.entities.client.layers.PlayerModifierLayer;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRendererMixin<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererManager;Z)V", at = @At("RETURN"))
    public void ueCosmetics$injectInit(EntityRendererManager context, boolean useSlimModel, CallbackInfo ci) {
        PlayerRenderer renderer = MixinUtils.castFrom(this);
        this.addLayer(new PlayerModifierLayer(renderer));
    }
}
