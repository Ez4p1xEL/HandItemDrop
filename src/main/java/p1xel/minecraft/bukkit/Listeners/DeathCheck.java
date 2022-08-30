package p1xel.minecraft.bukkit.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import p1xel.minecraft.bukkit.Utils.Config;
import p1xel.minecraft.bukkit.Utils.Locale;

import java.util.List;

public class DeathCheck implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity().getPlayer();
        boolean isInventoryKeep = e.getKeepInventory();
        ItemStack handItem;
        World world = p.getWorld();
        Location loc = p.getLocation();

        if (!isInventoryKeep) {

            if (Config.getStringList("blacklist.worlds").contains(world.getName())) {
                if (!Config.getBool("blacklist.to-whitelist")) {
                    return;
                }
            }

            if (p.hasPermission("handitemdrop.bypass")) {
                return;
            }

            if (!Config.getBool("highversion")) {

                handItem = p.getItemInHand();
                boolean keepHandItem = false;

                if (handItem != null) {

                    if (Config.getBool("cant-drop-item.enable")) {
                        for (String item : Config.getStringList("cant-drop-item.list")) {
                            if (handItem.getType() == Material.matchMaterial(item)) {
                                keepHandItem = true;
                            }
                        }
                    }

                    if (Config.getBool("lore-detect.enable")) {
                        if (handItem.hasItemMeta()) {
                            ItemMeta meta = handItem.getItemMeta();
                            if (meta.hasLore()) {
                                List<String> lores = meta.getLore();
                                if (lores.contains(Config.getString("lore-detect.lore"))) {
                                    keepHandItem = true;
                                }
                            }
                        }
                    }

                    e.getDrops().clear();
                    e.setKeepInventory(true);
                    e.setKeepLevel(true);
                    if (!keepHandItem) {
                        p.getInventory().setItemInHand(null);
                        world.dropItemNaturally(loc, handItem);
                        p.sendMessage(Locale.getMessage("death-message"));
                    }

                }
            } else {

                handItem = p.getInventory().getItemInMainHand();
                ItemStack offHandItem = p.getInventory().getItemInOffHand();

                if (handItem.getType() != Material.AIR && offHandItem.getType() == Material.AIR) {

                    boolean keepHandItem = false;
                    boolean keepOffHandItem = false;

                    if (Config.getBool("cant-drop-item.enable")) {
                        for (String item : Config.getStringList("cant-drop-item.list")) {
                            if (handItem.getType() == Material.matchMaterial(item)) {
                                keepHandItem = true;
                            }
                        }
                    }

                    if (Config.getBool("lore-detect.enable")) {
                        if (handItem.hasItemMeta()) {
                            ItemMeta meta = handItem.getItemMeta();
                            if (meta.hasLore()) {
                                List<String> lores = meta.getLore();
                                String l = Config.getString("lore-detect.lore");
                                l = ChatColor.translateAlternateColorCodes('&', l);
                                if (lores.contains(l)) {
                                    keepHandItem = true;
                                }
                            }
                        }

                    }

                    e.getDrops().clear();
                    e.setKeepInventory(true);
                    e.setKeepLevel(true);
                    if (!keepHandItem) {
                        p.getInventory().setItemInMainHand(null);
                        world.dropItemNaturally(loc, handItem);
                        p.sendMessage(Locale.getMessage("death-message"));
                    }
                    return;

                }

                if (handItem.getType() == Material.AIR && offHandItem.getType() != Material.AIR) {

                    boolean keepHandItem = false;
                    boolean keepOffHandItem = false;

                    if (Config.getBool("cant-drop-item.enable")) {
                        for (String item : Config.getStringList("cant-drop-item.list")) {
                            if (offHandItem.getType() == Material.matchMaterial(item)) {
                                keepOffHandItem = true;
                            }
                        }
                    }

                    if (Config.getBool("lore-detect.enable")) {
                        if (offHandItem.hasItemMeta()) {
                            ItemMeta meta = offHandItem.getItemMeta();
                            if (meta.hasLore()) {
                                List<String> lores = meta.getLore();
                                String l = Config.getString("lore-detect.lore");
                                l = ChatColor.translateAlternateColorCodes('&', l);
                                if (lores.contains(l)) {
                                    keepOffHandItem = true;
                                }
                            }
                        }
                    }

                    e.getDrops().clear();
                    e.setKeepInventory(true);
                    e.setKeepLevel(true);
                    if (!keepOffHandItem) {
                        p.getInventory().setItemInOffHand(null);
                        world.dropItemNaturally(loc, offHandItem);
                        p.sendMessage(Locale.getMessage("death-message"));
                    }
                    return;

                }

                if (handItem.getType() != Material.AIR && offHandItem.getType() != Material.AIR) {

                    boolean keepHandItem = false;
                    boolean keepOffHandItem = false;

                    if (Config.getBool("cant-drop-item.enable")) {
                        for (String item : Config.getStringList("cant-drop-item.list")) {
                            if (handItem.getType() == Material.matchMaterial(item)) {
                                keepHandItem = true;
                            }

                            if (offHandItem.getType() == Material.matchMaterial(item)) {
                                keepOffHandItem = true;
                            }
                        }
                    }

                    if (Config.getBool("lore-detect.enable")) {
                        if (handItem.hasItemMeta()) {
                            ItemMeta meta = handItem.getItemMeta();
                            if (meta.hasLore()) {
                                List<String> lores = meta.getLore();
                                String l = Config.getString("lore-detect.lore");
                                l = ChatColor.translateAlternateColorCodes('&', l);
                                if (lores.contains(l)) {
                                    keepHandItem = true;
                                }
                            }
                        }

                        if (offHandItem.hasItemMeta()) {
                            ItemMeta meta = offHandItem.getItemMeta();
                            if (meta.hasLore()) {
                                List<String> lores = meta.getLore();
                                String l = Config.getString("lore-detect.lore");
                                l = ChatColor.translateAlternateColorCodes('&', l);
                                if (lores.contains(l)) {
                                    keepOffHandItem = true;
                                }
                            }
                        }


                    }

                    e.getDrops().clear();
                    e.setKeepInventory(true);
                    e.setKeepLevel(true);
                    if (!keepHandItem) {
                        p.getInventory().setItemInMainHand(null);
                        world.dropItemNaturally(loc, handItem);
                    }

                    if (!keepOffHandItem) {
                        p.getInventory().setItemInOffHand(null);
                        world.dropItemNaturally(loc, offHandItem);
                    }

                    if (keepHandItem && keepOffHandItem) {
                        p.sendMessage(Locale.getMessage("death-message"));
                    }

                    return;

                }

                if (handItem.getType() == Material.AIR && offHandItem.getType() == Material.AIR) {
                    e.getDrops().clear();
                    e.setKeepInventory(true);
                    e.setKeepLevel(true);
                }

            }

        }

    }

}
