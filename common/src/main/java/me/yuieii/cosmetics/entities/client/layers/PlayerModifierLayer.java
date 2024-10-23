// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.entities.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.yuieii.cosmetics.modifier.IBodyPartModifier;
import me.yuieii.cosmetics.modifier.IHeadPartModifier;
import me.yuieii.cosmetics.util.ClientModRegistries;
import me.yuieii.cosmetics.util.UeOperationContext;
import me.yuieii.cosmetics.util.UeUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;

public class PlayerModifierLayer extends RenderLayer<PlayerRenderState, PlayerModel> {
    public PlayerModifierLayer(RenderLayerParent<PlayerRenderState, PlayerModel> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, PlayerRenderState player, float f, float g) {
        PlayerModel model = this.getParentModel();
        ResourceLocation location = player.skin.texture(); // player.getSkin().texture();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(location));
        int overlay = LivingEntityRenderer.getOverlayCoords(player, 0);

        ClientModRegistries.MODIFIERS.stream()
                .filter(m -> m.isApplicable(player))
                .forEach(m -> {
                    UeUtils.with(m)
                            .ifInstanceOf(IHeadPartModifier.class, t -> {
                                t.renderPlayerHeadPart(model, poseStack, bufferSource, vertexConsumer, packedLight, overlay);
                            })
                            .ifInstanceOf(IBodyPartModifier.class, t -> {
                                t.renderPlayerBodyPart(model, poseStack, bufferSource, vertexConsumer, packedLight, overlay);
                            });
                });
    }
}
