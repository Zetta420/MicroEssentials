package fr.jak12210;

import me.smessie.MultiLanguage.api.AdvancedMultiLanguageAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

            // Systeme de traduction
            String uuid = p.getUniqueId().toString();
            String language = AdvancedMultiLanguageAPI.getLanguageOfUuid(uuid);
            String prefix;
            if(language.equalsIgnoreCase("EN")) {
                prefix = "en";
            }else if(language.equalsIgnoreCase("FR")){
                prefix = "fr";
            }else{
                prefix = "en";
            }
            // Fin systeme de traduction

            // -----  ECONOMIE  ----- //
            // Commande /money
            if(command.getName().equalsIgnoreCase("money") || command.getName().equalsIgnoreCase("balance") || command.getName().equalsIgnoreCase("argent")){
                String coins = String.valueOf(Main.getInstance().coins.getCoins(p.getPlayer()));
                p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".moneystyle").replace("&", "§").replace("%money%", coins).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%name%", Main.getInstance().getConfig().getString("economy.name")));
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
                                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".sendmoney").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%target%", gtarget));
                                    target.sendMessage(Main.getInstance().getConfig().getString(prefix + ".recivemoney").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")));
                                    return true;
                                }else{
                                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".neednumeric").replace("&", "§"));
                                }
                                } else {
                                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                    return true;
                                }

                        }else{
                            p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }
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
                                p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".removemoney").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")).replace("%target%", gtarget));
                                target.sendMessage(Main.getInstance().getConfig().getString(prefix + ".removedmoney").replace("&", "§").replace("%money%", args[1]).replace("%symbol%", Main.getInstance().getConfig().getString("economy.symbol")));
                                return true;
                                }else{
                                    p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".neednumeric").replace("&", "§"));
                                }
                            }else{
                                p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                return true;
                            }
                        }else{
                            p.sendMessage(Main.getInstance().getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }
                }
            }
            // Fin commande /delmoney
            if(command.getName().equalsIgnoreCase("lang") || command.getName().equalsIgnoreCase("langue") || command.getName().equalsIgnoreCase("language")){
                if(args.length == 0){
                    if(language.equalsIgnoreCase("EN")) {
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "FR");
                        p.sendMessage(Main.getInstance().getConfig().getString("general.langchangefr").replace("&", "§"));

                        return true;
                    }else if(language.equalsIgnoreCase("FR")){
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

            if(Main.getInstance().getConfig().getBoolean("general.rules") == true) {

                if (command.getName().equalsIgnoreCase("rules")) {

                    p.sendMessage(Main.getInstance().getConfig().getString(prefix +".rules").replace("&", "§"));
                    return true;
                }
            }

        }
        return false;
    }

}
