package me.yuieii.cosmetics;


import me.yuieii.cosmetics.client.UeCosmeticsForgeClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(UeCosmetics.MOD_ID_FORGE)
public class UeCosmeticsForge {
    public UeCosmeticsForge() {
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        UeCosmetics.init();

        if (FMLEnvironment.dist.isClient()) {
            UeCosmeticsForgeClient.init();
        }
    }
}
