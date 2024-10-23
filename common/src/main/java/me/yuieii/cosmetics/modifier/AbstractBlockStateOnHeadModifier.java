package me.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import me.yuieii.cosmetics.util.AutoPoseStack;
import me.yuieii.cosmetics.util.OptionalBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public abstract class AbstractBlockStateOnHeadModifier extends Modifier implements ISkinSensitiveModifier, IHeadPartModifier {
    public abstract BlockState getBlockState();

    @Override
    public boolean isApplicable(PlayerRenderState player) {
        return ISkinSensitiveModifier.super.isApplicable(player);
    }

    @Override
    public OptionalBoolean isPlayerApplicable(PlayerRenderState player) {
        return ISkinSensitiveModifier.super.isPlayerApplicable(player);
    }

    @Override
    public void renderPlayerHeadPart(PlayerModel model, PoseStack poseStack, MultiBufferSource buffer, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        try (AutoPoseStack psa = new AutoPoseStack(poseStack)) {
            PoseStack pa = psa.poseStack();
            model.getHead().translateAndRotate(pa);

            final float sc = 1.1875f;
            pa.scale(sc, -sc, -sc);
            pa.translate(0, 0.4, 0);

            float scale = 0.5f;
            pa.scale(scale, scale, scale);

            try (AutoPoseStack psb = new AutoPoseStack(pa)) {
                PoseStack pb = psb.poseStack();
                pb.translate(-0.5, 0, -0.5);

                try (AutoPoseStack psc = new AutoPoseStack(pb)) {
                    PoseStack pc = psc.poseStack();
                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                            this.getBlockState(), pc, buffer, packedLight, packedOverlay);
                }
            }
        }
    }

    @Override
    public void renderOnSkull(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource buffer, int packedLight, SkullModelBase skullModelBase, RenderType renderType) {
        try (AutoPoseStack psa = new AutoPoseStack(poseStack)) {
            PoseStack pa = psa.poseStack();
            pa.mulPose(Axis.YP.rotationDegrees(f));

            try (AutoPoseStack psb = new AutoPoseStack(pa)) {
                PoseStack pb = psb.poseStack();
                pb.translate(-0.25, -0.5, -0.25);

                try (AutoPoseStack psc = new AutoPoseStack(pb)) {
                    PoseStack pc = psc.poseStack();

                    final float s = 0.5f;
                    pc.scale(s, -s, s);

                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                            this.getBlockState(), pc, buffer, packedLight, OverlayTexture.NO_OVERLAY);
                }
            }
        }
    }
}
