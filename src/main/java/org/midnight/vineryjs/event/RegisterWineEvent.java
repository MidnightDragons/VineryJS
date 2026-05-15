package org.midnight.vineryjs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import org.midnight.vineryjs.VineryJS;
import org.midnight.vineryjs.builder.WineBuilder;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RegisterWineEvent implements KubeEvent {
    public static RegisterWineEvent LAST;
    public final List<WineBuilder> builders = new ArrayList<>();

    public RegisterWineEvent() {
        LAST = this;
        VineryJS.log("RegisterWineEvent created, LAST updated");
    }

    public WineBuilder create(String id) {
        WineBuilder builder = new WineBuilder(id);
        builders.add(builder);
        VineryJS.log("create() called.\n  id: " + id + "\n  displayName: " + builder.displayName);
        return builder;
    }
}