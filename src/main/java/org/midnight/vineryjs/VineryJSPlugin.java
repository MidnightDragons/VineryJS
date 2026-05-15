package org.midnight.vineryjs;

import dev.latvian.mods.kubejs.client.LangKubeEvent;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.generator.KubeAssetGenerator;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
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
        if (RegisterWineEvent.LAST == null) return;

        for (var builder : RegisterWineEvent.LAST.builders) {
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
                b.simpleVariant("", builder.blockParentModel);
            });
        }
    }

    private String getItemLangID(WineBuilder builder, String type) {
        return type + "." + builder.resourceID.getNamespace() + "." + builder.resourceID.getPath();
    }

    @Override
    public void generateLang(LangKubeEvent lang) {
        if (RegisterWineEvent.LAST == null) {
            VineryJS.log("GenerateLang called but LAST is null!");
            return;
        }

        VineryJS.log("GenerateLang called, builders: " + RegisterWineEvent.LAST.builders.size());

        for (var builder : RegisterWineEvent.LAST.builders) {
            VineryJS.log("Generating lang for " + builder + ".");
            VineryJS.log("  Namespace: " + builder.resourceID.getNamespace());
            VineryJS.log("  Key: " + getItemLangID(builder, "item"));
            VineryJS.log("  Value: " + builder.displayName);
            lang.add(builder.resourceID.getNamespace(), getItemLangID(builder, "item"), builder.displayName);
            lang.add(builder.resourceID.getNamespace(), getItemLangID(builder, "block"), builder.displayName);
        }

        lang.add("vineryjs", "itemGroup.vineryjs.tab", "VineryJS");
    }
}