package org.midnight.vineryjs;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.satisfy.vinery.core.block.WineBottleBlock;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.midnight.vineryjs.builder.WineBuilder;
import org.midnight.vineryjs.event.ModifyWineEvent;
import org.midnight.vineryjs.event.RegisterWineEvent;
import org.midnight.vineryjs.event.VineryEvents;

import java.util.ArrayList;
import java.util.List;

@Mod(VineryJS.MOD_ID)
public class VineryJS {
    public static final String MOD_ID = "vineryjs";
    private static final List<WineBuilder> pendingWines = new ArrayList<>();

    public VineryJS(IEventBus modEventBus) {
        modEventBus.addListener(this::onRegisterBlocks);
        modEventBus.addListener(this::onRegisterItems);
        modEventBus.addListener(this::loadComplete);
    }

    private void onRegisterBlocks(RegisterEvent event) {
        if (!event.getRegistryKey().equals(net.minecraft.core.registries.Registries.BLOCK)) return;

        RegisterWineEvent wineEvent = new RegisterWineEvent();
        VineryEvents.REGISTER_WINE.post(wineEvent);
        pendingWines.addAll(wineEvent.builders);

        for (WineBuilder builder : pendingWines) {
            Block block = new WineBottleBlock(
                    Block.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.GLASS)
                            .noOcclusion()
                            .instabreak(),
                    2
            );

            event.register(net.minecraft.core.registries.Registries.BLOCK, builder.id, () -> block);
        }
    }

    private void onRegisterItems(RegisterEvent event) {
        if (!event.getRegistryKey().equals(net.minecraft.core.registries.Registries.ITEM)) return;

        for (WineBuilder builder : pendingWines) {
            Block block = BuiltInRegistries.BLOCK.get(builder.id);
            DrinkBlockItem item = new DrinkBlockItem(
                    block,
                    new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()),
                    builder.scaleWithAge,
                    builder.bottleSize
            );

            if (builder.effect != null) {
                Holder<MobEffect> effectHolder = BuiltInRegistries.MOB_EFFECT.getHolder(builder.effect).orElseThrow();
                item.setEffectSupplier(() -> effectHolder, builder.duration, builder.amplifier);
            }

            event.register(net.minecraft.core.registries.Registries.ITEM, builder.id, () -> item);
        }
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        VineryEvents.MODIFY_WINE.post(new ModifyWineEvent());
    }
}