package org.midnight.vineryjs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class VineryEvents {
    public static final EventGroup GROUP = EventGroup.of("VineryEvents");
    public static final EventHandler MODIFY_WINE = GROUP.startup("modifyWine", () -> RegisterWineEvent.class);
}