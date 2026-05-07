package org.midnight.vineryjs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class VineryEvents {
    public static final EventGroup GROUP = EventGroup.of("VineryJS");
    public static final EventHandler MODIFY_WINE = GROUP.startup("modifyWine", () -> ModifyWineEvent.class);
    public static final EventHandler REGISTER_WINE = GROUP.startup("registerWine", () -> RegisterWineEvent.class);
}