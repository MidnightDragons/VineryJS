package org.midnight.vineryjs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.midnight.vineryjs.duck.DrinkBlockItemDuck;

public class ModifyWineEvent implements KubeEvent {
    public void modify(String itemId, String effectId, int duration, int amplifier, boolean scaleWithAge) {
        ResourceLocation itemRL = ResourceLocation.parse(itemId);
        ResourceLocation effectRL = ResourceLocation.parse(effectId);

        DrinkBlockItem item = (DrinkBlockItem) BuiltInRegistries.ITEM.get(itemRL);
        Holder<MobEffect> effectHolder = BuiltInRegistries.MOB_EFFECT.getHolder(effectRL).orElseThrow();

        item.setEffectSupplier(() -> effectHolder, duration, amplifier);
        ((DrinkBlockItemDuck) item).setScaleDurationWithAge(scaleWithAge);
    }
}