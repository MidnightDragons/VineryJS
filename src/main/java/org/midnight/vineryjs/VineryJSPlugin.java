package org.midnight.vineryjs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import org.midnight.vineryjs.event.VineryEvents;

public class VineryJSPlugin implements KubeJSPlugin {
    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(VineryEvents.GROUP);
    }
}