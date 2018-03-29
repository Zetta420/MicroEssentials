package fr.jak12210;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;


    public Coins coins = new Coins();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        new EventsManager().registers(this);
        getCommand("rules").setExecutor(new Commands(this));
        getCommand("gm").setExecutor(new Gamemode(this));
        getCommand("gamemode").setExecutor((new Gamemode(this)));
        getCommand("lang").setExecutor((new Commands(this)));
        getCommand("langue").setExecutor(new Commands(this));
        getCommand("language").setExecutor(new Commands(this));
        // Connexion SQL
        String user = this.getConfig().getString("mysql.user");
        String db = this.getConfig().getString("mysql.database");
        String password = this.getConfig().getString("mysql.password");
        String host = this.getConfig().getString("mysql.host");
        String port = this.getConfig().getString("mysql.port");
        MySQL.connect(host, db, port, user, password);
        // FIN Connexion SQL

    }

    public void onDisable(){
        MySQL.close();

    }

    public static Main getInstance(){
        return instance;
    }

}
