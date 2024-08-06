// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;

public interface IBodyPartModifier {
    void renderPlayerBodyPart(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, MultiBufferSource buffer, VertexConsumer vertexConsumer, int i, int j);
}
