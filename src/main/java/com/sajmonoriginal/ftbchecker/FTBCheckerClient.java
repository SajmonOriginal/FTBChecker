package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class FTBCheckerClient implements ClientModInitializer {
    public static final String MOD_ID = "ftbchecker";
    public static ModConfig CONFIG;

    @Override
    public void onInitializeClient(ModContainer mod) {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static boolean areDependenciesMissing() {
        if ((CONFIG.architectury && !QuiltLoader.isModLoaded("architectury"))
                || (CONFIG.chunks && !QuiltLoader.isModLoaded("ftbchunks"))
                || (CONFIG.base && !QuiltLoader.isModLoaded("ftblibrary"))
                || (CONFIG.quests && !QuiltLoader.isModLoaded("ftbquests"))
                || (CONFIG.teams && !QuiltLoader.isModLoaded("ftbteams"))
                || (CONFIG.ultimine && !QuiltLoader.isModLoaded("ftbultimine"))
                || (CONFIG.backups && !QuiltLoader.isModLoaded("ftbbackups2"))
                || (CONFIG.polyLib && !QuiltLoader.isModLoaded("polylib"))) {
            return true;
        }

        for (String modEntry : CONFIG.customMods.mods) {
            String[] parts = modEntry.split("\\|", 3);
            if (parts.length >= 1) {
                String modId = parts[0].trim();
                if (!modId.isEmpty() && !QuiltLoader.isModLoaded(modId)) {
                    return true;
                }
            }
        }

        return false;
    }
}
