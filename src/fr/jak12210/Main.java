package fr.jak12210;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.smessie.MultiLanguage.api.AdvancedMultiLanguageAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main extends JavaPlugin {

    private static Main instance;


    public Coins coins = new Coins();
    private List<String> list;
    public ArrayList<UUID> broadcaster = new ArrayList<>();
    public String pluginFolder = getDataFolder().getAbsolutePath();

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
        getCommand("setmoney").setExecutor(new Commands());
        getCommand("givemoney").setExecutor(new Commands());
        getCommand("broadcaster").setExecutor(new Commands());
        // Connexion SQL
        String user = this.getConfig().getString("mysql.user");
        String db = this.getConfig().getString("mysql.database");
        String password = this.getConfig().getString("mysql.password");
        String host = this.getConfig().getString("mysql.host");
        String port = this.getConfig().getString("mysql.port");
        MySQL.connect(host, db, port, user, password);
        Boolean actif = Main.getInstance().getConfig().getBoolean("autobroadcast.active");
        if(actif == true) {
            autoBroadcast();
        }
        // FIN Connexion SQL


    }
    public static String language(Player p){
        String uuid = p.getUniqueId().toString();
        String language = AdvancedMultiLanguageAPI.getLanguageOfUuid(uuid);
        if(language.equalsIgnoreCase("FR")) {
            return "fr";
        }else{
            return "en";
        }
    }

    public void autoBroadcast(){
            int tics = Main.getInstance().getConfig().getInt("autobroadcast.time") * 20;
            String prefix = Main.getInstance().getConfig().getString("autobroadcast.prefix").replace("&", "§");
            Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(!Main.getInstance().broadcaster.contains(p.getUniqueId())) {
                        Random r = new Random();
                        String prx = language(p);
                        this.list = this.getConfig().getStringList("autobroadcast.messages." + prx);
                        int msg = r.nextInt(list.size());
                        String mots = list.get(msg);
                        p.sendMessage(prefix + mots.replace("&", "§"));
                    }
                }
            }, tics, tics);
    }

    public void onDisable(){
        MySQL.close();

    }


    public static Main getInstance(){
        return instance;
    }

}
