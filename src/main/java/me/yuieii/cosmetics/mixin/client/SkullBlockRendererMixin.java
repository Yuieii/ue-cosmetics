// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.matrix.MatrixStack;
import me.yuieii.cosmetics.client.extension.ISimpleTextureExtension;
import me.yuieii.cosmetics.modifier.IHeadPartModifier;
import me.yuieii.cosmetics.modifier.ISkinSensitiveModifier;
import me.yuieii.cosmetics.modifier.Modifier;
import me.yuieii.cosmetics.client.util.ClientModRegistries;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.UeStream;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(SkullTileEntityRenderer.class)
public class SkullBlockRendererMixin {
    @Unique
    private static final List<Modifier> applicableModifiers = new ArrayList<>();

    @ModifyArg(method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entityTranslucent(Lnet/minecraft/util/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private static ResourceLocation ueCosmetics$injectRenderType(ResourceLocation location) {
        applicableModifiers.clear();

        ClientModRegistries.MODIFIERS.stream()
            .filter(m -> m instanceof IHeadPartModifier && m instanceof ISkinSensitiveModifier)
            .forEach(m -> {
                Texture texture = Minecraft.getInstance().getTextureManager().getTexture(location);
                MixinUtils.tryCastFrom(texture, ISimpleTextureExtension.class).ifPresent(t -> {
                    if (t.uecosmetics$getData((ISkinSensitiveModifier) m).isApplicable()) {
                        applicableModifiers.add(m);
                    }
                });
            });

        return location;
    }

    @Inject(method = "renderSkull", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/matrix/MatrixStack;popPose()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void ueCosmetics$injectPostRenderSkullModel(Direction direction, float yRot, SkullBlock.ISkullType type, GameProfile profile, float f, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int packedLight, CallbackInfo ci, GenericHeadModel model) {
        UeStream.stream(applicableModifiers.stream())
            .instanceOf(IHeadPartModifier.class)
            .forEach(m -> {
                m.renderOnSkull(direction, yRot, type, profile, f, poseStack, bufferSource, packedLight, model);
            });
        applicableModifiers.clear();
    }
}
