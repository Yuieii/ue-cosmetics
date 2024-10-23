// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import me.yuieii.cosmetics.UeCosmetics;
import me.yuieii.cosmetics.util.ClientModRegistries;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public abstract class Modifier {
    /**
     * Returns the texture {@link ResourceLocation} of the modifier icon.
     * @return the texture {@link ResourceLocation} of the modifier icon
     */
    public ResourceLocation getIcon() {
        return Optional.ofNullable(this.getKey())
                .map(r -> r.withPrefix("textures/" + Modifier.registryKey() + "/"))
                .orElse(UeCosmetics.location("textures/gui/unknown_modifier.png"));
    }

    /**
     * Returns a list of {@link Component}s indicating the description of the modifier, line by line.
     * @return a list of {@link Component}s indicating the description of the modifier, line by line
     */
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

    /**
     * Tests if the player can apply this modifier.
     * @param player the target player to test
     * @return {@code true} if the player can apply this modifier, otherwise {@code false}
     */
    public abstract boolean isApplicable(PlayerRenderState player);

    private static String registryKey() {
        return ClientModRegistries.MODIFIER_REGISTRY.location().getPath();
    }
}
