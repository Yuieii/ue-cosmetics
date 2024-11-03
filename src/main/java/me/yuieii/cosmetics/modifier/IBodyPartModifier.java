// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.PlayerModel;

public interface IBodyPartModifier {
    void renderPlayerBodyPart(PlayerModel<AbstractClientPlayerEntity> model, MatrixStack poseStack, IRenderTypeBuffer buffer, IVertexBuilder vertexConsumer, int i, int j);
}
