package ovh.rootkovskiy.TimaCore.burn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BurnExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.burn"))) {
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

            if (target == sender) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cВы не можете поджечь самого себя!&r".replace("&", "§"));
                return true;
            }

            target.setFireTicks(30 * 20);

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage(("&9╚ &aВы подожгли игрока &r" + target.getName() + " &aна &r" + 30 + " сек").replace("&", "§"));

            target.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            target.sendMessage(("&9╚ &aВас поджег игрок &r" + sender.getName() + " &aна &r" + 30 + " сек").replace("&", "§"));
        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /burn <Ник игрока>".replace("&", "§"));
        }
        return true;
    }
}
