package org.midnight.vineryjs.mixin;

import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.midnight.vineryjs.duck.DrinkBlockItemDuck;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DrinkBlockItem.class)
public class DrinkBlockItemMixin implements DrinkBlockItemDuck {
    @Final
    @Mutable
    @Shadow(remap = false)
    private boolean scaleDurationWithAge;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public void setScaleDurationWithAge(boolean scale) {
        this.scaleDurationWithAge = scale;
    }
}