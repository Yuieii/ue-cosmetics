// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.client.util;

import com.mojang.serialization.Lifecycle;
import me.yuieii.cosmetics.UeCosmetics;
import me.yuieii.cosmetics.modifier.Modifier;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public final class ClientModRegistries {
    public static final ResourceKey<Registry<Modifier>> MODIFIER_REGISTRY = ResourceKey.createRegistryKey(UeCosmetics.location("modifier"));
    public static final Registry<Modifier> MODIFIERS = new MappedRegistry<>(MODIFIER_REGISTRY, Lifecycle.experimental());

    private ClientModRegistries() {}

    public static void init() {

    }
}
