package ovh.rootkovskiy.TimaCore.tp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length <= 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.tp"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /tp <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            Player player = (Player) sender;

            if (target == null || !target.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            Location targetloc = target.getLocation();
            player.teleport(targetloc);
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &aВы телепортировались к игроку &r".replace("&", "§") + target.getName());
            target.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            target.sendMessage("&9╚ &aК вам телепортировался игрок &r".replace("&", "§") + player.getName());
            return true;

        } else {

            if (!(sender.hasPermission("timacore.tp.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length != 2) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /tp <Ник игрока> <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player target1 = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);

            if (target1 == null || !target1.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока &r" + target1.getName() + "&c нет на сервере!&r".replace("&", "§"));
                return true;
            }

            if (target2 == null || !target2.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока &r" + target2.getName() + "&c нет на сервере!&r".replace("&", "§"));
                return true;
            }

            Location targetloc2 = target2.getLocation();
            target1.teleport(targetloc2);
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы телепортировали игрока &r".replace("&", "§") + target1.getName() + "&a к игроку &r".replace("&", "§") + target2.getName());
        }

        return true;
    }

}
