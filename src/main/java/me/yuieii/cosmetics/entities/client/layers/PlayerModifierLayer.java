// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.entities.client.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.yuieii.cosmetics.modifier.IBodyPartModifier;
import me.yuieii.cosmetics.modifier.IHeadPartModifier;
import me.yuieii.cosmetics.client.util.ClientModRegistries;
import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class PlayerModifierLayer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    public PlayerModifierLayer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> parent) {
        super(parent);
    }

    @Override
    public void render(MatrixStack poseStack, IRenderTypeBuffer bufferSource, int packedLight, AbstractClientPlayerEntity player, float f, float g, float v2, float v3, float v4, float v5) {
        PlayerModel<AbstractClientPlayerEntity> model = this.getParentModel();
        ResourceLocation location = player.getSkinTextureLocation(); // .skin.texture(); // player.getSkin().texture();
        IVertexBuilder vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(location));
        int overlay = LivingRenderer.getOverlayCoords(player, 0);

        ClientModRegistries.MODIFIERS.stream()
            .filter(m -> m.isApplicable(player))
            .forEach(m -> {
                UeUtils.with(m)
                    .ifInstanceOf(IHeadPartModifier.class, t -> {
                        t.renderPlayerHeadPart(model, poseStack, bufferSource, vertexConsumer, packedLight, overlay);
                    })
                    .ifInstanceOf(IBodyPartModifier.class, t -> {
                        t.renderPlayerBodyPart(model, poseStack, bufferSource, vertexConsumer, packedLight, overlay);
                    });
            });
    }
}
