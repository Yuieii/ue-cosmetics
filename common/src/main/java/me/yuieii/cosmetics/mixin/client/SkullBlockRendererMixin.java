// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import me.yuieii.cosmetics.client.UeCosmeticsClient;
import me.yuieii.cosmetics.modifier.IHeadPartModifier;
import me.yuieii.cosmetics.modifier.ISkinSensitiveModifier;
import me.yuieii.cosmetics.modifier.Modifier;
import me.yuieii.cosmetics.util.ClientModRegistries;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.UeStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererMixin {
    @Unique
    private static final List<Modifier> applicableModifiers = new ArrayList<>();

    @ModifyArg(method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entityTranslucent(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private static ResourceLocation ueCosmetics$injectRenderType(ResourceLocation location) {
        applicableModifiers.clear();

        ClientModRegistries.MODIFIERS.stream()
                .filter(m -> m instanceof IHeadPartModifier && m instanceof ISkinSensitiveModifier)
                .forEach(m -> {
                    AbstractTexture texture = Minecraft.getInstance().getTextureManager().getTexture(location);
                    MixinUtils.tryCastFrom(texture, SimpleTexture.class).ifPresent(t -> {
                        NativeImage bitmap = UeCosmeticsClient.getInstance().getTextureStore().get(t);
                        if (((ISkinSensitiveModifier) m).isSkinApplicable(bitmap)) {
                            applicableModifiers.add(m);
                        }
                    });
                });

        return location;
    }

    @Inject(method = "renderSkull", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"))
    private static void ueCosmetics$injectPostRenderSkullModel(Direction direction, float yRot, float mouthAnimation, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, SkullModelBase model, RenderType renderType, CallbackInfo ci) {
        UeStream.stream(applicableModifiers.stream())
                .instanceOf(IHeadPartModifier.class)
                .forEach(m -> {
                    m.renderOnSkull(direction, yRot, mouthAnimation, poseStack, bufferSource, packedLight, model, renderType);
                });
        applicableModifiers.clear();
    }
}
