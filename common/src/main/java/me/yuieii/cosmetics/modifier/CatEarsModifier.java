// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.yuieii.cosmetics.mixin.client.ISkullModelAccessor;
import me.yuieii.cosmetics.util.MixinUtils;
import me.yuieii.cosmetics.util.OptionalBoolean;
import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;

import java.awt.*;

public class CatEarsModifier extends Modifier implements ISkinSensitiveModifier, IHeadPartModifier, IPlayerModelAdditiveModifier {
    private static final Color CAT_EAR_COLOR = new Color(0xdb9c3e);

    @Override
    public void renderPlayerHeadPart(PlayerModel model, PoseStack poseStack, MultiBufferSource buffer, VertexConsumer vertexConsumer, int i, int j) {
        ModelPart part = model.head.getChild(CatEarsPartNames.CAT_EARS);
        part.render(poseStack, vertexConsumer, i, j);
    }

    @Override
    public void renderOnSkull(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource buffer, int i, SkullModelBase skullModelBase, RenderType renderType) {
        if (!(skullModelBase instanceof SkullModel model)) return;

        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);
        ModelPart rootPart = MixinUtils.castThenReturn(model, ISkullModelAccessor::getRoot);
        rootPart.getChild(CatEarsPartNames.CAT_EARS).render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
    }

    @Override
    public void addPartsToModel(PartDefinition rootPart) {
        PartDefinition head = rootPart.getChild(PartNames.HEAD);

        // The pivot will be the main visibility switch of the cat ears.
        // It also controls the *global* transform of the whole component.
        PartDefinition pivot = head.addOrReplaceChild(CatEarsPartNames.CAT_EARS, CubeListBuilder.create(), PartPose.ZERO);

        pivot.addOrReplaceChild(CatEarsPartNames.LEFT_EAR,
                CubeListBuilder.create()
                        .texOffs(56, 18).mirror()
                        .addBox(-1.5f, -2f, -0.5f, 3f, 2f, 1f, CubeDeformation.NONE),
                PartPose.offset(2.5f, -8f, -0.5f));

        pivot.addOrReplaceChild(CatEarsPartNames.RIGHT_EAR,
                CubeListBuilder.create()
                        .texOffs(56, 18)
                        .addBox(-1.5f, -2f, -1f, 3f, 2f, 1f, CubeDeformation.NONE),
                PartPose.offset(-2.5f, -8f, 0f));
    }

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
        if (!CommonModifierValidations.validateTrafficSignalPalette(texture)) return false;
        return UeUtils.colorAtPixel(texture, 62, 1).equals(CAT_EAR_COLOR);
    }

    private static final class CatEarsPartNames {
        public static final String CAT_EARS = "cat_ears";
        public static final String LEFT_EAR = "left_ear";
        public static final String RIGHT_EAR = "right_ear";

        private CatEarsPartNames() {}
    }
}
