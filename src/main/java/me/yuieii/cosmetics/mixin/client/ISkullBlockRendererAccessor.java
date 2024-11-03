package me.yuieii.cosmetics.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SkullTileEntityRenderer.class)
public interface ISkullBlockRendererAccessor {
    @Invoker("getRenderType")
    static RenderType uecosmetics$getRenderType(SkullBlock.ISkullType p_228878_0_, GameProfile p_228878_1_) {
        throw new AssertionError("Mixin");
    }
}
