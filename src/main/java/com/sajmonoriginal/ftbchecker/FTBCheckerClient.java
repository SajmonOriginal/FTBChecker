package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import static com.sajmonoriginal.ftbchecker.FTBChecker.CONFIG;
import static com.sajmonoriginal.ftbchecker.FTBChecker.IGNORED;

@Mod(value = FTBChecker.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = FTBChecker.MOD_ID, value = Dist.CLIENT)
public class FTBCheckerClient {
    public FTBCheckerClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (client, parent) ->
                AutoConfig.getConfigScreen(ModConfig.class, parent).get());
    }

    @SubscribeEvent
    static void onInitScreenEventPost(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof TitleScreen && areDependenciesMissing() && !IGNORED) {
            Minecraft.getInstance().setScreen(new MissingModsScreen());
        }
    }

    private static boolean areDependenciesMissing() {
        return (!ModList.get().isLoaded("ftbchunks") && CONFIG.chunks)
                || (!ModList.get().isLoaded("ftblibrary") && CONFIG.base)
                || (!ModList.get().isLoaded("ftbquests") && CONFIG.quests)
                || (!ModList.get().isLoaded("ftbteams") && CONFIG.teams)
                || (!ModList.get().isLoaded("ftbessentials") && CONFIG.essentials)
                || (!ModList.get().isLoaded("ftbultimine") && CONFIG.ultimine)
                || (!ModList.get().isLoaded("ftbbackups2") && CONFIG.backups)
                || (!ModList.get().isLoaded("polylib") && CONFIG.polyLib);
    }
}
