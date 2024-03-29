package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.client.event.ScreenEvent;

@Mod(FTBChecker.MOD_ID)
public class FTBChecker {
    public static final String MOD_ID = "ftbchecker";

    public static ModConfig CONFIG;
    public static boolean IGNORED = false;

    public FTBChecker() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);

        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,

                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get()
                )
        );
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public void onInitScreenEventPost(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof TitleScreen && areDependenciesMissing() && !IGNORED) {
            Minecraft.getInstance().setScreen(new MissingModsScreen());
        }
    }

    private boolean areDependenciesMissing() {
        return (!ModList.get().isLoaded("ftbchunks") && CONFIG.chunks)
                || (!ModList.get().isLoaded("ftblibrary") && CONFIG.base)
                || (!ModList.get().isLoaded("ftbquests") && CONFIG.quests)
                || (!ModList.get().isLoaded("ftbteams") && CONFIG.teams)
                || (!ModList.get().isLoaded("ftbessentials") && CONFIG.essentials)
                || (!ModList.get().isLoaded("ftbultimine") && CONFIG.ultimine)
                || (!ModList.get().isLoaded("ftbbackups2") && CONFIG.backups)
                || (!ModList.get().isLoaded("polylib") && CONFIG.polyLib)
                || (!ModList.get().isLoaded("ftbxmodcompat") && CONFIG.xmod)
                || (!ModList.get().isLoaded("itemfilters") && CONFIG.filters);
    }
}
