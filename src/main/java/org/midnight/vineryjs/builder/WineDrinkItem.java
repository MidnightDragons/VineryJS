package org.midnight.vineryjs.builder;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.midnight.vineryjs.VineryJSConfig;

public class WineDrinkItem extends DrinkBlockItem {
    private final boolean allowPlacement;

    public WineDrinkItem(Block block, Properties settings, boolean scaleDurationWithAge, BottleSize bottleSize, boolean allowPlacement) {
        super(block, settings, scaleDurationWithAge, bottleSize);
        this.allowPlacement = allowPlacement;
    }

    // Temporary while I find a way to fix placing wine bottles:
    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        if (VineryJSConfig.globalAllowPlacement && allowPlacement) { return super.getPlacementState(context); }
        return null;
    }
}