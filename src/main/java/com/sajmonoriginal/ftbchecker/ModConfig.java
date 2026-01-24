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
        public String chunksVersion = "5598952";
        public String baseVersion = "5583834";
        public String questsVersion = "5543248";
        public String teamsVersion = "5448371";
        public String essentialsVersion = "5443860";
        public String ultimineVersion = "5448787";
        public String backupsVersion = "5859579";
        public String polyLibVersion = "7140477";
    }
}
