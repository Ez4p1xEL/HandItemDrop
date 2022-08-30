package p1xel.minecraft.bukkit.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import p1xel.minecraft.bukkit.Utils.Config;
import p1xel.minecraft.bukkit.Utils.Locale;

import javax.annotation.ParametersAreNonnullByDefault;

public class Cmd implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("handitemdrop.reload")) {
            sender.sendMessage(Locale.getMessage("no-perm"));
            return true;
        }

        if (args.length == 0) {

            for (String m : Locale.get().getStringList("commands.main")) {
                sender.sendMessage(Locale.translate(m.replaceAll("%version%", Config.getVersion())));
            }
            return true;

        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(Locale.getMessage("reload-success"));
                Config.reloadConfig();
                return true;
            }
         }










        return false;
    }

}
