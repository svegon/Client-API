package com.svegon.capi.util.event.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface VignetteOverlayRenderListener {
    Event<VignetteOverlayRenderListener> EVENT = EventFactory.createArrayBacked(VignetteOverlayRenderListener.class,
            (entity, callback) -> {}, listeners -> (entity, callback) -> {
        for (VignetteOverlayRenderListener listener : listeners) {
            listener.onVignetteOverlayRender(entity, callback);

            if (callback.isCancelled()) {
                return;
            }
        }
    });

    void onVignetteOverlayRender(Entity entity, CallbackInfo callback);
}
