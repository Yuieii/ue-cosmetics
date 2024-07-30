package io.github.yuieii.cosmetics.forge;

import net.minecraftforge.fml.common.Mod;

import io.github.yuieii.cosmetics.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
