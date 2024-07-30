// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import io.github.yuieii.cosmetics.UeCosmetics;
import io.github.yuieii.cosmetics.util.ClientModRegistries;
import net.minecraft.core.Registry;

public final class Modifiers {
    private Modifiers() {}

    // public static final Modifier GRASS = register("grass", new ISkinSensitiveModifier());
    // public static final Modifier CAT_EARS = register("cat_ears", new ISkinSensitiveModifier());
    // public static final Modifier CAT_TAIL = register("cat_tail", new ISkinSensitiveModifier());
    // public static final Modifier EEVEE = register("eevee", new ISkinSensitiveModifier());
    public static final Modifier MUSHROOM = register("mushroom", new MushroomModifier());

    private static <T extends Modifier> T register(String name, T modifier) {
        return Registry.register(ClientModRegistries.MODIFIERS, UeCosmetics.location(name), modifier);
    }
}
