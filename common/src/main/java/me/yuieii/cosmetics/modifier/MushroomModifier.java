// Copyright (c) 2024 Yuieii.
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
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;

public class MushroomModifier extends Modifier implements ISkinSensitiveModifier, IHeadPartModifier {
    @Override
    public boolean isApplicable(AbstractClientPlayer player) {
        return ISkinSensitiveModifier.super.isApplicable(player);
    }

    @Override
    public OptionalBoolean isPlayerApplicable(AbstractClientPlayer player) {
        return ISkinSensitiveModifier.super.isPlayerApplicable(player);
    }

    @Override
    public boolean isSkinApplicable(NativeImage texture) {
        return true; // MushroomModifier.validateYuieiiMushroomPattern(texture, false);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean validateYuieiiMushroomPattern(NativeImage bitmap, boolean checkBackground) {
        // For this we want an exact same mushroom pattern placed at the most top left.
        // @formatter:off
        int[] redPos = {
                  3, 4, 4, 4,
            2, 5,       4, 5, 5, 5,
            2, 6, 3, 6, 4, 6, 5, 6
        };

        int[] whitePos = {
                  3, 5
        };

        int[] stemPos = {
                  3, 7, 4, 7
        };
        // @formatter:on

        if (!ISkinSensitiveModifier.colorsAllMatch(redPos, bitmap, new Color(0xca6262))) return false;
        if (!ISkinSensitiveModifier.colorsAllMatch(whitePos, bitmap, new Color(0xffffff))) return false;
        if (!ISkinSensitiveModifier.colorsAllMatch(stemPos, bitmap, new Color(0xffd4bb))) return false;

        if (!checkBackground) return true;

        // @formatter:off
        int[] backPos = {
                0, 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0,
                0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1,
                0, 2, 1, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6, 2, 7, 2,
                0, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3, 6, 3, 7, 3,
                0, 4, 1, 4, 2, 4,             5, 4, 6, 4, 7, 4,
                0, 5, 1, 5,                         6, 5, 7, 5,
                0, 6, 1, 6,                         6, 6, 7, 6,
                0, 7, 1, 7, 2, 7,             5, 7, 6, 7, 7, 7,
        };
        // @formatter:on

        return ISkinSensitiveModifier.allTransparent(backPos, bitmap);
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
                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.RED_MUSHROOM.defaultBlockState(), pc, buffer, packedLight, packedOverlay);
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
                            Blocks.RED_MUSHROOM.defaultBlockState(), pc, buffer, packedLight, OverlayTexture.NO_OVERLAY);
                }
            }
        }
    }
}
