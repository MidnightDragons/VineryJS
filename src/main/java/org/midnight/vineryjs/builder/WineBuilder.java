package org.midnight.vineryjs.builder;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import dev.latvian.mods.kubejs.block.entity.BlockEntityInfo;
import dev.latvian.mods.kubejs.client.ModelGenerator;
import dev.latvian.mods.kubejs.generator.KubeAssetGenerator;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.ReturnsSelf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.satisfy.vinery.core.item.DrinkBlockItem;

@SuppressWarnings({"unused", "UnusedReturnValue"})
@ReturnsSelf
public class WineBuilder extends BlockBuilder {
    private final BlockEntityInfo info;
    public final String id;
    public final ResourceLocation resourceID;
    public transient ResourceLocation effect;
    public transient int duration;
    public transient int amplifier;
    public transient boolean scaleWithAge;
    public transient DrinkBlockItem.BottleSize bottleSize;

    public WineBuilder(String id) {
        super(ResourceLocation.parse(id));
        this.info = new BlockEntityInfo(this);
        this.id = id;
        this.resourceID = ResourceLocation.parse(id);
        this.effect = null;
        this.duration = 0;
        this.amplifier = 0;
        this.scaleWithAge = false;
        this.bottleSize = DrinkBlockItem.BottleSize.BIG;
    }

    @Info("Sets the effect the wine gives the player when drank.")
    public WineBuilder effect(String effectId, int duration, int amplifier) {
        this.effect = ResourceLocation.parse(effectId);
        this.duration = duration;
        this.amplifier = amplifier;
        return this;
    }

    @Info("Sets if the wine should scale its effects with age.")
    public WineBuilder scaleWithAge(boolean scale) {
        this.scaleWithAge = scale;
        return this;
    }

    @Info("Sets the size of the bottle used.")
    public WineBuilder bottleSize(String size) {
        this.bottleSize = DrinkBlockItem.BottleSize.valueOf(size.toUpperCase());
        return this;
    }

    @Override
    protected void generateItemModel(ModelGenerator m) {
        m.parent(parentModel != null ? parentModel : KubeAssetGenerator.GENERATED_ITEM_MODEL);
    }

    @Override
    @HideFromJS
    public Block createObject() {
        return info.blockBuilder.createObject();
    }
}