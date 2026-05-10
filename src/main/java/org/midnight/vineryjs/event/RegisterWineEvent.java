package org.midnight.vineryjs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.resources.ResourceLocation;
import org.midnight.vineryjs.builder.WineBuilder;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RegisterWineEvent implements KubeEvent {
    public static final List<WineBuilder> COLLECTED = new ArrayList<>();
    public final List<WineBuilder> builders = new ArrayList<>();

    public WineBuilder create(String id) {
        System.out.println("[VineryJS] create() called with id: " + id);
        WineBuilder builder = new WineBuilder(id);
        builders.add(builder);
        COLLECTED.add(builder);
        return builder;
    }
}