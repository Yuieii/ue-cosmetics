// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import me.yuieii.cosmetics.UeCosmetics;
import me.yuieii.cosmetics.client.util.ClientModRegistries;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Modifier {
    /**
     * Returns the texture {@link ResourceLocation} of the modifier icon.
     * @return the texture {@link ResourceLocation} of the modifier icon
     */
    public ResourceLocation getIcon() {
        return Optional.ofNullable(this.getKey())
                .map(r -> new ResourceLocation(r.getNamespace(), "textures/" + Modifier.registryKey() + "/" + r.getPath()))
                .orElse(UeCosmetics.location("textures/gui/unknown_modifier.png"));
    }

    /**
     * Returns a list of {@link ITextComponent}s indicating the description of the modifier, line by line.
     * @return a list of {@link ITextComponent}s indicating the description of the modifier, line by line
     */
    public List<ITextComponent> getDescription() {
        return new ArrayList<>();
    }

    public ITextComponent getDisplayName() {
        String path = Optional.ofNullable(this.getKey())
                .map(ResourceLocation::getPath)
                .orElse("unknown");

        return new TranslationTextComponent("ue-cosmetics." + Modifier.registryKey() + "." + path);
    }

    public ResourceLocation getKey() {
        return ClientModRegistries.MODIFIERS.getKey(this);
    }

    /**
     * Tests if the player can apply this modifier.
     * @param player the target player to test
     * @return {@code true} if the player can apply this modifier, otherwise {@code false}
     */
    public abstract boolean isApplicable(AbstractClientPlayerEntity player);

    private static String registryKey() {
        return ClientModRegistries.MODIFIER_REGISTRY.location().getPath();
    }
}
