package me.alexxxychep.wlhub.items;

import me.alexxxychep.wlhub.Interactable;
import me.alexxxychep.wlhub.gui.WLGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MenuSelectorItem extends WLItem implements Interactable {
    public MenuSelectorItem() {
        super("MenuSelector");
    }

    @Override
    public Material getMaterial() {
        return Material.COMPASS;
    }

    @Override
    public Component getDisplayName() {
        return Component.text("Меню серверов");
    }

    @Override
    public void onInteract(Player player) {
        player.openInventory(WLGui.getGuiFromName("ServerMenu").getInventory(player));
    }
}
