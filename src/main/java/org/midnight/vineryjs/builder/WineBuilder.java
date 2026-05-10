package org.midnight.vineryjs.builder;

import dev.latvian.mods.kubejs.block.BlockBuilder;
import dev.latvian.mods.kubejs.block.entity.BlockEntityInfo;
import dev.latvian.mods.kubejs.client.ModelGenerator;
import dev.latvian.mods.kubejs.generator.KubeAssetGenerator;
import dev.latvian.mods.kubejs.plugin.builtin.wrapper.StringUtilsWrapper;
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
    public final ResourceLocation resourceID;
    public transient String displayName;
    public transient String itemTexture;
    public transient String blockTexture;
    public transient ResourceLocation itemParentModel;
    public transient ResourceLocation blockParentModel;
    public transient ResourceLocation effect;
    public transient int duration;
    public transient int amplifier;
    public transient boolean scaleWithAge;
    public transient DrinkBlockItem.BottleSize bottleSize;
    public transient boolean allowPlacement;

    // Constructor with defaults
    public WineBuilder(String id) {
        super(ResourceLocation.parse(id));
        this.info = new BlockEntityInfo(this);
        this.resourceID = ResourceLocation.parse(id);
        this.displayName = StringUtilsWrapper.snakeCaseToTitleCase(resourceID.getPath());
        this.itemTexture = resourceID.getNamespace() + "item/" + resourceID.getPath();
        this.blockTexture = resourceID.getNamespace() + "block/" + resourceID.getPath();
        this.itemParentModel = KubeAssetGenerator.GENERATED_ITEM_MODEL;
        this.blockParentModel = ResourceLocation.parse("vinery:block/mellohi_wine");
        this.effect = ResourceLocation.parse("minecraft:speed");
        this.duration = 60;
        this.amplifier = 0;
        this.scaleWithAge = true;
        this.bottleSize = DrinkBlockItem.BottleSize.SMALL;
        this.allowPlacement = false;
    }

    @Info("Sets the display name of the wine.")
    public WineBuilder displayName(String name) {
        displayName = name;
        return this;
    }

    @Info("Sets the item texture of the wine.")
    public WineBuilder itemTexture(String tex) {
        itemTexture = tex;
        return this;
    }

    @Info("Sets the block texture of the wine.")
    public WineBuilder blockTexture(String tex) {
        blockTexture = tex;
        return this;
    }

    @Info("Sets the item model of the wine.")
    public WineBuilder itemParentModel(String model) {
        this.itemParentModel = ResourceLocation.parse(model);
        return this;
    }

    @Info("Sets the block model of the wine.")
    public WineBuilder blockParentModel(String model) {
        this.blockParentModel = ResourceLocation.parse(model);
        return this;
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

    @Info("Can the bottle be placed on the ground?")
    public WineBuilder allowPlacement(boolean allow) {
        this.allowPlacement = allow;
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