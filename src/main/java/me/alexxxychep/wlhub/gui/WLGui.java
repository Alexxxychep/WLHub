package me.alexxxychep.wlhub.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public abstract class WLGui {
    private static final Map<String, WLGui> guis = Map.of(
        ServerMenuGui.getInstance().getName(), ServerMenuGui.getInstance()
    );

    private final String name;
    private final String displayName;

    public WLGui(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Inventory getInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, getContents().length, Component.text(getDisplayName()));
        inventory.setContents(getContents());
        return inventory;
    }

    public static WLGui getGuiFromName(String name) {
        return guis.get(name);
    }

    public abstract ItemStack[] getContents();

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
