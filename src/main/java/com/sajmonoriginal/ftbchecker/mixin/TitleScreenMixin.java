package com.sajmonoriginal.ftbchecker.mixin;

import com.sajmonoriginal.ftbchecker.FTBCheckerClient;
import com.sajmonoriginal.ftbchecker.MissingModsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique
    private static boolean ftbchecker$checked = false;

    @Inject(method = "init", at = @At("TAIL"))
    private void ftbchecker$onInit(CallbackInfo ci) {
        if (!ftbchecker$checked && FTBCheckerClient.areDependenciesMissing()) {
            ftbchecker$checked = true;
            MinecraftClient.getInstance().setScreen(new MissingModsScreen());
        }
    }
}
