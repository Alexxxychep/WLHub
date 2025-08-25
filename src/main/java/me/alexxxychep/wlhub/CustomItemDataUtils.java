package me.alexxxychep.wlhub;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CustomItemDataUtils {
    private static final List<String> keys = List.of("WLItem");

    public static String hasCustomData(ItemStack itemStack) {
        for(String key : keys) {
            if(itemStack.getItemMeta().getPersistentDataContainer().getKeys().contains(NamespacedKey.fromString(key))) {
                return key;
            }
        }
        return null;
    }

    public static String getValueFromKey(ItemStack itemStack, String key) {
        return itemStack.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString(key), PersistentDataType.STRING);
    }
}
