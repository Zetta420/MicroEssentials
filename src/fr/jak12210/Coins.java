package fr.jak12210;


import com.mysql.jdbc.Driver;
import org.bukkit.entity.Player;


import java.sql.*;


public class Coins {

    private Main main;

    public void createAccount(Player p){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
            sts.setString(1, p.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(!rs.next()){
                sts.close();
                sts = MySQL.getConnection().prepareStatement("INSERT INTO coins (player_uuid, coins) VALUES (?, ?)");
                sts.setString(1, p.getUniqueId().toString());
                sts.setInt(2, main.getConfig().getInt("economy.start"));
                sts.executeUpdate();

                System.out.println("Le joueur " + p.getDisplayName() + " viens d etre inscrit dans la bdd");
            }
        }
        catch (SQLException e){
            System.out.println("Erreur lors de l inscription de " + p.getDisplayName() + " dans la bdd");
            e.printStackTrace();
        }
    }


}
