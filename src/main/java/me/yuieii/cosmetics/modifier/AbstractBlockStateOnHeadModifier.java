package me.yuieii.cosmetics.modifier;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.yuieii.cosmetics.client.util.AutoPoseStack;
import me.yuieii.cosmetics.util.OptionalBoolean;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.vector.Vector3f;

public abstract class AbstractBlockStateOnHeadModifier extends Modifier implements ISkinSensitiveModifier, IHeadPartModifier {
    public abstract BlockState getBlockState();

    @Override
    public boolean isApplicable(AbstractClientPlayerEntity player) {
        return ISkinSensitiveModifier.super.isApplicable(player);
    }

    @Override
    public OptionalBoolean isPlayerApplicable(AbstractClientPlayerEntity player) {
        return ISkinSensitiveModifier.super.isPlayerApplicable(player);
    }

    @Override
    public void renderPlayerHeadPart(PlayerModel<AbstractClientPlayerEntity> model, MatrixStack poseStack, IRenderTypeBuffer buffer, IVertexBuilder vertexConsumer, int i, int j) {
        try (AutoPoseStack psa = new AutoPoseStack(poseStack)) {
            MatrixStack pa = psa.poseStack();
            model.getHead().translateAndRotate(pa);

            final float sc = 1.1875f;
            pa.scale(sc, -sc, -sc);
            pa.translate(0, 0.4, 0);

            float scale = 0.5f;
            pa.scale(scale, scale, scale);

            try (AutoPoseStack psb = new AutoPoseStack(pa)) {
                MatrixStack pb = psb.poseStack();
                pb.translate(-0.5, 0, -0.5);

                try (AutoPoseStack psc = new AutoPoseStack(pb)) {
                    MatrixStack pc = psc.poseStack();
                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                        this.getBlockState(), pc, buffer, i, j);
                }
            }
        }
    }

    @Override
    public void renderOnSkull(net.minecraft.util.Direction direction, float yRot, SkullBlock.ISkullType type, GameProfile profile, float f, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int packedLight, GenericHeadModel model) {
        try (AutoPoseStack psa = new AutoPoseStack(poseStack)) {
            MatrixStack pa = psa.poseStack();
            pa.mulPose(Vector3f.YP.rotationDegrees(f));

            try (AutoPoseStack psb = new AutoPoseStack(pa)) {
                MatrixStack pb = psb.poseStack();
                pb.translate(-0.25, -0.5, -0.25);

                try (AutoPoseStack psc = new AutoPoseStack(pb)) {
                    MatrixStack pc = psc.poseStack();

                    final float s = 0.5f;
                    pc.scale(s, -s, s);

                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                        this.getBlockState(), pc, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                }
            }
        }
    }
}
