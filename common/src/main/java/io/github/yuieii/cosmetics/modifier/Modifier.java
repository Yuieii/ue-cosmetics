// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import io.github.yuieii.cosmetics.UeCosmetics;
import io.github.yuieii.cosmetics.util.ClientModRegistries;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public abstract class Modifier {
    public ResourceLocation getIcon() {
        return Optional.ofNullable(this.getKey())
                .map(r -> r.withPrefix("textures/" + Modifier.registryKey() + "/"))
                .orElse(UeCosmetics.location("textures/gui/unknown_modifier.png"));
    }

    public List<Component> getDescription() {
        return List.of();
    }

    public Component getDisplayName() {
        String path = Optional.ofNullable(this.getKey())
                .map(ResourceLocation::getPath)
                .orElse("unknown");

        return Component.translatable("ue-cosmetics." + Modifier.registryKey() + "." + path);
    }

    public ResourceLocation getKey() {
        return ClientModRegistries.MODIFIERS.getKey(this);
    }

    public abstract boolean isApplicable(AbstractClientPlayer player);

    private static String registryKey() {
        return ClientModRegistries.MODIFIER_REGISTRY.location().getPath();
    }
}
