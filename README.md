# FTBChecker

FTBChecker is a Minecraft NeoForge mod designed to simplify modpack publishing on Modrinth by resolving licensing issues. It ensures the presence of key FTB mods, such as FTBChunks, FTB Library, and FTB Quests.

## Features
- Automatically checks for the presence of FTB mods in the Minecraft client.
- Displays a screen with download links for missing mods upon launch.
- Facilitates the inclusion of FTB mods in modpacks for hassle-free publishing on Modrinth.
- Support for custom mods - require any mod in your modpack.

## Installation
1. Download the latest release of FTBChecker from the [Releases](https://github.com/SajmonOriginal/FTBChecker/releases) page.
2. Place the downloaded `.jar` file into the `mods` folder of your Minecraft installation directory.
3. Launch Minecraft with the NeoForge profile.

## Configuration

Config file location: `config/ftbchecker.toml`

### Adding Custom Mods

You can require any mod by adding entries to the `customMods.mods` list. Each entry uses this format:

```
modId|Display Name|Download URL
```

**Example config:**
```toml
[customMods]
mods = [
    "jei|Just Enough Items|https://www.curseforge.com/minecraft/mc-mods/jei/download/5101366",
    "create|Create|https://www.curseforge.com/minecraft/mc-mods/create/download/5678901"
]
```

### Finding the CurseForge File ID

1. Go to the mod's CurseForge page
2. Click on the "Files" tab
3. Find the version you want
4. Click on the file - the ID is in the URL (e.g., `.../download/5101366`)

### Disabling FTB Mod Checks

You can disable individual FTB mod checks by setting them to `false`:

```toml
chunks = false
base = true
quests = true
# ... etc
```

## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Contributing
Contributions are welcome! If you have any ideas, suggestions, or bug reports, please open an issue or submit a pull request.

## Credits
FTBChecker is developed and maintained by [SajmonOriginal](https://github.com/SajmonOriginal).

## Disclaimer
FTBChecker is not affiliated with or endorsed by FTB (Feed The Beast) or Modrinth.

Enjoy using FTBChecker and happy modpack publishing!
