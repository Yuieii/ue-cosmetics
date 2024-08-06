// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.entities.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.yuieii.cosmetics.modifier.IBodyPartModifier;
import io.github.yuieii.cosmetics.modifier.IHeadPartModifier;
import io.github.yuieii.cosmetics.util.ClientModRegistries;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class PlayerModifierLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerModifierLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        PlayerModel<AbstractClientPlayer> model = this.getParentModel();
        ResourceLocation location = player.getSkin().texture();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(location));
        int overlay = LivingEntityRenderer.getOverlayCoords(player, 0);

        ClientModRegistries.MODIFIERS.stream()
                .filter(m -> m.isApplicable(player))
                .forEach(m -> {
                    if (m instanceof IHeadPartModifier h) {
                        h.renderPlayerHeadPart(model, poseStack, bufferSource, vertexConsumer, packedLight, overlay);
                    }

                    if (m instanceof IBodyPartModifier b) {
                        b.renderPlayerBodyPart(model, poseStack, bufferSource, vertexConsumer, packedLight, overlay);
                    }
                });
    }
}
