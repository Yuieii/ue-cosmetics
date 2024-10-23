// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;

public interface IBodyPartModifier {
    void renderPlayerBodyPart(PlayerModel model, PoseStack poseStack, MultiBufferSource buffer, VertexConsumer vertexConsumer, int i, int j);
}
