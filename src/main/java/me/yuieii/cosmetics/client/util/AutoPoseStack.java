package me.yuieii.cosmetics.client.util;

import com.mojang.blaze3d.matrix.MatrixStack;

public class AutoPoseStack implements AutoCloseable {
    private final MatrixStack poseStack;

    public AutoPoseStack(MatrixStack poseStack) {
        this.poseStack = poseStack;
        this.poseStack.pushPose();
    }

    public MatrixStack poseStack() {
        return this.poseStack;
    }

    @Override
    public void close() {
        this.poseStack.popPose();
    }
}
