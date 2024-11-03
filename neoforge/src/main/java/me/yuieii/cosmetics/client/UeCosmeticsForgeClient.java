package me.yuieii.cosmetics.client;

import me.yuieii.cosmetics.UeCosmetics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = UeCosmetics.MOD_ID, dist = Dist.CLIENT)
public class UeCosmeticsForgeClient {
    public UeCosmeticsForgeClient(IEventBus eventBus) {
        UeCosmeticsClient.init();
    }
}
