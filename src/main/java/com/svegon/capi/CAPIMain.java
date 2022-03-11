package com.svegon.capi;

import com.svegon.capi.util.event.network.C2SPlayPacketListener;
import com.svegon.capi.util.event.network.S2CPlayPacketListener;
import com.svegon.capi.util.event.render.ScreenRenderListener;
import com.svegon.capi.util.event.render.RenderListener;
import com.svegon.capi.util.event.render.WorldRenderListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

public final class CAPIMain implements ClientModInitializer, DedicatedServerModInitializer, ModInitializer {
    @Override
    public void onInitializeClient() {

    }

    @Override
    public void onInitializeServer() {

    }

    @Override
    public void onInitialize() {
        // static initialization which couldn't be done in interfaces
        C2SPlayPacketListener.CLIENT_PACKET_SENT_EVENT.register(C2SPlayPacketListener.LISTENER_LIST.invoker());
        C2SPlayPacketListener.CLIENT_PACKET_SENT_EVENT.register(C2SPlayPacketListener.LISTENER_SET.invoker());
        S2CPlayPacketListener.CLIENT_PACKET_RECEIVED_EVENT.register(S2CPlayPacketListener.LISTENER_LIST.invoker());
        S2CPlayPacketListener.CLIENT_PACKET_RECEIVED_EVENT.register(S2CPlayPacketListener.LISTENER_SET.invoker());

        RenderListener.EVENT.register(RenderListener.LISTENER_LIST.invoker());
        RenderListener.EVENT.register(RenderListener.LISTENER_SET.invoker());
        ScreenRenderListener.EVENT.register(ScreenRenderListener.LISTENER_LIST.invoker());
        ScreenRenderListener.EVENT.register(ScreenRenderListener.LISTENER_SET.invoker());
        WorldRenderListener.EVENT.register(WorldRenderListener.LISTENER_LIST.invoker());
        WorldRenderListener.EVENT.register(WorldRenderListener.LISTENER_SET.invoker());
    }
}
