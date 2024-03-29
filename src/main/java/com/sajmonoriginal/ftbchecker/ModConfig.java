package com.sajmonoriginal.ftbchecker;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = FTBChecker.MOD_ID)
public class ModConfig implements ConfigData {
    public boolean chunks = true;
    public boolean base = true;
    public boolean quests = true;
    public boolean teams = true;
    public boolean essentials = true;
    public boolean ultimine = true;
    public boolean backups = true;
    public boolean polyLib = true;

    @ConfigEntry.Gui.CollapsibleObject
    Versions versions = new Versions();

    public static class Versions {
        public String chunksVersion = "5210496";
        public String baseVersion = "5051953";
        public String questsVersion = "5192381";
        public String teamsVersion = "5176343";
        public String essentialsVersion = "4896152";
        public String ultimineVersion = "5005006";
        public String backupsVersion = "4834403";
        public String polyLibVersion = "5218944";
    }
}
