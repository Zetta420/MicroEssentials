package fr.jak12210;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Main main;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
    main.getInstance().coins.createAccount(p);
    p.getInventory().clear();
    }

}
