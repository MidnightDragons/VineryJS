package org.midnight.vineryjs.builder;

import net.minecraft.resources.ResourceLocation;
import net.satisfy.vinery.core.item.DrinkBlockItem;

public class WineBuilder {
    public final ResourceLocation id;
    public ResourceLocation effect = null;
    public int duration = 0;
    public int amplifier = 0;
    public boolean scaleWithAge = false;
    public DrinkBlockItem.BottleSize bottleSize = DrinkBlockItem.BottleSize.BIG;

    public WineBuilder(ResourceLocation id) {
        this.id = id;
    }

    public WineBuilder effect(String effectId, int duration, int amplifier) {
        this.effect = ResourceLocation.parse(effectId);
        this.duration = duration;
        this.amplifier = amplifier;
        return this;
    }

    public WineBuilder scaleWithAge(boolean scale) {
        this.scaleWithAge = scale;
        return this;
    }

    public WineBuilder bottleSize(String size) {
        this.bottleSize = DrinkBlockItem.BottleSize.valueOf(size.toUpperCase());
        return this;
    }
}