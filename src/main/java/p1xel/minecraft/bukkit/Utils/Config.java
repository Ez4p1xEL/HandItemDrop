package p1xel.minecraft.bukkit.Utils;

import p1xel.minecraft.bukkit.HandItemDrop;

import java.util.List;

public class Config {

    public static String getString(String path) {
        return HandItemDrop.getInstance().getConfig().getString(path);
    }

    public static boolean getBool(String path) {
        return HandItemDrop.getInstance().getConfig().getBoolean(path);
    }

    public static String getLanguage() {
        return HandItemDrop.getInstance().getConfig().getString("Language");
    }

    public static List<String> getStringList(String path) { return HandItemDrop.getInstance().getConfig().getStringList(path); }

    public static void reloadConfig() {

        HandItemDrop.getInstance().reloadConfig();

    }

    public static String getVersion() {
        return getString("Version");
    }

    public static int getInt(String path) {
        return HandItemDrop.getInstance().getConfig().getInt(path);
    }
    
    
}
