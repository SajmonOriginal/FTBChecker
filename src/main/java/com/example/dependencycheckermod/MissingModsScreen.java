package com.example.dependencycheckermod;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.fml.ModList;

import java.net.URI;
import java.net.URISyntaxException;

public class MissingModsScreen extends Screen {
    public MissingModsScreen() {
        super(new TextComponent("Missing Mods Screen"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int centerX = (this.width - buttonWidth) / 2;
        int centerY = (this.height - buttonHeight) / 2;

        if (!areDependenciesPresent()) {
            Button downloadButton = new Button(centerX, centerY, buttonWidth, buttonHeight, new TextComponent("Download Mods"), button -> {
                openDownloadPage();
            });

            this.addRenderableWidget(downloadButton);
        }
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    private boolean areDependenciesPresent() {
        return ModList.get().isLoaded("ftbchunks") &&
                ModList.get().isLoaded("ftb-library") &&
                ModList.get().isLoaded("ftb-quests");
    }

    private void openDownloadPage() {
        try {
            // Odkaz na stránku s modifikací FTBChunks
            openWebPage(new URI("https://www.curseforge.com/minecraft/mc-mods/ftbchunks"));

            // Odkaz na stránku s modifikací FTB Library
            openWebPage(new URI("https://www.curseforge.com/minecraft/mc-mods/ftb-library"));

            // Odkaz na stránku s modifikací FTB Quests
            openWebPage(new URI("https://www.curseforge.com/minecraft/mc-mods/ftb-quests"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void openWebPage(URI uri) {
        try {
            Class<?> desktopClass = Class.forName("java.awt.Desktop");
            Object desktop = desktopClass.getMethod("getDesktop").invoke(null);
            desktopClass.getMethod("browse", URI.class).invoke(desktop, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
