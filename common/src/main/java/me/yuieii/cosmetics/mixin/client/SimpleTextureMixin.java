// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.client.extension.ISimpleTextureExtension;
import me.yuieii.cosmetics.client.util.ClientModRegistries;
import me.yuieii.cosmetics.modifier.ISkinSensitiveModifier;
import me.yuieii.cosmetics.modifier.SkinSensitiveModifierData;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.renderer.texture.SimpleTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(SimpleTexture.class)
public class SimpleTextureMixin implements ISimpleTextureExtension {
    private final Map<ISkinSensitiveModifier, SkinSensitiveModifierData> ueCosmetics$skinModifierData = new HashMap<>();

    @Inject(method = "doLoad", at = @At("HEAD"))
    public void ueCosmetics$injectDoLoad(NativeImage image, boolean blur, boolean clamp, CallbackInfo ci) {
        MixinUtils.tryCastFrom(this, SimpleTexture.class).ifPresent(t -> {
            UeCosmeticsClient.getInstance().getTextureStore().register(t, image);

            // TODO: Check if a constant exists
            if (!(image.getWidth() == 64 && image.getHeight() == 64)) return;
            this.ueCosmetics$loadForPotentialSkin(image);
        });
    }

    protected void ueCosmetics$loadForPotentialSkin(NativeImage image) {
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
