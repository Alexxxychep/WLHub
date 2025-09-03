package me.alexxxychep.wlhub.items;

import me.alexxxychep.wlhub.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;


public abstract class WLItem {
    private final String name;


    protected WLItem(String name) {
        this.name = name;
    }

    public ItemStack getItem(int amount) {
        return getItem(amount, Main.getInstance());
    }

    public ItemStack getItem(int amount, Plugin plugin) {
        ItemStack itemStack = new ItemStack(getMaterial(), amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(((TextComponent)getDisplayName()).content());
        itemStack.setItemMeta(getModifiedItemMeta(getItemMetaWithCustomData(itemMeta, plugin)));
        return itemStack;
    }

    public ItemMeta getItemMetaWithCustomData(ItemMeta itemMeta) {
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "WLItem"), PersistentDataType.STRING, getName());
        return itemMeta;
    }

    public ItemMeta getItemMetaWithCustomData(ItemMeta itemMeta, Plugin plugin) {
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "WLItem"), PersistentDataType.STRING, getName());
        return itemMeta;
    }

    public ItemMeta getModifiedItemMeta(ItemMeta itemMeta) {
        return itemMeta;
    }

    public abstract Material getMaterial();

    public abstract Component getDisplayName();

    public String getName() {
        return name;
    }
}
