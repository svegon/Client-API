package com.svegon.capi.util.event.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface SpyglassOverlayRenderListener {
    Event<SpyglassOverlayRenderListener> EVENT = EventFactory.createArrayBacked(SpyglassOverlayRenderListener.class,
            (scale, callback) -> {}, listeners -> (scale, callback) -> {
        for (SpyglassOverlayRenderListener listener : listeners) {
            listener.onSpyglassOverlayRender(scale, callback);

            if (callback.isCancelled()) {
                return;
            }
        }
    });

    void onSpyglassOverlayRender(float scale, CallbackInfo callback);
}
