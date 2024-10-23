// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.modifier;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class RedMushroomModifier extends AbstractMushroomModifier {
    @Override
    public BlockState getBlockState() {
        return Blocks.RED_MUSHROOM.defaultBlockState();
    }

    @Override
    public Color getPrimaryMushroomColor() {
        return AbstractMushroomModifier.RED_MUSHROOM_COLOR;
    }
}
