package me.yuieii.cosmetics;


import me.yuieii.cosmetics.client.UeCosmeticsForgeClient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(UeCosmetics.MOD_ID_FORGE)
public class UeCosmeticsForge {
    public UeCosmeticsForge(IEventBus eventBus) {
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.

        UeCosmetics.init();

        if (FMLEnvironment.dist.isClient()) {
            UeCosmeticsForgeClient.init();
        }
    }
}
