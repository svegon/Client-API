package com.svegon.capi.util.event.render;

import com.svegon.capi.util.event.ListenerCollectionFactory;
import com.svegon.capi.util.event.ListenerList;
import com.svegon.capi.util.event.ListenerSet;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.util.math.MatrixStack;

public interface ScreenRenderListener {
    Event<ScreenRenderListener> EVENT = EventFactory.createArrayBacked(ScreenRenderListener.class,
            listeners -> (startTime, tick, matrixStack, mouseX, mouseY) -> {
                for (ScreenRenderListener listener : listeners) {
                    listener.onScreenRender(startTime, tick, matrixStack, mouseX, mouseY);
                }
            });
    ListenerList<ScreenRenderListener> LISTENER_LIST = ListenerCollectionFactory.listenersArrayList(
            (listeners) -> (startTime, tick, matrixStack, mouseX, mouseY) -> {
                for (ScreenRenderListener listener : listeners) {
                    listener.onScreenRender(startTime, tick, matrixStack, mouseX, mouseY);
                }
            });
    ListenerSet<ScreenRenderListener> LISTENER_SET = ListenerCollectionFactory.listenersLinkedSet(
            (listeners) -> (startTime, tick, matrixStack, mouseX, mouseY) -> {
                for (ScreenRenderListener listener : listeners) {
                    listener.onScreenRender(startTime, tick, matrixStack, mouseX, mouseY);
                }
            });

    void onScreenRender(long startTime, boolean tick, MatrixStack matrices, int mouseX, int mouseY);
}
