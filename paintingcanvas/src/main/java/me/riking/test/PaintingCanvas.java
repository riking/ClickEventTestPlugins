package me.riking.test;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PaintingCanvas extends JavaPlugin implements Listener {
    public Map<String, Map<String, Canvas>> paintings;
    public Map<String, PlayerState> players;

    public void onEnable() {
        getServer().getPluginManager().registerEvents((Listener)this, this);
        paintings = new HashMap<String, Map<String, Canvas>>();
    }

    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName() == "paint") {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Painting will not work with a non-player!");
                return false;
            }
            Map<String, Canvas> cmap = paintings.get(sender.getName());
            if (cmap == null) {
                cmap = new HashMap<String, Canvas>();
                paintings.put(sender.getName(), cmap);
            }
            String canvasname = "";
            if (args.length != 0) {
                canvasname = args[0];
            }
            Canvas c = cmap.get(canvasname);
            if (c == null) {
                c = new Canvas();
                cmap.put(canvasname, c);
            }
            openCanvas((Player)sender, c);
        }
        return false;
    }

    public void openCanvas(Player p, Canvas c) {
        Inventory i = Bukkit.getServer().createInventory(p, 54, "Painting");
        c.initEmptyCanvas(i);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (players.get(e.getPlayer().getName()) == PlayerState.CANVAS_OPEN) {
            players.put(e.getPlayer().getName(), PlayerState.IDLE);
        }
    }

    private enum PlayerState {
        IDLE,
        CANVAS_OPEN,
        ;
    }
}
