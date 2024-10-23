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
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PlayerModifierLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerModifierLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, float v, float v1, float v2, float v3, float v4, float v5) {
        PlayerModel<AbstractClientPlayer> model = this.getParentModel();
        ResourceLocation location = player.getSkinTextureLocation();
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
