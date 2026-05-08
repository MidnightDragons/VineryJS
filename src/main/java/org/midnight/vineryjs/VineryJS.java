package org.midnight.vineryjs;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.satisfy.vinery.core.block.WineBottleBlock;
import net.satisfy.vinery.core.block.entity.StorageBlockEntity;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.midnight.vineryjs.event.ModifyWineEvent;
import org.midnight.vineryjs.event.RegisterWineEvent;
import org.midnight.vineryjs.event.VineryEvents;

@Mod(VineryJS.MOD_ID)
public class VineryJS {
    public static final String MOD_ID = "vineryjs";

    public VineryJS(IEventBus modEventBus) {
        modEventBus.addListener(this::loadComplete);
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        System.out.println("[VineryJS] loadComplete fired, COLLECTED size: " + RegisterWineEvent.COLLECTED.size());

        // Register blocks and items dynamically here instead
        for (var builder : RegisterWineEvent.COLLECTED) {
            // register using BuiltInRegistries directly
            net.minecraft.core.Registry.register(BuiltInRegistries.BLOCK, builder.id,
                    new WineBottleBlock(
                            Block.Properties.ofFullCopy(Blocks.GLASS).noOcclusion().instabreak(), 2
                    )
            );
            net.minecraft.core.Registry.register(BuiltInRegistries.ITEM, builder.id,
                    new DrinkBlockItem(BuiltInRegistries.BLOCK.get(builder.id),
                            new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()),
                            builder.scaleWithAge, builder.bottleSize
                    )
            );
        }

        VineryEvents.MODIFY_WINE.post(new ModifyWineEvent());
    }
}