package me.yuieii.cosmetics.client.util;

import com.mojang.blaze3d.vertex.PoseStack;

public class AutoPoseStack implements AutoCloseable {
    private final PoseStack poseStack;

    public AutoPoseStack(PoseStack poseStack) {
        this.poseStack = poseStack;
        this.poseStack.pushPose();
    }

    public PoseStack poseStack() {
        return this.poseStack;
    }

    @Override
    public void close() {
        this.poseStack.popPose();
    }
}
