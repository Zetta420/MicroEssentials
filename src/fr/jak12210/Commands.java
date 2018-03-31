package fr.jak12210;

import me.smessie.MultiLanguage.api.AdvancedMultiLanguageAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class Commands implements CommandExecutor {

    public boolean isNumeric(String s) {
        try {
            Long.parseLong(s);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String msg, String[] args) {






        if(sender instanceof Player){
            Player p = (Player) sender;
            if(command.getName().equalsIgnoreCase("title")){
                if(sender.hasPermission("microessentials.title")){
                    // Code for TITLE
                }
            }

            // Appel de la trad
            String prefix = Main.language(p);
            // Stockage de l'uuid en string
            String uuid = p.getUniqueId().toString();


            // -----  ECONOMIE  ----- //
            // Commande /money
            if(command.getName().equalsIgnoreCase("money") || command.getName().equalsIgnoreCase("balance") || command.getName().equalsIgnoreCase("argent")){
                String coins = String.valueOf(Main.getInstance().coins.getCoins(p.getPlayer()));
                p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".balance_pattern").replace("&", "§").replace("%money%", coins).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%name%", Main.getInstance().getConfig().getString("economy.name")));
                return true;
            }
            // Fin commande /money
            // Commande /addmoney
            if(command.getName().equalsIgnoreCase("addmoney")){
                if(sender.hasPermission("microessentials.economy.add")){
                    if(args.length == 2) {
                        if (!(Bukkit.getPlayer(args[0]) == null)) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target.isOnline()) {
                                if (isNumeric(args[1]) == true) {
                                    long coin = Long.valueOf(args[1]);
                                    Main.getInstance().coins.addCoins(target, coin);
                                    String gtarget = String.valueOf(target.getDisplayName());
                                    p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".send_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%target%", gtarget));
                                    target.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".recive_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")));
                                    return true;
                                }else{
                                    p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".not_numeric").replace("&", "§"));
                                }
                                } else {
                                    p.sendMessage(Main.getInstance().getConfig().getString( prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                    return true;
                                }

                        }else{
                            p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }else{
                        p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".syntax_add"));
                    }
                }else{
                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                }
            }
            // Fin commande /addmoney
            // Commande /delmoney
            if(command.getName().equalsIgnoreCase("delmoney")){
                if(sender.hasPermission("microessentials.economy.del")){
                    if(args.length == 2) {
                        if (!(Bukkit.getPlayer(args[0]) == null)) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target.isOnline()) {
                                if (isNumeric(args[1]) == true) {
                                long coin = Long.valueOf(args[1]);
                                Main.getInstance().coins.delCoins(target, coin);
                                String gtarget = String.valueOf(target.getDisplayName());
                                p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".remove_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%target%", gtarget));
                                target.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".removed_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")));
                                return true;
                                }else{
                                    p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".not_numeric").replace("&", "§"));
                                }
                            }else{
                                p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                return true;
                            }
                        }else{
                            p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }else{
                        p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".syntax_del"));
                    }
                }else{
                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                }
            }
            // Fin commande /delmoney
            // Commande /setmoney
            if(command.getName().equalsIgnoreCase("setmoney")){
                if(sender.hasPermission("microessentials.economy.set")){
                    if(args.length == 2) {
                        if (!(Bukkit.getPlayer(args[0]) == null)) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target.isOnline()) {
                                if (isNumeric(args[1]) == true) {
                                    long coin = Long.valueOf(args[1]);
                                    Main.getInstance().coins.setCoins(target, coin);
                                    String gtarget = String.valueOf(target.getDisplayName());
                                    p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".set_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%target%", gtarget));
                                    target.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".seted_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")));
                                    return true;
                                }else{
                                    p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".not_numeric").replace("&", "§"));
                                }
                            }else{
                                p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                return true;
                            }
                        }else{
                            p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }else{
                        p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".syntax_set").replace("&", "§"));
                    }
                }else{
                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                }
            }
            // Fin de la commande /setmoney
            // Commande /givemoney
            if(command.getName().equalsIgnoreCase("givemoney")){
                if(sender.hasPermission("microessentials.economy.give")){
                    if(args.length == 2) {
                        if (!(Bukkit.getPlayer(args[0]) == null)) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if(!target.equals(p)) {
                                if (target.isOnline()) {
                                    if (isNumeric(args[1]) == true) {
                                        long coin = Long.valueOf(args[1]);
                                        if (new Coins().giveCoins(p, target, coin).equalsIgnoreCase("oui")) {
                                            Main.getInstance().coins.giveCoins(p, target, coin);
                                            String gtarget = String.valueOf(target.getDisplayName());
                                            String ps = String.valueOf(p.getDisplayName());

                                            p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".give_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%target%", gtarget));
                                            target.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".gived_money").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol").replace("%target%", p.getDisplayName())));
                                            return true;
                                        } else if (new Coins().giveCoins(p, target, coin).equalsIgnoreCase("non")) {
                                            p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".not_enought".replace("&", "§")));
                                            return true;
                                        } else if (new Coins().giveCoins(p, target, coin).equalsIgnoreCase("pon")) {
                                            p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".too_low_send").replace("&", "§").replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")));
                                        }
                                    } else {
                                        p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix +  ".not_numeric").replace("&", "§"));
                                        return true;
                                    }
                                } else {
                                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                    return true;
                                }
                            }else{
                                p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".not_self").replace("&", "§"));
                            }
                        }else{
                            p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }else{
                        p.sendMessage(Main.getInstance().getConfig().getString("economy.messages." + prefix + ".syntax_give").replace("&", "§"));
                    }
                }else{
                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                }
            }
            // Fin de la commande /givemoney


            // Commande /broadcaster
            if(command.getName().equalsIgnoreCase("broadcaster")){
                if(p.hasPermission("microessentials.broadcast")){
                    if(Main.getInstance().broadcaster.contains(p.getUniqueId())) {
                        Main.getInstance().broadcaster.remove(p.getUniqueId());
                        p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".activebroadcast").replace("&", "§"));
                        return false;
                    }
                    Main.getInstance().broadcaster.add(p.getUniqueId());
                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".stoppedbroadcast").replace("&", "§"));
                }
            }
            // Fin de la commande /broadcaster


            // Commande /lang
            if(command.getName().equalsIgnoreCase("lang") || command.getName().equalsIgnoreCase("langue") || command.getName().equalsIgnoreCase("language")){

                if(args.length == 0){
                    if(prefix.equalsIgnoreCase("en")) {
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "FR");
                        p.sendMessage(Main.getInstance().getConfig().getString("general.langchangefr").replace("&", "§"));

                        return true;
                    }else if(prefix.equalsIgnoreCase("fr")){
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "EN");
                        p.sendMessage(Main.getInstance().getConfig().getString("general.langchangeen").replace("&", "§"));
                        return true;
                    }
                }else if(args.length == 1){
                    if((args[0].equalsIgnoreCase("fr")) || (args[0].equalsIgnoreCase("french")) || (args[0].equalsIgnoreCase("francais")) || (args[0].equalsIgnoreCase("français"))){
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "FR");
                        p.sendMessage(Main.getInstance().getConfig().getString("general.langchangefr").replace("&", "§"));
                        return true;
                    }else if((args[0].equalsIgnoreCase("en")) || (args[0].equalsIgnoreCase("english")) || (args[0].equalsIgnoreCase("anglais")) || (args[0].equalsIgnoreCase("us"))){
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "EN");
                        p.sendMessage(Main.getInstance().getConfig().getString("general.langchangeen").replace("&", "§"));
                        return true;
                    }
                }
                return false;
            }
            // Fin commande /lang


            // Commande /rules
            if(Main.getInstance().getConfig().getBoolean("rules.active") == true) {

                if (command.getName().equalsIgnoreCase("rules")) {
                    List<String> rules = Main.getInstance().getConfig().getStringList("rules.messages." + prefix);
                    for (int i = 0; i <= rules.size(); i++) {
                        for (String rl : Main.getInstance().getConfig().getStringList("rules.messages." + prefix)) {
                            p.sendMessage(rules.get(i).replace("&", "§"));
                            return true;
                        }
                    }
                }
            }
            // Fin commande /rules

        }
        return false;
    }

}
