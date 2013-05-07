package me.riking.test;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Canvas {
    public static final int TOOLS_COLUMN = 0;
    public static final int PALETTE_COLUMN = 1;
    public static final int BORDER_COLUMN = 2;

    public static final int BRUSH_SLOT = 0*9 + 0;
    public static final int FILL_SLOT = 1*9 + 0;
    public static final int ERASE_SLOT = 2*9 + 0;
    public static final int UNDO_SLOT = 3*9 + 0;
    public static final int HELP_SLOT = 4*9 + 0;
    public static final int EXIT_SLOT = 5*9 + 0;
    public static final int PALETTE_UP_SLOT = 0*9 + 1;
    public static final int PALETTE_DOWN_SLOT = 5*9 + 1;

    public final ItemStack item_brush_unselected = new ItemStack(Material.STONE_SPADE);
    public final ItemStack item_brush_selected = new ItemStack(Material.IRON_SPADE);
    public final ItemStack item_fill_unselected = new ItemStack(Material.BUCKET);
    public final ItemStack item_fill_selected = new ItemStack(Material.WATER_BUCKET);
    public final ItemStack item_erase_unselected = new ItemStack(Material.PORK);
    public final ItemStack item_erase_selected = new ItemStack(Material.GRILLED_PORK);
    public final ItemStack item_undo_unselected = new ItemStack(Material.WEB);
    public final ItemStack item_undo_selected = new ItemStack(Material.TNT);
    public final ItemStack item_help = new ItemStack(Material.BOOK);
    public final ItemStack item_exit = new ItemStack(Material.ENDER_PEARL);
    public final ItemStack item_palette_scroll = new ItemStack(Material.);


    private ItemStack[] items = new ItemStack[6*9];
    public ItemStack material;
    public int opmode;

    public void initEmptyCanvas(Inventory inv) {
        items[BRUSH_SLOT] = item_brush_unselected;
        items[FILL_SLOT] = item_fill_unselected;
        items[ERASE_SLOT] = item_erase_unselected;
        items[UNDO_SLOT] = item_undo_unselected;
        items[HELP_SLOT] = item_help;
        items[EXIT_SLOT] = item_exit;
        inv.
    }

    public ItemStack[][] getAllItems() {
        return items;
    }

    public void setItem(int row, int column, ItemStack item) {
        items[row*9 + column] = item;
    }
}
