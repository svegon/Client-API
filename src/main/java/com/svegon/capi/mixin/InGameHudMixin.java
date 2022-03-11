package com.svegon.capi.mixin;

import com.svegon.capi.util.event.render.SpyglassOverlayRenderListener;
import com.svegon.capi.util.event.render.VignetteOverlayRenderListener;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    @Inject(method = "renderVignetteOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderVignetteOverlay(Entity entity, CallbackInfo callback) {
        VignetteOverlayRenderListener.EVENT.invoker().onVignetteOverlayRender(entity, callback);
    }

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderOverlay(Identifier texture, float opacity, CallbackInfo callback) {

    }

    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderSpyglassOverlay(float scale, CallbackInfo callback) {
        SpyglassOverlayRenderListener.EVENT.invoker().onSpyglassOverlayRender(scale, callback);
    }
}
