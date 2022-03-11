package com.svegon.capi.mixin;

import com.svegon.capi.util.event.network.C2SPlayPacketListener;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ServerPlayPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    @SuppressWarnings("unchecked")
    public void onSendPacket(Packet<?> packet, CallbackInfo info) {
        try {
            C2SPlayPacketListener.CLIENT_PACKET_SENT_EVENT.invoker().apply((Packet<ServerPlayPacketListener>) packet,
                    info);
        } catch (ClassCastException ignored) {

        }
    }
}
