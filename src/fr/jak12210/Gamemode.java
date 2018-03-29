package fr.jak12210;

import me.smessie.MultiLanguage.api.AdvancedMultiLanguageAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    private Main main;

    public Gamemode(Main main) {

        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String msg, String[] args) {

        if(sender instanceof Player) {
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
            if((command.getName().equalsIgnoreCase("gm")) || command.getName().equalsIgnoreCase("gamemode")){
                if(args.length == 0) {
                    if(p.hasPermission("epicwars.gamemode.self")) {
                        if (p.getGameMode() == GameMode.CREATIVE) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(main.getConfig().getString(prefix + ".survivalgm").replace("&", "§"));

                            return true;
                        } else {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(main.getConfig().getString(prefix + ".creativegm").replace("&", "§"));
                            return true;
                        }
                    }else{
                        p.sendMessage(main.getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                    }
                }else if(args.length == 1){
                    if(p.hasPermission("epicwars.gamemode.self")) {
                        if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative")) || (args[0].equalsIgnoreCase("crea")) || (args[0].equalsIgnoreCase("creatif"))) {
                            if (p.getGameMode() == GameMode.CREATIVE) {
                                p.sendMessage(main.getConfig().getString(prefix + ".alreadycrea").replace("&", "§"));
                                return true;
                            } else {
                                p.setGameMode(GameMode.CREATIVE);
                                p.sendMessage(main.getConfig().getString(prefix + ".creativegm").replace("&", "§"));
                                return true;
                            }
                        } else if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survie")) || (args[0].equalsIgnoreCase("survival")) || (args[0].equalsIgnoreCase("surv"))) {
                            if (p.getGameMode() == GameMode.SURVIVAL) {
                                p.sendMessage(main.getConfig().getString(prefix + ".alreadysurv").replace("&", "§"));
                                return true;
                            } else {
                                p.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(main.getConfig().getString(prefix + ".survivalgm").replace("&", "§"));
                                return true;
                            }
                        } else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("adventure")) || (args[0].equalsIgnoreCase("aventure")) || (args[0].equalsIgnoreCase("adv"))) {
                            if (p.getGameMode() == GameMode.ADVENTURE) {
                                p.sendMessage(main.getConfig().getString(prefix + ".alreadyadv").replace("&", "§"));
                                return true;
                            } else {
                                p.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(main.getConfig().getString(prefix + ".adventuregm").replace("&", "§"));
                                return true;
                            }
                        }
                    }else{
                        p.sendMessage(main.getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                    }
                }else{
                    if(p.hasPermission("epicwars.gamemode.others")) {
                        String player = args[1].toString();
                        if (!(Bukkit.getPlayer(args[1]) == null)) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target.isOnline()) {
                                if ((args[0].equalsIgnoreCase("1")) || (args[0].equalsIgnoreCase("creative")) || (args[0].equalsIgnoreCase("crea")) || (args[0].equalsIgnoreCase("creatif"))) {
                                    target.setGameMode(GameMode.CREATIVE);
                                    target.sendMessage(main.getConfig().getString(prefix + ".setcreagm").replace("&", "§"));
                                    p.sendMessage(main.getConfig().getString(prefix + ".setadmcreagm").replace("&", "§").replace("%target%", target.getDisplayName()));
                                    return true;
                                } else if ((args[0].equalsIgnoreCase("0")) || (args[0].equalsIgnoreCase("survie")) || (args[0].equalsIgnoreCase("survival")) || (args[0].equalsIgnoreCase("surv"))) {
                                    target.setGameMode(GameMode.SURVIVAL);
                                    target.sendMessage(main.getConfig().getString(prefix + ".setsurvgm").replace("&", "§"));
                                    p.sendMessage(main.getConfig().getString(prefix + ".setadmsurvgm").replace("&", "§").replace("%target%", target.getDisplayName()));
                                    return true;
                                } else if ((args[0].equalsIgnoreCase("3")) || (args[0].equalsIgnoreCase("adventure")) || (args[0].equalsIgnoreCase("aventure")) || (args[0].equalsIgnoreCase("adv"))) {
                                    target.setGameMode(GameMode.ADVENTURE);
                                    target.sendMessage(main.getConfig().getString(prefix + ".setadvgm").replace("&", "§"));
                                    p.sendMessage(main.getConfig().getString(prefix + ".setadmadvgm").replace("&", "§").replace("%target%", target.getDisplayName()));
                                    return true;
                                }
                            } else {
                                p.sendMessage(main.getConfig().getString(prefix + ".playeroffline").replace("&", "§").replace("%target%", args[1]));
                                return true;
                            }
                        } else {
                            p.sendMessage(main.getConfig().getString(prefix + ".playernotfound").replace("&", "§").replace("%target%", args[1]));
                            return true;
                        }
                    }else{
                        p.sendMessage(main.getConfig().getString(prefix + ".nopermission").replace("&", "§"));
                    }
                }
            }
        }
        return false;
    }
}
