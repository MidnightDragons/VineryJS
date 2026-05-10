package org.midnight.vineryjs;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.satisfy.vinery.core.block.WineBottleBlock;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.midnight.vineryjs.builder.WineBuilder;
import org.midnight.vineryjs.builder.WineDrinkItem;
import org.midnight.vineryjs.event.ModifyWineEvent;
import org.midnight.vineryjs.event.RegisterWineEvent;
import org.midnight.vineryjs.event.VineryEvents;

import java.util.ArrayList;
import java.util.List;

@Mod(VineryJS.MOD_ID)
public class VineryJS {
    public static final String MOD_ID = "vineryjs";
    private static final List<WineBuilder> pendingWines = new ArrayList<>();

    public VineryJS(IEventBus modEventBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, VineryJSConfig.SPEC);
        modEventBus.addListener((ModConfigEvent e) -> VineryJSConfig.bake());
        modEventBus.addListener(this::onRegisterBlocks);
        modEventBus.addListener(this::onRegisterItems);
        modEventBus.addListener(this::loadComplete);
    }

    public static void log(String message) {
        if (VineryJSConfig.debugLogging) {
            System.out.println("[VineryJS] " + message);
        }
    }

    private static BlockBehaviour.Properties getWineSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).noOcclusion().instabreak();
    }

    private void onRegisterBlocks(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.BLOCK)) return;

        RegisterWineEvent wineEvent = new RegisterWineEvent();
        VineryEvents.REGISTER_WINE.post(wineEvent);
        pendingWines.addAll(wineEvent.builders);

        for (WineBuilder builder : pendingWines) {
            Block block = new WineBottleBlock(
                    getWineSettings(),
                    2
            );

            event.register(Registries.BLOCK, builder.resourceID, () -> block);
        }
    }

    private void onRegisterItems(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.ITEM)) return;

        for (WineBuilder builder : pendingWines) {
            Block block = BuiltInRegistries.BLOCK.get(builder.resourceID);
            DrinkBlockItem ITEM = new WineDrinkItem(
                    block,
                    new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()),
                    builder.scaleWithAge,
                    builder.bottleSize,
                    builder.allowPlacement
            );

            if (builder.effect != null) {
                Holder<MobEffect> effectHolder = BuiltInRegistries.MOB_EFFECT.getHolder(builder.effect).orElseThrow();
                ITEM.setEffectSupplier(() -> effectHolder, builder.duration, builder.amplifier);
            }

            VineryJS.log(builder + " created with id: " + builder.resourceID.getPath());

            event.register(Registries.ITEM, builder.resourceID, () -> ITEM);
        }
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        VineryEvents.MODIFY_WINE.post(new ModifyWineEvent());
    }
}