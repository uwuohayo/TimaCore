package ovh.rootkovskiy.TimaCore.v;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ovh.rootkovskiy.TimaCore.Main;

public class VExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length <= 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.v"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /v <ON/OFF>".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;

            switch (args[0].toLowerCase()) {
                case "on":
                    for (Player plrs : Bukkit.getServer().getOnlinePlayers())
                        plrs.hidePlayer(player);
                    Main.vanished.add(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));

                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы вошли в Ваниш!&r".replace("&", "§"));
                    return true;
                case "off":
                    for (Player plrs : Bukkit.getServer().getOnlinePlayers())
                        plrs.showPlayer(player);
                    Main.vanished.remove(player);
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);

                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы вышли из Ваниша!&r".replace("&", "§"));
                    return true;
                default:
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aИспользование команды:&r /v <ON/OFF>".replace("&", "§"));
                    return true;
            }

        } else {

            if (!(sender.hasPermission("timacore.v.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length != 2) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /v <ON/OFF> <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);


            if (target == null || !target.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "on":
                    for (Player plrs : Bukkit.getServer().getOnlinePlayers())
                        plrs.hidePlayer(target);
                    Main.vanished.add(target);
                    target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));

                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы включили Ваниш игроку &r".replace("&", "§") + target.getName());
                    return true;
                case "off":
                    for (Player plrs : Bukkit.getServer().getOnlinePlayers())
                        plrs.showPlayer(target);
                    Main.vanished.remove(target);
                    target.removePotionEffect(PotionEffectType.INVISIBILITY);

                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы выключили Ваниш игроку &r".replace("&", "§") + target.getName());
                    return true;
                default:
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aИспользование команды:&r /v <ON/OFF> <Ник игрока>".replace("&", "§"));
                    return true;
            }

        }
    }

}
