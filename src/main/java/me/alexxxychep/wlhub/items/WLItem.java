package me.alexxxychep.wlhub.items;

import me.alexxxychep.wlhub.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class WLItem {
    private final String name;

    private static final Map<ItemMeta, Class<? extends WLItem>> items = new HashMap<>();

    protected WLItem(String name) {
        this.name = name;
    }

    public static void registerItems() {
        registerItem(new GuiGlassFrameItem());
        registerItem(new MenuSelectorItem());
    }

    private static void registerItem(WLItem item) {
        System.out.println("About to register item: " + item.getName());
        items.put(item.getItem(1).getItemMeta(), item.getClass());
        System.out.println("Successfully register item: " + item.getName());
    }

    protected static List<String> getRegisteredItems() {
        return items.keySet().stream()
            .map(ItemMeta::getLocalizedName)
            .collect(Collectors.toList());
    }

    public ItemStack getItem(int amount) {
        ItemStack itemStack = new ItemStack(getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(getDisplayName());
        itemStack.setItemMeta(getModifiedItemMeta(getItemMetaWithCustomData(itemMeta)));
        return itemStack;
    }

    public static boolean isWLItem(ItemStack itemStack) {
        return items.containsKey(itemStack.getItemMeta());
    }

    public static WLItem getItemFromItemMeta(ItemMeta itemMeta) {
        return getItemFromClass(items.get(itemMeta));
    }

    public static WLItem getItemFromName(String name) {
        for (Map.Entry<ItemMeta, Class<? extends WLItem>> entry : items.entrySet()) {
            WLItem wlItem = getItemFromClass(entry.getValue());
            if (wlItem.name.equals(name)) {
                return wlItem;
            }
        }
        return null;
    }

    private static WLItem getItemFromClass(Class<? extends WLItem> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public ItemMeta getItemMetaWithCustomData(ItemMeta itemMeta) {
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "WLItem"), PersistentDataType.STRING, getName());
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

    protected static void populateItemMap() {
        items.clear();
        java.util.List<Class<? extends WLItem>> subclasses = WLItemScanner.findWLItemSubclasses();
        for (Class<? extends WLItem> clazz : subclasses) {
            try {
                WLItem instance = clazz.getDeclaredConstructor().newInstance();
                ItemStack itemStack = instance.getItem(1);
                if (itemStack != null && itemStack.hasItemMeta()) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    items.put(itemMeta, clazz);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
