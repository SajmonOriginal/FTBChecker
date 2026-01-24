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
        public String architecturyVersion = "5786326";
        public String chunksVersion = "7157144";
        public String baseVersion = "7420411";
        public String questsVersion = "7429015";
        public String teamsVersion = "7369020";
        public String ultimineVersion = "7188393";
        public String backupsVersion = "5501350";
        public String polyLibVersion = "7140476";
    }

    @ConfigEntry.Gui.CollapsibleObject
    public CustomMods customMods = new CustomMods();

    public static class CustomMods {
        public List<String> mods = new ArrayList<>();
    }
}
