// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import me.yuieii.cosmetics.UeCosmetics;
import me.yuieii.cosmetics.client.util.ClientModRegistries;
import net.minecraft.core.Registry;

public final class Modifiers {
    private Modifiers() {}

    // public static final Modifier GRASS = register("grass", new ISkinSensitiveModifier());

    public static final Modifier CAT_EARS = register("cat_ears", new CatEarsModifier());

    // public static final Modifier CAT_TAIL = register("cat_tail", new ISkinSensitiveModifier());

    public static final Modifier EEVEE = register("eevee", new EeveeEarsModifier());

    public static final Modifier RED_MUSHROOM = register("red_mushroom", new RedMushroomModifier());
    public static final Modifier BROWN_MUSHROOM = register("brown_mushroom", new BrownMushroomModifier());

    public static void ensureLoad() {}

    private static <T extends Modifier> T register(String name, T modifier) {
        return Registry.register(ClientModRegistries.MODIFIERS, UeCosmetics.location(name), modifier);
    }
}
