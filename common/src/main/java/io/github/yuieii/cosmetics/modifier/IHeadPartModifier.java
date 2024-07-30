package io.github.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;

public interface IHeadPartModifier {
    void renderOnPlayer(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, MultiBufferSource buffer, VertexConsumer vertexConsumer, int i, int j);
    void renderOnSkull(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource buffer, int i, SkullModelBase skullModelBase, RenderType renderType);
}
