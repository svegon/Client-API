package com.svegon.capi.util.event.render;

import com.svegon.capi.util.event.ListenerCollectionFactory;
import com.svegon.capi.util.event.ListenerList;
import com.svegon.capi.util.event.ListenerSet;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

public interface WorldRenderListener {
    Event<WorldRenderListener> EVENT = EventFactory.createArrayBacked(WorldRenderListener.class,
            listeners -> (matrices, tickDelta, startTime, renderBlockOutline, camera, lightmapTextureManager,
                          matrix) -> {
                for (WorldRenderListener listener : listeners) {
                    listener.onWorldRender(matrices, tickDelta, startTime,renderBlockOutline, camera,
                            lightmapTextureManager, matrix);
                }
            });
    ListenerList<WorldRenderListener> LISTENER_LIST = ListenerCollectionFactory.listenersArrayList(
            listeners -> (matrices, tickDelta, startTime, renderBlockOutline, camera, lightmapTextureManager,
                          matrix) -> {
                for (WorldRenderListener listener : listeners) {
                    listener.onWorldRender(matrices, tickDelta, startTime,renderBlockOutline, camera,
                            lightmapTextureManager, matrix);
                }
            });
    ListenerSet<WorldRenderListener> LISTENER_SET = ListenerCollectionFactory.listenersLinkedSet(
            listeners -> (matrices, tickDelta, startTime, renderBlockOutline, camera, lightmapTextureManager,
                          matrix) -> {
                for (WorldRenderListener listener : listeners) {
                    listener.onWorldRender(matrices, tickDelta, startTime,renderBlockOutline, camera,
                            lightmapTextureManager, matrix);
                }
            });

    void onWorldRender(MatrixStack matrices, float tickDelta, long startTime, boolean renderBlockOutline, Camera camera,
                       LightmapTextureManager lightmapTextureManager, Matrix4f matrix);
}
