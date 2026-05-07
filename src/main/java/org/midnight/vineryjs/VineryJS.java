package org.midnight.vineryjs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.midnight.vineryjs.event.RegisterWineEvent;
import org.midnight.vineryjs.event.VineryEvents;

@Mod(VineryJS.MOD_ID)
public class VineryJS {
    public static final String MOD_ID = "vineryjs";

    public VineryJS(IEventBus modEventBus) {
        modEventBus.addListener(this::loadComplete);
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        VineryEvents.MODIFY_WINE.post(new RegisterWineEvent());
    }
}