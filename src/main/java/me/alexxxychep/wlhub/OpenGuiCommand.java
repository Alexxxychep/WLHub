package me.alexxxychep.wlhub;

import me.alexxxychep.wlhub.gui.WLGui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenGuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        String name = strings[0];

        WLGui wlGui = WLGui.getGuiFromName(name);
        player.openInventory(wlGui.getInventory(player));

        return true;
    }
}
