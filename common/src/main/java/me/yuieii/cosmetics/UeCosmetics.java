// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics;

import me.yuieii.cosmetics.modifier.Modifiers;
import me.yuieii.cosmetics.platform.Services;
import me.yuieii.cosmetics.platform.services.IPlatformHelper;
import me.yuieii.cosmetics.util.UeStream;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class UeCosmetics {
    /**
     * The ID of this mod. It is the namespace of all the {@link ResourceLocation} this mod owns.
     */
    public static final String MOD_ID = "uecosmetics";

    /**
     * The product name of this mod. It is shown to mod users.
     */
    public static final String PRODUCT_NAME = "ue-cosmetics";

    /**
     * The version of this mod.
     */
    public static final String VERSION = "0.0.1-alpha.1";

    /**
     * The main logger used by this mod.
     */
    public static final Logger LOGGER = LogManager.getLogger(PRODUCT_NAME);

    public static void init() {
        UeCosmetics.sanityCheck();
        LOGGER.info("Initializing: {} version {}", PRODUCT_NAME, VERSION);

        IPlatformHelper platform = Services.PLATFORM;
        LOGGER.info(
                "{} is running on platform {} ({})",
                PRODUCT_NAME,
                platform.getPlatformName(),
                platform.getEnvironmentName()
        );

        Modifiers.ensureLoad();
    }

    private static void sanityCheck() {
        // We need to make sure our mod ID is a valid namespace.
        // If that ID is invalid, we should fix this!
        if (!ResourceLocation.isValidNamespace(MOD_ID)) {
            throw new IllegalStateException("The mod ID is an invalid resource location namespace: " + MOD_ID);
        }
    }

    /**
     * Build a {@link ResourceLocation} owned by this mod, by a given path.
     * @param path the specified path under the namespace of this mod, making it owned by this mod
     * @return a {@link ResourceLocation} owned by this mod, with the specified path
     * @throws net.minecraft.ResourceLocationException if the given path is an invalid {@link ResourceLocation} path
     */
    public static ResourceLocation location(@NotNull String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
