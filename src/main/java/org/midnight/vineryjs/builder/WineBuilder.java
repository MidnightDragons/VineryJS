package org.midnight.vineryjs.builder;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.vinery.core.item.DrinkBlockItem;

public class WineBuilder extends ItemBuilder {
    public ResourceLocation effect = null;
    public int duration = 0;
    public int amplifier = 0;
    public boolean scaleWithAge = false;
    public DrinkBlockItem.BottleSize bottleSize = DrinkBlockItem.BottleSize.BIG;

    public WineBuilder(ResourceLocation id) {
        super(id);
        this.duration = 0;
        this.amplifier = 0;
        this.scaleWithAge = false;
        this.bottleSize = DrinkBlockItem.BottleSize.SMALL;
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