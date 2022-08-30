package p1xel.minecraft.bukkit.Utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import p1xel.minecraft.bukkit.HandItemDrop;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Locale {

    public static void createLocaleFile() {

        List<String> lang = Arrays.asList("en","zh_CN");
        for (String l : lang) {
            File file = new File(HandItemDrop.getInstance().getDataFolder(), l + ".yml");
            if (!file.exists()) {
                HandItemDrop.getInstance().saveResource(l + ".yml", false);
            }
        }
    }

    public static FileConfiguration get() {
        File file = new File(HandItemDrop.getInstance().getDataFolder(), Config.getLanguage() + ".yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, Object value) {
        File file = new File(HandItemDrop.getInstance().getDataFolder(), Config.getLanguage() + ".yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set(path,value);
        try {
            yaml.save(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', get().getString(path).replaceAll("%plugin%", get().getString("Prefix")).replaceAll("%version%", Config.getVersion()));
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message.replaceAll("%plugin%", get().getString("Prefix")).replaceAll("%version%", Config.getVersion()));
    }
    
}
