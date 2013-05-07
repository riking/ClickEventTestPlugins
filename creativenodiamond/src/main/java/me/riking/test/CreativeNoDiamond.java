package me.riking.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class CreativeNoDiamond extends JavaPlugin implements Listener {
    public void onEnable() {
        getServer().getPluginManager().registerEvents((Listener)this, this);
    }

    public void onDisable() {
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e instanceof InventoryCreativeEvent) {
            InventoryCreativeEvent ev = (InventoryCreativeEvent)e;
            switch(ev.getItem().getType()) {
            case DIAMOND:
            case DIAMOND_BLOCK:
            case BEACON:
            case DIAMOND_AXE:
            case DIAMOND_BOOTS:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_HELMET:
            case DIAMOND_HOE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_ORE:
            case DIAMOND_PICKAXE:
            case DIAMOND_SPADE:
            case DIAMOND_SWORD:
            case JUKEBOX:
                e.setCancelled(true);
                break;
            default:
                break;
            }
        }
    }
}
