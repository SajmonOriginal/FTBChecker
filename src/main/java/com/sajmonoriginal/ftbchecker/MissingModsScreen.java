package com.sajmonoriginal.ftbchecker;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MissingModsScreen extends Screen {
    private final Component titleText = Component.literal("You're missing mods!");
    private final Component subtitleText = Component.literal("Click to download:");

    public MissingModsScreen() {
        super(Component.literal("Missing Mods"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int topTextY = 10;
        int buttonWidth = 200;
        int buttonHeight = 20;

        // Buttons for individual mods
        int modButtonY = topTextY + 45;
        int modButtonSpacing = 25;

        int buttons = 0;

        for (Map.Entry<String, String> entry : getMods().entrySet()) {
            addButtonForMod(entry.getKey(), entry.getValue(), centerX - buttonWidth / 2, modButtonY + modButtonSpacing * buttons);
            buttons++;
        }

        // Button for downloading all mods
        int downloadAllButtonY = modButtonY + modButtonSpacing * buttons + 10;
        int gap = 8;

        addRenderableWidget(
                Button.builder(
                        Component.literal("Download All"),
                        button -> openDownloadPages()
                ).bounds(
                        centerX - buttonWidth / 2,
                        downloadAllButtonY,
                        buttonWidth / 2 - gap / 2,
                        buttonHeight
                ).build()
        );

        addRenderableWidget(
                Button.builder(
                        Component.literal("Ignore"),
                        button -> exit()
                ).bounds(
                        centerX + gap / 2,
                        downloadAllButtonY,
                        buttonWidth / 2 - gap / 2,
                        buttonHeight
                ).build()
        );
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);

        super.render(graphics, mouseX, mouseY, delta);

        // Render missing mods text
        int centerX = this.width / 2;
        int titleY = 10;
        int subtitleY = titleY + 15;

        graphics.drawCenteredString(font, titleText, centerX, titleY, 16777215);
        graphics.drawCenteredString(font, subtitleText, centerX, subtitleY, 16777215);
    }

    private Map<String, String> getMods() {
        // This map has to be generated at runtime, otherwise the config won't work.
        Map<String, String> mods = new HashMap<>();

        if (FTBChecker.CONFIG.chunks && !ModList.get().isLoaded("ftbchunks"))
            mods.put("FTB Chunks", "https://www.curseforge.com/minecraft/mc-mods/ftb-chunks-forge/download/" + FTBChecker.CONFIG.versions.chunksVersion);

        if (FTBChecker.CONFIG.base && !ModList.get().isLoaded("ftblibrary"))
            mods.put("FTB Library", "https://www.curseforge.com/minecraft/mc-mods/ftb-library-forge/download/" + FTBChecker.CONFIG.versions.baseVersion);

        if (FTBChecker.CONFIG.quests && !ModList.get().isLoaded("ftbquests"))
            mods.put("FTB Quests", "https://www.curseforge.com/minecraft/mc-mods/ftb-quests-forge/download/" + FTBChecker.CONFIG.versions.questsVersion);

        if (FTBChecker.CONFIG.teams && !ModList.get().isLoaded("ftbteams"))
            mods.put("FTB Teams", "https://www.curseforge.com/minecraft/mc-mods/ftb-teams-forge/download/" + FTBChecker.CONFIG.versions.teamsVersion);

        if (FTBChecker.CONFIG.essentials && !ModList.get().isLoaded("ftbessentials"))
            mods.put("FTB Essentials", "https://www.curseforge.com/minecraft/mc-mods/ftb-essentials-forge/download/" + FTBChecker.CONFIG.versions.essentialsVersion);

        if (FTBChecker.CONFIG.ultimine && !ModList.get().isLoaded("ftbultimine"))
            mods.put("FTB Ultimine", "https://www.curseforge.com/minecraft/mc-mods/ftb-ultimine-forge/download/" + FTBChecker.CONFIG.versions.ultimineVersion);

        if (FTBChecker.CONFIG.backups && !ModList.get().isLoaded("ftbbackups2"))
            mods.put("FTB Backups 2", "https://www.curseforge.com/minecraft/mc-mods/ftb-backups-2/download/" + FTBChecker.CONFIG.versions.backupsVersion);

        if (FTBChecker.CONFIG.polyLib && !ModList.get().isLoaded("polylib"))
            mods.put("PolyLib", "https://www.curseforge.com/minecraft/mc-mods/polylib/download/" + FTBChecker.CONFIG.versions.polyLibVersion);

        if (FTBChecker.CONFIG.xmod && !ModList.get().isLoaded("ftbxmodcompat"))
            mods.put("FTB XMod Compat", "https://www.curseforge.com/minecraft/mc-mods/ftb-xmod-compat/download/" + FTBChecker.CONFIG.versions.xmodVersion);

        if (FTBChecker.CONFIG.filters && !ModList.get().isLoaded("itemfilters"))
            mods.put("Item Filters", "https://www.curseforge.com/minecraft/mc-mods/item-filters/download/" + FTBChecker.CONFIG.versions.filtersVersion);

        return mods;
    }

    private void addButtonForMod(String modName, String downloadLink, int x, int y) {
        int buttonWidth = 200;
        int buttonHeight = 20;

        addRenderableWidget(new Button.Builder(Component.literal(modName), button -> openWebPage(downloadLink)).bounds(x, y, buttonWidth, buttonHeight).build());
    }

    private void exit() {
        FTBChecker.IGNORED = true;

        Minecraft.getInstance().setScreen(new TitleScreen());
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void openWebPage(String url) {
        try {
            Util.getPlatform().openUri(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void openDownloadPages() {
        for (String url : getMods().values()) {
            openWebPage(url);
        }
    }
}