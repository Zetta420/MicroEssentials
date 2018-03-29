package fr.jak12210;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main extends JavaPlugin {

    private static Main instance;


    public Coins coins = new Coins();
    private List<String> list;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        new EventsManager().registers(this);
        getCommand("rules").setExecutor(new Commands());
        getCommand("gm").setExecutor(new Gamemode());
        getCommand("gamemode").setExecutor((new Gamemode()));
        getCommand("lang").setExecutor((new Commands()));
        getCommand("langue").setExecutor(new Commands());
        getCommand("language").setExecutor(new Commands());
        getCommand("addmoney").setExecutor(new Commands());
        getCommand("delmoney").setExecutor(new Commands());
        getCommand("money").setExecutor(new Commands());

        // Connexion SQL
        String user = this.getConfig().getString("mysql.user");
        String db = this.getConfig().getString("mysql.database");
        String password = this.getConfig().getString("mysql.password");
        String host = this.getConfig().getString("mysql.host");
        String port = this.getConfig().getString("mysql.port");
        MySQL.connect(host, db, port, user, password);
        this.list = this.getConfig().getStringList("autobroadcast");
        autoBroadcast();
        // FIN Connexion SQL


    }

    public void autoBroadcast(){
        int random = new Random().nextInt(list.size());
        String randomMessage = list.get(random);
        System.out.println(randomMessage);
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            Bukkit.broadcastMessage(randomMessage);
        }, 200, 200);
    }

    public void onDisable(){
        MySQL.close();

    }


    public static Main getInstance(){
        return instance;
    }

}
