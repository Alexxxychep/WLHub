package me.alexxxychep.wlhub.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public abstract class WLItems {
    private static final Map<ItemMeta, WLItem> items = new HashMap<>();

    private static WLItem registerItem(WLItem item) {
        items.put(item.getItem(1).getItemMeta(), item);
        return item;
    }

    public static boolean isWLItem(ItemStack itemStack) {
        return items.containsKey(itemStack.getItemMeta());
    }

    public static WLItem getItemFromItemMeta(ItemMeta itemMeta) {
        return items.get(itemMeta);
    }

    public static WLItem getItemFromName(String name) {
        for(Map.Entry<ItemMeta, WLItem> entry : items.entrySet()) {
            if(entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
