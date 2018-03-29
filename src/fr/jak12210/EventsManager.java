package fr.jak12210;

import fr.jak12210.Main;
import fr.jak12210.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Alexis on 23/07/2017.
 */
public class EventsManager {

    public void registers(Main main){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), main);
    }
}