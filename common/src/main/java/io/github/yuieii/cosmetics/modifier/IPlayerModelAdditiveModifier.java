// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics.modifier;

import net.minecraft.client.model.geom.builders.PartDefinition;

public interface IPlayerModelAdditiveModifier {
    void addPartsToModel(PartDefinition rootPart);
}
