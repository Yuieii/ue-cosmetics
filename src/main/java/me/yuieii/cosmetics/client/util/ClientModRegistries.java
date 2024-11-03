// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.client.util;

import com.mojang.serialization.Lifecycle;
import me.yuieii.cosmetics.UeCosmetics;
import me.yuieii.cosmetics.modifier.Modifier;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

public final class ClientModRegistries {
    public static final RegistryKey<Registry<Modifier>> MODIFIER_REGISTRY = RegistryKey.createRegistryKey(UeCosmetics.location("modifier"));
    public static final Registry<Modifier> MODIFIERS = new SimpleRegistry<>(MODIFIER_REGISTRY, Lifecycle.experimental());

    private ClientModRegistries() {}

    public static void init() {

    }
}
