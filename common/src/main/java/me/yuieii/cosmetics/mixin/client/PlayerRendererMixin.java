// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import me.yuieii.cosmetics.entities.client.layers.PlayerModifierLayer;
import me.yuieii.cosmetics.util.MixinUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRendererMixin<PlayerRenderState, PlayerModel> {
    @Inject(method = "<init>", at = @At("RETURN"))
    public void ueCosmetics$injectInit(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
        PlayerRenderer renderer = MixinUtils.castFrom(this);
        this.addLayer(new PlayerModifierLayer(renderer));
    }
}
