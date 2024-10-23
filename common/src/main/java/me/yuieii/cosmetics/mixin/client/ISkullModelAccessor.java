// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SkullModel.class)
public interface ISkullModelAccessor {
    @Accessor
    ModelPart getRoot();
}
