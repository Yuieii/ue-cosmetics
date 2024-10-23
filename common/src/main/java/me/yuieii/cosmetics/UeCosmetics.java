// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics;

import me.yuieii.cosmetics.modifier.Modifiers;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class UeCosmetics {
    /**
     * The ID of this mod. It is the namespace of all the {@link ResourceLocation} this mod owns.
     */
    public static final String MOD_ID = "ue-cosmetics";

    public static final String MOD_ID_FORGE = "uecosmetics";

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

        Modifiers.ensureLoad();
    }

    private static void sanityCheck() {
        // We need to make sure our mod ID is a valid namespace.
        // If that ID is invalid, we should fix this!
        if (!ResourceLocation.isValidResourceLocation(MOD_ID + ":path")) {
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
        ResourceLocation result = ResourceLocation.tryBuild(MOD_ID, path);
        if (result != null) return result;

        throw new IllegalArgumentException("Invalid path: " + path);
    }
}
