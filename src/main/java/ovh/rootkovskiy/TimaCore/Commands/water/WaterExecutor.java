package ovh.rootkovskiy.TimaCore.Commands.water;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaterExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.water"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            target.setFireTicks(0);

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage(("&9╚ &aВы потушили игрока &r" + target.getName()).replace("&", "§"));

            target.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            target.sendMessage(("&9╚ &aВас потушил игрок &r" + sender.getName()).replace("&", "§"));

            return true;
        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /water <Ник игрока>".replace("&", "§"));
            return true;

        }

    }
}