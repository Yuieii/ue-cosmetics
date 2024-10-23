// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.yuieii.cosmetics.mixin.client.IModelAccessor;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.OptionalBoolean;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;

public class EeveeEarsModifier extends Modifier implements ISkinSensitiveModifier, IHeadPartModifier, IPlayerModelAdditiveModifier {
    @Override
    public void renderPlayerHeadPart(PlayerModel model, PoseStack poseStack, MultiBufferSource buffer, VertexConsumer vertexConsumer, int i, int j) {
        ModelPart leftEar = model.head.getChild(EeveeEarsPartNames.LEFT_EAR);
        ModelPart rightEar = model.head.getChild(EeveeEarsPartNames.RIGHT_EAR);

        leftEar.render(poseStack, vertexConsumer, i, j);
        rightEar.render(poseStack, vertexConsumer, i, j);
    }

    @Override
    public void renderOnSkull(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource buffer, int i, SkullModelBase skullModelBase, RenderType renderType) {
        if (!(skullModelBase instanceof SkullModel model)) return;

        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);
        ModelPart rootPart = MixinUtils.castThenReturn(model, IModelAccessor::getRoot);

        ModelPart leftEar = rootPart.getChild(EeveeEarsPartNames.LEFT_EAR);
        ModelPart rightEar = rootPart.getChild(EeveeEarsPartNames.RIGHT_EAR);

        leftEar.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        rightEar.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
    }

    @Override
    public void addPartsToModel(PartDefinition rootPart) {
        PartDefinition head = rootPart.getChild(PartNames.HEAD);

        head.addOrReplaceChild(EeveeEarsPartNames.LEFT_EAR,
                CubeListBuilder.create()
                        .texOffs(58, 19).addBox(0f, -6f, -0.5f, 2f, 5f, 1f, CubeDeformation.NONE)
                        .texOffs(58, 27).addBox(2f, -6f, -0.5f, 2f, 5f, 1f, CubeDeformation.NONE)
                        .texOffs(56, 38).addBox(0f, -1f, -0.5f, 3f, 1f, 1f, CubeDeformation.NONE)
                        .texOffs(58, 35).addBox(2f, -8f, -0.5f, 2f, 1f, 1f, CubeDeformation.NONE)
                        .texOffs(56, 41).addBox(1f, -7f, -0.5f, 3f, 1f, 1f, CubeDeformation.NONE),
                PartPose.offsetAndRotation(1.75f, -6.5f, 0f, -0.2182f, -0.1309f, 0.3054f));

        head.addOrReplaceChild(EeveeEarsPartNames.RIGHT_EAR,
                CubeListBuilder.create()
                        .texOffs(58, 27).mirror().addBox(-4f, -6f, -0.5f, 2f, 5f, 1f, CubeDeformation.NONE).mirror(false)
                        .texOffs(58, 19).mirror().addBox(-2f, -6f, -0.5f, 2f, 5f, 1f, CubeDeformation.NONE).mirror(false)
                        .texOffs(56, 38).addBox(-3f, -1f, -0.5f, 3f, 1f, 1f, CubeDeformation.NONE)
                        .texOffs(58, 35).addBox(-4f, -8f, -0.5f, 2f, 1f, 1f, CubeDeformation.NONE)
                        .texOffs(56, 41).mirror().addBox(-4f, -7f, -0.5f, 3f, 1f, 1f, CubeDeformation.NONE).mirror(false),
                PartPose.offsetAndRotation(-1.75f, -6.5f, 0f, -0.2182f, 0.1309f, -0.3054f));
    }

    @Override
    public boolean isApplicable(PlayerRenderState player) {
        return ISkinSensitiveModifier.super.isApplicable(player);
    }

    @Override
    public OptionalBoolean isPlayerApplicable(PlayerRenderState player) {
        return ISkinSensitiveModifier.super.isPlayerApplicable(player);
    }

    @Override
    public boolean isSkinApplicable(NativeImage texture) {
        return CommonModifierValidations.validateEeveePalette(texture);
    }

    private static final class EeveeEarsPartNames {
        public static final String LEFT_EAR = "left_ear";
        public static final String RIGHT_EAR = "right_ear";

        private EeveeEarsPartNames() {}
    }
}
