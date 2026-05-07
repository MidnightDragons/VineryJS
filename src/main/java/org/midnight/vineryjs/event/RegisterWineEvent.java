package org.midnight.vineryjs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.resources.ResourceLocation;
import org.midnight.vineryjs.builder.WineBuilder;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RegisterWineEvent implements KubeEvent {
    public final List<WineBuilder> builders = new ArrayList<>();

    public WineBuilder create(String id) {
        WineBuilder builder = new WineBuilder(ResourceLocation.parse(id));
        builders.add(builder);
        return builder;
    }
}