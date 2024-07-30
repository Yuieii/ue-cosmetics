// Copyright (c) 2024 Yuieii.
package io.github.yuieii.cosmetics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UeCosmetics {
    public static final String MOD_ID = "ue-cosmetics";
    public static final String PRODUCT_NAME = "ue-cosmetics";
    public static final String VERSION = "0.0.1-alpha.1";

    public static final Logger LOGGER = LogManager.getLogger(PRODUCT_NAME);

    public static void init() {
        LOGGER.info("Initializing {}", PRODUCT_NAME);
    }
}
