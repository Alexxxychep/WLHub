package me.alexxxychep.wlhub;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class Main extends JavaPlugin {
    private static Main instance;

    public Main()
    {
        super();
    }

    protected Main(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getCommand("getWLItem").setExecutor(new GetItemCommand());
        getCommand("openGui").setExecutor(new OpenGuiCommand());
    }

    public static Main getInstance() {
        return instance;
    }
}
