package org.midnight.vineryjs.builder;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import dev.latvian.mods.rhino.util.ReturnsSelf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.satisfy.vinery.core.item.DrinkBlockItem;

@SuppressWarnings({"unused", "UnusedReturnValue"})
@ReturnsSelf
public class WineBuilder extends BlockBuilder {
    public transient ResourceLocation effect;
    public transient int duration;
    public transient int amplifier;
    public transient boolean scaleWithAge;
    public transient DrinkBlockItem.BottleSize bottleSize;

    public WineBuilder(ResourceLocation id) {
        super(id);
        this.effect = null;
        this.duration = 0;
        this.amplifier = 0;
        this.scaleWithAge = false;
        this.bottleSize = DrinkBlockItem.BottleSize.SMALL;
    }

    @Override
    public Block createObject() {
        return new Block(createProperties());
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