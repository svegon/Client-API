package com.svegon.capi.mixin;

import com.svegon.capi.util.event.render.RenderListener;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;" +
            "setPhase(Ljava/lang/String;)V", ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
    private void onRender(boolean tick, CallbackInfo info) {
        RenderListener.EVENT.invoker().onRender(tick, info);
    }
}
