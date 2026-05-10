package org.midnight.vineryjs.builder;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.vinery.core.item.DrinkBlockItem;

public class WineDrinkItem extends DrinkBlockItem {
    public WineDrinkItem(Block block, Properties settings, boolean scaleDurationWithAge, BottleSize bottleSize) {
        super(block, settings, scaleDurationWithAge, bottleSize);
    }

    // Temporary while I find a way to fix placing wine bottles
    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        return null;
    }
}