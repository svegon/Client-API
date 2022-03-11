package com.svegon.capi.util.event.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface LoadingOverlayRenderListener {
    Event<LoadingOverlayRenderListener> EVENT = EventFactory.createArrayBacked(LoadingOverlayRenderListener.class,
            (overlay, matrices, mouseX, mouseY, lastFrameDuration, callback) -> {},
            listeners -> (overlay, matrices, mouseX, mouseY, lastFrameDuration, callback) -> {
        for (LoadingOverlayRenderListener listener : listeners) {
            listener.onLoadingOverlayRender(overlay, matrices, mouseX, mouseY, lastFrameDuration, callback);

            if (callback.isCancelled()) {
                return;
            }
        }
    });

    void onLoadingOverlayRender(Overlay overlay, MatrixStack matrices, int mouseX, int mouseY, float lastFrameDuration,
                                CallbackInfo callback);
}
