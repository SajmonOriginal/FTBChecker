package com.sajmonoriginal.ftbchecker;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public class MissingModsScreen extends Screen {
    private final Component titleText = new TextComponent("You're missing mods");
    private final Component subtitleText = new TextComponent("Click to download");

    public MissingModsScreen() {
        super(new TextComponent("Missing Mods"));
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
        addButtonForMod("FTBChunks", "https://www.curseforge.com/minecraft/mc-mods/ftb-chunks-forge/download/4494633", centerX - buttonWidth / 2, modButtonY);
        addButtonForMod("FTBLibrary", "https://www.curseforge.com/minecraft/mc-mods/ftb-library-forge/download/4396792", centerX - buttonWidth / 2, modButtonY + modButtonSpacing);
        addButtonForMod("FTBQuests", "https://legacy.curseforge.com/minecraft/mc-mods/ftb-quests-forge/download/4398375", centerX - buttonWidth / 2, modButtonY + modButtonSpacing * 2);
        addButtonForMod("FTBTeams", "https://www.curseforge.com/minecraft/mc-mods/ftb-teams-forge/download/4375188", centerX - buttonWidth / 2, modButtonY + modButtonSpacing * 3);

        // Button for downloading all mods
        int downloadAllButtonY = modButtonY + modButtonSpacing * 4 + 10;
        this.addRenderableWidget(new Button(centerX - buttonWidth / 2, downloadAllButtonY, buttonWidth, buttonHeight, new TextComponent("Download All"), button -> openDownloadPage()));
    }

    @Override
    public void render(@NotNull PoseStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        // Render missing mods text
        int centerX = this.width / 2;
        int titleY = 10;
        int subtitleY = titleY + 15;
        drawCenteredString(matrices, this.font, titleText, centerX, titleY, 16777215);
        drawCenteredString(matrices, this.font, subtitleText, centerX, subtitleY, 16777215);
    }

    private void addButtonForMod(String modName, String downloadLink, int x, int y) {
        int buttonWidth = 200;
        int buttonHeight = 20;
        this.addRenderableWidget(new Button(x, y, buttonWidth, buttonHeight, new TextComponent(modName), button -> openWebPage(downloadLink)));
    }

    private void openWebPage(String url) {
        try {
            net.minecraft.Util.getPlatform().openUri(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void openDownloadPage() {
        try {
            openWebPage("https://www.curseforge.com/minecraft/mc-mods/ftb-chunks-forge/download/4494633");
            openWebPage("https://www.curseforge.com/minecraft/mc-mods/ftb-library-forge/download/4396792");
            openWebPage("https://legacy.curseforge.com/minecraft/mc-mods/ftb-quests-forge/download/4398375");
            openWebPage("https://www.curseforge.com/minecraft/mc-mods/ftb-teams-forge/download/4375188");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}