package fr.jak12210;

import me.smessie.MultiLanguage.api.AdvancedMultiLanguageAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private Main main;

    public Commands(Main main) {

        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String msg, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
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
            if(command.getName().equalsIgnoreCase("lang") || command.getName().equalsIgnoreCase("langue") || command.getName().equalsIgnoreCase("language")){
                if(args.length == 0){
                    if(language.equalsIgnoreCase("EN")) {
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "FR");
                        p.sendMessage(main.getConfig().getString("general.langchangefr").replace("&", "§"));

                        return true;
                    }else if(language.equalsIgnoreCase("FR")){
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "EN");
                        p.sendMessage(main.getConfig().getString("general.langchangeen").replace("&", "§"));
                        return true;
                    }
                }else if(args.length == 1){
                    if((args[0].equalsIgnoreCase("fr")) || (args[0].equalsIgnoreCase("french")) || (args[0].equalsIgnoreCase("francais")) || (args[0].equalsIgnoreCase("français"))){
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "FR");
                        p.sendMessage(main.getConfig().getString("general.langchangefr").replace("&", "§"));
                        return true;
                    }else if((args[0].equalsIgnoreCase("en")) || (args[0].equalsIgnoreCase("english")) || (args[0].equalsIgnoreCase("anglais")) || (args[0].equalsIgnoreCase("us"))){
                        AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, "EN");
                        p.sendMessage(main.getConfig().getString("general.langchangeen").replace("&", "§"));
                        return true;
                    }
                }
                return false;
            }

            if(main.getConfig().getBoolean("general.rules") == true) {

                if (command.getName().equalsIgnoreCase("rules")) {

                    p.sendMessage(main.getConfig().getString(prefix +".rules").replace("&", "§"));
                    return true;
                }
            }

        }
        return false;
    }
}
