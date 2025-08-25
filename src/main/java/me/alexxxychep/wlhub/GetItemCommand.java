package me.alexxxychep.wlhub;

import me.alexxxychep.wlhub.items.WLItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        String item = strings[0];
        player.getInventory().addItem(WLItem.getItemFromName(item).getItem(1));
        return true;
    }
}
