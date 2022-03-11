package com.svegon.capi.util.event.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public interface UnderwaterOverlayRenderListener {
    Event<UnderwaterOverlayRenderListener> EVENT = EventFactory.createArrayBacked(UnderwaterOverlayRenderListener.class,
            (matrices, callback) -> {}, listeners -> (matrices, callback) -> {
        for (UnderwaterOverlayRenderListener listener : listeners) {
            listener.onUnderwaterOverlayRender(matrices, callback);

            if (callback.isCancelled()) {
                return;
            }
        }
    });

    void onUnderwaterOverlayRender(MatrixStack matrices, CallbackInfo callback);
}
