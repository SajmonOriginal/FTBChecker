package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;

public class FTBCheckerClient implements ClientModInitializer {
    public static final String MOD_ID = "ftbchecker";
    public static ModConfig CONFIG;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof TitleScreen && areDependenciesMissing()) {
                MinecraftClient.getInstance().setScreen(new MissingModsScreen());
            }
        });
    }

    private boolean areDependenciesMissing() {
        if ((CONFIG.architectury && !FabricLoader.getInstance().isModLoaded("architectury"))
                || (CONFIG.chunks && !FabricLoader.getInstance().isModLoaded("ftbchunks"))
                || (CONFIG.base && !FabricLoader.getInstance().isModLoaded("ftblibrary"))
                || (CONFIG.quests && !FabricLoader.getInstance().isModLoaded("ftbquests"))
                || (CONFIG.teams && !FabricLoader.getInstance().isModLoaded("ftbteams"))
                || (CONFIG.ultimine && !FabricLoader.getInstance().isModLoaded("ftbultimine"))
                || (CONFIG.backups && !FabricLoader.getInstance().isModLoaded("ftbbackups2"))
                || (CONFIG.polyLib && !FabricLoader.getInstance().isModLoaded("polylib"))) {
            return true;
        }

        for (String modEntry : CONFIG.customMods.mods) {
            String[] parts = modEntry.split("\\|", 3);
            if (parts.length >= 1) {
                String modId = parts[0].trim();
                if (!modId.isEmpty() && !FabricLoader.getInstance().isModLoaded(modId)) {
                    return true;
                }
            }
        }

        return false;
    }
}
