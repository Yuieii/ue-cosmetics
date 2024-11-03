// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.mixin.client;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GenericHeadModel.class)
public interface IModelAccessor {
    @Accessor("head")
    ModelRenderer getRoot();
}
