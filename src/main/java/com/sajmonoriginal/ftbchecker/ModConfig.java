package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.List;

@Config(name = FTBCheckerClient.MOD_ID)
public class ModConfig implements ConfigData {
    public boolean architectury = true;
    public boolean chunks = true;
    public boolean base = true;
    public boolean quests = true;
    public boolean teams = true;
    public boolean ultimine = false;
    public boolean backups = false;
    public boolean polyLib = false;

    @ConfigEntry.Gui.CollapsibleObject
    public Versions versions = new Versions();

    public static class Versions {
        // MC 1.21.0 compatible versions (Fabric/Quilt)
        public String architecturyVersion = "5472557";  // [Fabric 1.21] v13.0.2
        public String chunksVersion = "5448647";        // [FABRIC][1.21] FTB Chunks 2100.1.0
        public String baseVersion = "5444533";          // [FABRIC][1.21] FTB Library 2100.1.0
        public String questsVersion = "5468466";        // [FABRIC][1.21] FTB Quests 2100.1.1
        public String teamsVersion = "5448372";         // [FABRIC][1.21] FTB Teams 2100.1.0
        public String ultimineVersion = "5446671";      // [FABRIC][1.21] FTB Ultimine 2100.1.0
        public String backupsVersion = "5501350";       // FTB Backups 2
        public String polyLibVersion = "5553799";       // Architectury 13.0.6 (fallback if polyLib not found)
    }

    @ConfigEntry.Gui.CollapsibleObject
    public CustomMods customMods = new CustomMods();

    public static class CustomMods {
        public List<String> mods = new ArrayList<>();
    }
}
