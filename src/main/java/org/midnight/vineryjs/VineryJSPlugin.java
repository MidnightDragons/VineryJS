package org.midnight.vineryjs;

import dev.latvian.mods.kubejs.client.LangKubeEvent;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.generator.KubeAssetGenerator;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import net.minecraft.resources.ResourceLocation;
import org.midnight.vineryjs.builder.WineBuilder;
import org.midnight.vineryjs.event.RegisterWineEvent;
import org.midnight.vineryjs.event.VineryEvents;

public class VineryJSPlugin implements KubeJSPlugin {
    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(VineryEvents.GROUP);
    }

    @Override
    public void generateAssets(KubeAssetGenerator generator) {
        for (var builder : RegisterWineEvent.COLLECTED) {
            generator.itemModel(builder.resourceID, m -> {
                m.parent(builder.itemParentModel);
                m.texture("layer0", builder.itemTexture);
            });

            generator.blockModel(builder.resourceID, m -> {
                m.parent(builder.blockParentModel);
                m.texture("5", builder.blockTexture);
                m.texture("particle", builder.blockTexture);
            });

            // noinspection CodeBlock2Expr
            generator.blockState(builder.resourceID, b -> {
                b.simpleVariant("", ResourceLocation.parse("vineryjs:block/" + builder.id.getPath()));
            });
        }
    }

    private String getItemLangID(WineBuilder builder, String type) {
        return type + "." + builder.resourceID.getNamespace() + "." + builder.resourceID.getPath();
    }

    @Override
    public void generateLang(LangKubeEvent lang) {
        for (var builder : RegisterWineEvent.COLLECTED) {
            lang.add(builder.resourceID.getNamespace(), getItemLangID(builder, "item"), builder.displayName);
            lang.add(builder.resourceID.getNamespace(), getItemLangID(builder, "block"), builder.displayName);
        }
    }
}