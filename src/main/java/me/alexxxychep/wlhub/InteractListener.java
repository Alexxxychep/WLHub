package me.alexxxychep.wlhub;

import me.alexxxychep.wlhub.items.WLItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().isLeftClick()) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir() && !WLItem.isWLItem(item)) {
            return;
        }

        WLItem wlItem = WLItem.getItemFromItemMeta(item.getItemMeta());

        if(wlItem instanceof Interactable) {
            ((Interactable) wlItem).onInteract(player);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        if(!(event.getClickedInventory().getHolder() instanceof Player player)) {
            return;
        }
        ItemStack item = event.getCursor();
        if (item.getType().isAir() && !WLItem.isWLItem(item)) {
            return;
        }

        WLItem wlItem = WLItem.getItemFromItemMeta(item.getItemMeta());
        if(wlItem instanceof GuiInteractable) {
            ((GuiInteractable) wlItem).onInteract(player);
            event.setCancelled(true);
        }

        if(wlItem instanceof Immovable) {
            event.setCancelled(true);
        }
    }

}
