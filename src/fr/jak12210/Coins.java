package fr.jak12210;


import com.mysql.jdbc.Driver;
import org.bukkit.entity.Player;


import java.math.BigInteger;
import java.sql.*;


public class Coins {

    public void createAccount(Player p){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
            sts.setString(1, p.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(!rs.next()){
                sts.close();
                sts = MySQL.getConnection().prepareStatement("INSERT INTO coins (player_uuid, coins) VALUES (?, ?)");
                sts.setString(1, p.getUniqueId().toString());
                sts.setInt(2, Main.getInstance().getConfig().getInt("economy.start"));
                sts.executeUpdate();

                System.out.println("Le joueur " + p.getDisplayName() + " viens d etre inscrit dans la bdd");
            }
        }
        catch (SQLException e){
            System.out.println("Erreur lors de l inscription de " + p.getDisplayName() + " dans la bdd");
            e.printStackTrace();
        }
    }

    public long getCoins(Player p){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT * FROM coins WHERE player_uuid=?");
            sts.setString(1, p.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if(rs.next()){
                return rs.getLong("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addCoins(Player p, long coins){
        if(coins < 1) return;
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
            sts.setString(1, p.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if(rs.next()){
                long money = rs.getLong("coins");
                sts.close();
                sts = MySQL.getConnection().prepareStatement("UPDATE coins SET coins=? WHERE player_uuid=?");
                sts.setLong(1, (coins + money));
                sts.setString(2, p.getUniqueId().toString());
                sts.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delCoins(Player player, long coins){
        if(coins < 1) return;
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if(rs.next()){
                long money = rs.getLong("coins");
                sts.close();

                if((money - coins) < 0){
                    coins = money;
                }

                sts = MySQL.getConnection().prepareStatement("UPDATE coins SET coins=? WHERE player_uuid=?");
                sts.setLong(1, (money - coins));
                sts.setString(2, player.getUniqueId().toString());
                sts.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setCoins(Player player, long coins){
        if(coins < 1) return;
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();

            if(rs.next()){
                sts.close();

                sts = MySQL.getConnection().prepareStatement("UPDATE coins SET coins=? WHERE player_uuid=?");
                sts.setLong(1, (coins));
                sts.setString(2, player.getUniqueId().toString());
                sts.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String giveCoins(Player p, Player s, long coins){
        if(coins < 1) return "pon";
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
            sts.setString(1, p.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            try {
                PreparedStatement st = MySQL.getConnection().prepareStatement("SELECT coins FROM coins WHERE player_uuid=?");
                st.setString(1, s.getUniqueId().toString());
                ResultSet rst = st.executeQuery();
                if(rs.next() && rst.next()){
                    long moneyp = rs.getLong("coins");
                    long moneys = rst.getLong("coins");
                    sts.close();
                    if(moneyp >= coins) {
                        st = MySQL.getConnection().prepareStatement("UPDATE coins SET coins=? WHERE player_uuid=?");
                        sts = MySQL.getConnection().prepareStatement("UPDATE coins SET coins=? WHERE player_uuid=?");
                        sts.setLong(1, (coins + moneys));
                        sts.setString(2, s.getUniqueId().toString());
                        st.setLong(1, (moneyp - coins));
                        st.setString(2, p.getUniqueId().toString());
                        sts.executeUpdate();
                        st.executeUpdate();
                        return "oui";
                    }else{
                        return "non";
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


}
