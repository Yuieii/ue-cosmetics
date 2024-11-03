// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.Direction;

public interface IHeadPartModifier {
    void renderPlayerHeadPart(PlayerModel<AbstractClientPlayerEntity> model, MatrixStack poseStack, IRenderTypeBuffer buffer, IVertexBuilder vertexConsumer, int i, int j);
    void renderOnSkull(Direction direction, float yRot, SkullBlock.ISkullType type, GameProfile profile, float f, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int packedLight, GenericHeadModel model);
}
