package me.yuieii.cosmetics;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.player.AbstractClientPlayer;

public final class UeCosmeticsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run the common setup.
        UeCosmetics.init();
    }
}
