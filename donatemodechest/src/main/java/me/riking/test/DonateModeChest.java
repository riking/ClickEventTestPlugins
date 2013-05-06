package me.riking.test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class DonateModeChest extends JavaPlugin implements Listener {
    public void onEnable() {
        getPluginManager().registerEvents((Listener)this, this);
    }

    public void onDisable() {
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getType() != org.bukkit.event.inventory.InventoryType.CHEST) {
            return;
        }
        switch (e.getAction()) {
            case PICKUP_ALL:
            case PICKUP_SOME:
            case PICKUP_HALF:
            case PICKUP_ONE:
            case SWAP_WITH_CURSOR:
            case DROP_ALL_SLOT:
            case DROP_ONE_SLOT:
            case MOVE_TO_OTHER_INVENTORY:
            case HOTBAR_MOVE_AND_READD:
            case HOTBAR_SWAP:
            case UNKNOWN:
                // Bottom half only
                if (e.getRawSlot() == e.getSlot()) { // exploit InventoryView contract
                    e.setResult(Result.DENY);
                }
                break;
            case PLACE_ALL:
            case PLACE_SOME:
            case PLACE_ONE:
            case CLONE_STACK:
            case DROP_ALL_CURSOR:
            case DROP_ONE_CURSOR:
            case RESET_DRAG_STATE:
            case BEGIN_DRAG:
            case DRAG_ADD_SLOT:
            case FINISH_DRAG:
            case PLACE_DRAG_SINGLE:
            case PLACE_DRAG_EVEN:
                // Allow
                return;
            case COLLECT_TO_CURSOR:
                if (e.getView().getTopInventory().contains(e.getCursor().getType())) {
                    e.setResult(Result.DENY);
                }
                break;
            default:
                break;
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        // Allow
        return;
    }
}
