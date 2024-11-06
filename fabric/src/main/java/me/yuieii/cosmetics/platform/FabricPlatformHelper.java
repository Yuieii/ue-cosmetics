package me.yuieii.cosmetics.platform;

import me.yuieii.cosmetics.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {
    private final FabricLoader loader = FabricLoader.getInstance();

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return this.loader.isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return this.loader.isDevelopmentEnvironment();
    }
}
