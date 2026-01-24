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
        // MC 1.19.2 compatible versions (Fabric)
        public String architecturyVersion = "5084403";  // [Fabric 1.19.2] v6.5.90
        public String chunksVersion = "5710629";        // [Fabric][1.19.2] FTB Chunks 1902.4.7
        public String baseVersion = "4661833";          // [Fabric][1.19.2] FTB Library 1902.4.1
        public String questsVersion = "5417955";        // [Fabric][1.19.2] FTB Quests 1902.5.9
        public String teamsVersion = "4477313";         // [Fabric][1.19.2] FTB Teams 1902.2.13
        public String ultimineVersion = "4598467";      // [Fabric][1.19.2] FTB Ultimine 1902.4.1
        public String backupsVersion = "4407012";       // FTB Backups 2 - 1.0.19
        public String polyLibVersion = "4346098";       // PolyLib 1900.0.2-build.73
    }

    @ConfigEntry.Gui.CollapsibleObject
    public CustomMods customMods = new CustomMods();

    public static class CustomMods {
        public List<String> mods = new ArrayList<>();
    }
}
