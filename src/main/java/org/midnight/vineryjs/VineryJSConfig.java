package org.midnight.vineryjs;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class VineryJSConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ALLOW_PLACEMENT;

    static {
        BUILDER.push("general");
        ALLOW_PLACEMENT = BUILDER.comment("Allow custom wines to be placed as blocks")
                .define("allowPlacement", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading event) {}
}