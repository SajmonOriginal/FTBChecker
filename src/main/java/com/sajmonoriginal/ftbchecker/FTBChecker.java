package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.neoforged.fml.common.Mod;

@Mod(FTBChecker.MOD_ID)
public class FTBChecker {
    public static final String MOD_ID = "ftbchecker";

    public static ModConfig CONFIG;
    public static boolean IGNORED = false;

    public FTBChecker() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
