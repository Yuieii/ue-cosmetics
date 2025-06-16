// Copyright (c) 2024-2025 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.client.extension.IReloadableTextureExtension;
import me.yuieii.cosmetics.client.util.ClientModRegistries;
import me.yuieii.cosmetics.modifier.ISkinSensitiveModifier;
import me.yuieii.cosmetics.modifier.SkinSensitiveModifierData;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.client.util.ReloadableTextureStore;
import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ReloadableTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(AbstractTexture.class)
public class AbstractTextureMixin implements IReloadableTextureExtension {
    protected final Map<ISkinSensitiveModifier, SkinSensitiveModifierData> ueCosmetics$skinModifierData = new HashMap<>();

    @Inject(method = "close", at = @At("HEAD"))
    public void ueCosmetics$close(CallbackInfo ci){
        MixinUtils.tryCastFrom(this, ReloadableTexture.class).ifPresent(t -> {
            ReloadableTextureStore store = UeCosmeticsClient.getInstance().getTextureStore();
            NativeImage image = store.get(t);
            if (image != null) {
                image.close();
                store.unregister(t);
            }
        });
    }

    @Inject(method = "close", at = @At("HEAD"))
    public void ueCosmetics$reset(CallbackInfo ci){
        MixinUtils.tryCastFrom(this, ReloadableTexture.class).ifPresent(t -> {
            ReloadableTextureStore store = UeCosmeticsClient.getInstance().getTextureStore();
            NativeImage image = store.get(t);
            if (image != null) {
                image.close();
            }
        });
    }

    @Override
    public void uecosmetics$loadForPotentialSkin(NativeImage image) {
        this.ueCosmetics$skinModifierData.clear();
        ClientModRegistries.MODIFIERS.stream()
            .forEach(m -> {
                UeUtils.with(m)
                    .ifInstanceOf(ISkinSensitiveModifier.class, sm -> {
                        this.ueCosmetics$skinModifierData.put(sm, new SkinSensitiveModifierData(
                                sm.isSkinApplicable(image)
                        ));
                    });
            });
    }

    @Override
    public SkinSensitiveModifierData uecosmetics$getData(ISkinSensitiveModifier modifier) {
        SkinSensitiveModifierData result = this.ueCosmetics$skinModifierData.get(modifier);
        if (result == null && !this.ueCosmetics$skinModifierData.isEmpty()) {
            throw new IllegalStateException(
                    "Every mapping from ISkinSensitiveModifier to SkinSensitiveModifierData must be registered at loading phase. " +
                            "Found missing data for modifier: " + modifier
            );
        }

        return result;
    }
}
