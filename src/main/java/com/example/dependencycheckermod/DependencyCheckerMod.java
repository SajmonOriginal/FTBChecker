package com.example.dependencycheckermod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DependencyCheckerMod.MOD_ID)
public class DependencyCheckerMod {
    public static final String MOD_ID = "dependencycheckermod";

    public DependencyCheckerMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        Minecraft.getInstance().execute(() -> {
            if (areDependenciesMissing()) {
                Screen missingModsScreen = new MissingModsScreen();
                Minecraft.getInstance().setScreen(missingModsScreen);
            }
        });
    }

    private boolean areDependenciesMissing() {
        boolean missingMods = !ModList.get().isLoaded("ftbchunks")
                || !ModList.get().isLoaded("ftb-library")
                || !ModList.get().isLoaded("ftb-quests");

        return missingMods;
    }
}
