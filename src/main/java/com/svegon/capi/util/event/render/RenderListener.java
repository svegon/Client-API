package com.svegon.capi.util.event.render;

import com.svegon.capi.util.event.ListenerCollectionFactory;
import com.svegon.capi.util.event.ListenerList;
import com.svegon.capi.util.event.ListenerSet;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface RenderListener {
    Event<RenderListener> EVENT = EventFactory.createArrayBacked(RenderListener.class,
            listeners -> (tick, callback) -> {
        for (RenderListener listener : listeners) {
            listener.onRender(tick, callback);

            if (callback.isCancelled()) {
                return;
            }
        }
    });
    ListenerList<RenderListener> LISTENER_LIST = ListenerCollectionFactory.listenersArrayList(
            (l) -> (tick, callback) -> {
                for (RenderListener listener : l) {
                    listener.onRender(tick, callback);

                    if (callback.isCancelled()) {
                        return;
                    }
                }
            });
    ListenerSet<RenderListener> LISTENER_SET = ListenerCollectionFactory.listenersLinkedSet(
            (s) -> (tick, callback) -> {
                for (RenderListener listener : s) {
                    listener.onRender(tick, callback);

                    if (callback.isCancelled()) {
                        return;
                    }
                }
            });

    void onRender(boolean tick, CallbackInfo callback);
}
