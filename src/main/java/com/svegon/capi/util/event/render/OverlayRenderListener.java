package com.svegon.capi.util.event.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface OverlayRenderListener {
    Event<OverlayRenderListener> EVENT = EventFactory.createArrayBacked(OverlayRenderListener.class,
            (scale, callback) -> {}, listeners -> (scale, callback) -> {
        for (OverlayRenderListener listener : listeners) {
            listener.onSpyglassOverlayRender(scale, callback);

            if (callback.isCancelled()) {
                return;
            }
        }
    });

    void onSpyglassOverlayRender(float scale, CallbackInfo callback);
}
