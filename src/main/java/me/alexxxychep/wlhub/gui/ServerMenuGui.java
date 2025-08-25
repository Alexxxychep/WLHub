package me.alexxxychep.wlhub.gui;

import me.alexxxychep.wlhub.GuiUpdatable;
import me.alexxxychep.wlhub.items.GuiGlassFrameItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ServerMenuGui extends WLGui implements GuiUpdatable {
    private static final int MAX_SLOTS_IN_ONE_LINE = 9;
    private static final int MAX_SLOTS_LINES = 5;
    private static final int MAX_SLOTS = MAX_SLOTS_IN_ONE_LINE * MAX_SLOTS_LINES;

    private static final ServerMenuGui instance = new ServerMenuGui();

    public ServerMenuGui() {
        super("ServerMenu", "Меню Серверов");
    }

    public static ServerMenuGui getInstance() {
        return instance;
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack[] result = new ItemStack[MAX_SLOTS];
        // Color first line
        for (int i = 0; i < MAX_SLOTS_IN_ONE_LINE; i++) {
            ItemStack itemStack = new GuiGlassFrameItem().getItem(1);
            itemStack.setType(Material.ORANGE_STAINED_GLASS_PANE);
            result[i] = itemStack;
        }

        // Color last line
        for (int i = MAX_SLOTS_IN_ONE_LINE * (MAX_SLOTS_LINES - 1) - 1; i < MAX_SLOTS; i++) {
            ItemStack itemStack = new GuiGlassFrameItem().getItem(1);
            itemStack.setType(Material.GRAY_STAINED_GLASS_PANE);
            result[i] = itemStack;
        }

        return result;
    }
}
