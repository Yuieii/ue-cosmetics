// Copyright (c) 2024 Yuieii.
package me.yuieii.cosmetics.dev;

@SuppressWarnings("PointlessBooleanExpression")
public final class DebugConstants {
    private DebugConstants() {
        // Utility class.
    }

    /**
     * The switch of all the features below for debug purposes.
     * If debug mode is disabled, all the features below are also disabled.
     */
    public static final boolean ENABLE_DEBUG_MODE = true;

    // -------------------------------------------------------------------------------------------

    /**
     * Whether the mushroom modifier test should be bypassed and thus the modifier should always be applied.
     * <p>Only affects the development environment. The modifier is always checked outside of the development environment.</p>
     */
    public static final boolean BYPASS_MUSHROOM_MODIFIER_TEST = ENABLE_DEBUG_MODE && true;
    public static final boolean WARN_MISSING_MODEL_PART = ENABLE_DEBUG_MODE && true;
}
