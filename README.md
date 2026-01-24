# FTBChecker

A simple mod that checks for required mods on startup and shows download links for any that are missing.

Comes pre-configured with FTB mods (Chunks, Library, Quests, Teams, Ultimine, Backups) but can be configured to check for any mod.

![Missing Mods Screen](https://i.imgur.com/placeholder.png)

## 📦 Supported Platforms

| Platform | Minecraft | Branch |
|----------|-----------|--------|
| NeoForge | 1.21.1 | `main` |
| Fabric | 1.21.1 | `fabric` |
| Fabric | 1.19.2 | `fabric-1.19.2` |
| Quilt | 1.21.0 | `quilt` |

## ✨ Features

- 🔍 Checks for mods on game startup
- 📋 Shows a screen with direct download links for missing mods
- ⚙️ Fully configurable - enable/disable individual checks
- ➕ Add custom mods to check for
- 📦 All dependencies bundled - just drop in and go

## 📥 Installation

1. Download from [Releases](https://github.com/SajmonOriginal/FTBChecker/releases)
2. Drop the `.jar` into your `mods` folder
3. Done!

## ⚙️ Configuration

Config file: `config/ftbchecker.toml`

### Enable/Disable Mod Checks

```toml
chunks = true
base = true
quests = true
teams = true
ultimine = false
backups = false
```

### Add Custom Mods

```toml
[customMods]
mods = [
    "jei|Just Enough Items|https://www.curseforge.com/minecraft/mc-mods/jei/download/5101366",
    "create|Create|https://www.curseforge.com/minecraft/mc-mods/create/download/5678901"
]
```

Format: `modId|Display Name|Download URL`

> 💡 **Tip:** Find the CurseForge file ID in the download URL (e.g., `.../download/5101366`)

## 📄 License

[MIT License](LICENSE)

## ⚠️ Disclaimer

FTBChecker is not affiliated with or endorsed by FTB (Feed The Beast).
