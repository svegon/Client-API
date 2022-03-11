package com.svegon.capi.mixin;

import com.svegon.capi.util.event.network.S2CPlayPacketListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
    @Shadow
    private PacketListener packetListener;

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;handlePacket(" +
                    "Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;)V"))
    @SuppressWarnings("unchecked")
    private void onChannelRead0(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        try {
            if (packetListener instanceof ClientPlayPacketListener) {
                S2CPlayPacketListener.CLIENT_PACKET_RECEIVED_EVENT.invoker()
                        .apply((Packet<ClientPlayPacketListener>) packet, info);
            }
        } catch (ClassCastException ignored) {

        }
    }
}
