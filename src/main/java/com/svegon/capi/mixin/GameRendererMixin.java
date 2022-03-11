package com.svegon.capi.mixin;

import com.svegon.capi.util.event.render.FloatingItemRenderListener;
import com.svegon.capi.util.event.render.ScreenRenderListener;
import com.svegon.capi.util.event.render.LoadingOverlayRenderListener;
import com.svegon.capi.util.event.render.WorldRenderListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Locale;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    @Final
    private LightmapTextureManager lightmapTextureManager;

    @Inject(method = "render", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onRender(float tickDelta, long startTime, boolean tick, CallbackInfo callback, int i, int j,
                          Window window, Matrix4f matrix4f, MatrixStack matrixStack, MatrixStack matrixStack2) {

        try {
            ScreenRenderListener.EVENT.invoker().onScreenRender(startTime, tick, matrixStack2, i, j);
        } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.create(throwable, "Rendering GUI event");
            CrashReportSection crashReportSection = crashReport.addElement("GUI eventdetails");
            crashReportSection.add("Mouse location", () -> String.format(Locale.ROOT,
                    "Scaled: (%d, %d). Absolute: (%f, %f)", i, j, this.client.mouse.getX(), this.client.mouse.getY()));
            crashReportSection.add("Screen size", () -> String.format(Locale.ROOT,
                    "Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %f",
                    this.client.getWindow().getScaledWidth(), this.client.getWindow().getScaledHeight(),
                    this.client.getWindow().getFramebufferWidth(), this.client.getWindow().getFramebufferHeight(),
                    this.client.getWindow().getScaleFactor()));
            throw new CrashException(crashReport);
        }
    }

    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;" +
            "render(Lnet/minecraft.client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;" +
            "Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;" +
            "Lnet/minecraft/util/math/Matrix4f)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onRenderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo callback, float f,
                               boolean bl, Camera camera, MatrixStack matrixStack, double d, int i, float g,
                               Vec3f vec3f, float h, Matrix4f matrix4f) {
        WorldRenderListener.EVENT.invoker().onWorldRender(matrices, tickDelta, limitTime, bl, camera,
                this.lightmapTextureManager, matrix4f);
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Drawable;" +
            "render(Lnet/minecraft.client/util/math/MatrixStack;IIF)V"))
    private void renderOverlay(Drawable overlay, MatrixStack var1, int var2, int var3, float var4) {
        CallbackInfo callback = new CallbackInfo("Lnet/minecraft/client/gui/Drawable;" +
                "render(Lnet/minecraft.client/util/math/MatrixStack;IIF)V", true);

        LoadingOverlayRenderListener.EVENT.invoker().onLoadingOverlayRender((Overlay) overlay, var1, var2, var3, var4, callback);

        if (!callback.isCancelled()) {
            overlay.render(var1, var2, var3, var4);
        }
    }

    @Inject(method = "renderFloatingItem", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD,
            target = "Lnet/minecraft/client/render/GameRenderer;floatingItemTimeLeft:I"), cancellable = true)
    private void onRenderFloatingItem(int scaledWidth, int scaledHeight, float tickDelta, CallbackInfo callback) {
        FloatingItemRenderListener.EVENT.invoker().onFloatingItemRender(scaledWidth, scaledHeight, tickDelta, callback);
    }
}
