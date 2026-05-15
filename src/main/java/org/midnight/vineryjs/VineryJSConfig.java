package org.midnight.vineryjs;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class VineryJSConfig {
    public static boolean debugLogging = false;
    public static boolean globalAllowPlacement = false;

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue ALLOW_PLACEMENT;
    public static final ModConfigSpec.BooleanValue DEBUG_LOGGING;

    static {
        BUILDER.push("debug");
        DEBUG_LOGGING = BUILDER.comment("Enable debug logging")
                .define("debugLogging", false);
        BUILDER.pop();

        BUILDER.push("general");
        ALLOW_PLACEMENT = BUILDER.comment("Globally allow custom wines to be placed as blocks\nWARNING: Currently broken!")
                .define("universalAllowPlacement", false);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void bake() {
        debugLogging = DEBUG_LOGGING.get();
        globalAllowPlacement = ALLOW_PLACEMENT.get();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading event) {}
}