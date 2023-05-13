package com.example.dependencycheckermod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.client.event.ScreenEvent;

@Mod(DependencyCheckerMod.MOD_ID)
public class DependencyCheckerMod {
    public static final String MOD_ID = "dependencycheckermod";

    public DependencyCheckerMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public void onInitScreenEventPost(ScreenEvent.InitScreenEvent.Post event) {
        if (event.getScreen() instanceof TitleScreen && areDependenciesMissing()) {
            Minecraft.getInstance().setScreen(new MissingModsScreen());
        }
    }

    private boolean areDependenciesMissing() {
        return !net.minecraftforge.fml.ModList.get().isLoaded("ftbchunks")
                || !net.minecraftforge.fml.ModList.get().isLoaded("ftblibrary")
                || !net.minecraftforge.fml.ModList.get().isLoaded("ftbquests")
                || !net.minecraftforge.fml.ModList.get().isLoaded("ftbteams");
    }
}
