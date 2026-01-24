package com.sajmonoriginal.ftbchecker;

import org.quiltmc.loader.api.QuiltLoader;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MissingModsScreen extends Screen {
    private final Text titleText = Text.literal("You're missing mods!");
    private final Text subtitleText = Text.literal("Click to download:");

    public MissingModsScreen() {
        super(Text.literal("Missing Mods"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int topTextY = 10;
        int buttonWidth = 200;
        int buttonHeight = 20;

        int modButtonY = topTextY + 45;
        int modButtonSpacing = 25;

        int buttons = 0;

        for (Map.Entry<String, String> entry : getMods().entrySet()) {
            addButtonForMod(entry.getKey(), entry.getValue(), centerX - buttonWidth / 2, modButtonY + modButtonSpacing * buttons);
            buttons++;
        }

        int downloadAllButtonY = modButtonY + modButtonSpacing * buttons + 10;

        addDrawableSelectableElement(
                ButtonWidget.builder(
                        Text.literal("Download All"),
                        button -> openDownloadPages()
                ).position(
                        centerX - buttonWidth / 2,
                        downloadAllButtonY
                ).size(
                        buttonWidth,
                        buttonHeight
                ).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics, mouseX, mouseY, delta);

        super.render(graphics, mouseX, mouseY, delta);

        int centerX = this.width / 2;
        int titleY = 10;
        int subtitleY = titleY + 15;

        graphics.drawCenteredShadowedText(textRenderer, titleText, centerX, titleY, 16777215);
        graphics.drawCenteredShadowedText(textRenderer, subtitleText, centerX, subtitleY, 16777215);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    private Map<String, String> getMods() {
        Map<String, String> mods = new LinkedHashMap<>();

        if (FTBCheckerClient.CONFIG.architectury && !QuiltLoader.isModLoaded("architectury"))
            mods.put("Architectury API", "https://www.curseforge.com/minecraft/mc-mods/architectury-api/download/" + FTBCheckerClient.CONFIG.versions.architecturyVersion);

        if (FTBCheckerClient.CONFIG.chunks && !QuiltLoader.isModLoaded("ftbchunks"))
            mods.put("FTB Chunks", "https://www.curseforge.com/minecraft/mc-mods/ftb-chunks-fabric/download/" + FTBCheckerClient.CONFIG.versions.chunksVersion);

        if (FTBCheckerClient.CONFIG.base && !QuiltLoader.isModLoaded("ftblibrary"))
            mods.put("FTB Library", "https://www.curseforge.com/minecraft/mc-mods/ftb-library-fabric/download/" + FTBCheckerClient.CONFIG.versions.baseVersion);

        if (FTBCheckerClient.CONFIG.quests && !QuiltLoader.isModLoaded("ftbquests"))
            mods.put("FTB Quests", "https://www.curseforge.com/minecraft/mc-mods/ftb-quests-fabric/download/" + FTBCheckerClient.CONFIG.versions.questsVersion);

        if (FTBCheckerClient.CONFIG.teams && !QuiltLoader.isModLoaded("ftbteams"))
            mods.put("FTB Teams", "https://www.curseforge.com/minecraft/mc-mods/ftb-teams-fabric/download/" + FTBCheckerClient.CONFIG.versions.teamsVersion);

        if (FTBCheckerClient.CONFIG.ultimine && !QuiltLoader.isModLoaded("ftbultimine"))
            mods.put("FTB Ultimine", "https://www.curseforge.com/minecraft/mc-mods/ftb-ultimine-fabric/download/" + FTBCheckerClient.CONFIG.versions.ultimineVersion);

        if (FTBCheckerClient.CONFIG.backups && !QuiltLoader.isModLoaded("ftbbackups2"))
            mods.put("FTB Backups 2", "https://www.curseforge.com/minecraft/mc-mods/ftb-backups-2/download/" + FTBCheckerClient.CONFIG.versions.backupsVersion);

        if (FTBCheckerClient.CONFIG.polyLib && !QuiltLoader.isModLoaded("polylib"))
            mods.put("PolyLib", "https://www.curseforge.com/minecraft/mc-mods/polylib/download/" + FTBCheckerClient.CONFIG.versions.polyLibVersion);

        for (String modEntry : FTBCheckerClient.CONFIG.customMods.mods) {
            String[] parts = modEntry.split("\\|", 3);
            if (parts.length >= 3) {
                String modId = parts[0].trim();
                String displayName = parts[1].trim();
                String downloadUrl = parts[2].trim();
                if (!modId.isEmpty() && !QuiltLoader.isModLoaded(modId)) {
                    mods.put(displayName, downloadUrl);
                }
            }
        }

        return mods;
    }

    private void addButtonForMod(String modName, String downloadLink, int x, int y) {
        int buttonWidth = 200;
        int buttonHeight = 20;

        addDrawableSelectableElement(ButtonWidget.builder(Text.literal(modName), button -> openWebPage(downloadLink)).position(x, y).size(buttonWidth, buttonHeight).build());
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void openWebPage(String url) {
        try {
            Util.getOperatingSystem().open(new URI(url));
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
