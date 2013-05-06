package me.riking.test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryView;

public class ReadOnlyHopper extends JavaPlugin implements Listener {
    public void onEnable() {
        getPluginManager().registerEvents((Listener)this, this);
    }

    public void onDisable() {
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getType() != org.bukkit.event.inventory.InventoryType.HOPPER) {
            return;
        }
        switch (e.getAction()) {
            case PICKUP_ALL:
            case PICKUP_SOME:
            case PICKUP_HALF:
            case PICKUP_ONE:
            case PLACE_ALL:
            case PLACE_SOME:
            case PLACE_ONE:
            case SWAP_WITH_CURSOR:
            case DROP_ALL_SLOT:
            case DROP_ONE_SLOT:
            case HOTBAR_MOVE_AND_READD:
            case HOTBAR_SWAP:
            case PLACE_DRAG_SINGLE:
            case PLACE_DRAG_EVEN:
            case UNKNOWN:
                // Bottom half only
                if (e.getRawSlot() == e.getSlot()) { // exploit InventoryView contract
                    e.setResult(Result.DENY);
                }
                break;
            case NOTHING:
            case CLONE_STACK:
            case DROP_ALL_CURSOR:
            case DROP_ONE_CURSOR:
            case RESET_DRAG_STATE:
            case BEGIN_DRAG:
            case DRAG_ADD_SLOT:
            case FINISH_DRAG:
                // Allow
                return;
            case COLLECT_TO_CURSOR:
                if (e.getView().getTopInventory().contains(e.getCursor().getType())) {
                    e.setResult(Result.DENY);
                }
                break;
            case MOVE_TO_OTHER_INVENTORY:
                e.setResult(Result.DENY);
                break;
            default:
                break;
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getView().getType() != org.bukkit.event.inventory.InventoryType.HOPPER) {
            return;
        }
        // Bottom half only
        InventoryView v = e.getView();
        for (int slot : e.getRawSlots()) {
            if (v.convertSlot(slot) == slot) { // top inventory is returned unchanged
                e.setResult(Result.DENY);
            }
        }
        return;
    }
}
