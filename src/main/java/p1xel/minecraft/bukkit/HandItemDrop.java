package p1xel.minecraft.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import p1xel.minecraft.bukkit.Command.Cmd;
import p1xel.minecraft.bukkit.Listeners.DeathCheck;
import p1xel.minecraft.bukkit.Utils.Locale;
import p1xel.minecraft.bukkit.bStats.Metrics;

public class HandItemDrop extends JavaPlugin {

    private static HandItemDrop instance;

    public static HandItemDrop getInstance() { return instance; }


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Locale.createLocaleFile();

        getServer().getPluginCommand("HandItemDrop").setExecutor(new Cmd());
        getServer().getPluginManager().registerEvents(new DeathCheck(), this);

        getLogger().info("Plugin loaded!");

        int pluginId = 16302;
        new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin unloaded!");
    }

}
