package me.alexxxychep.wlhub.items;

import me.alexxxychep.wlhub.Immovable;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class GuiGlassFrameItem extends WLItem implements Immovable {
    private final Material glass;

    public GuiGlassFrameItem() {
        super("GuiGlassFrame");
        this.glass = Material.GREEN_STAINED_GLASS_PANE;
    }

    public GuiGlassFrameItem(Material glass) {
        super("GuiGlassFrame");
        this.glass = glass;
    }

    @Override
    public Material getMaterial() {
        return glass;
    }

    @Override
    public Component getDisplayName() {
        return Component.empty();
    }
}
