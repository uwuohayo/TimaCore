package ovh.rootkovskiy.TimaCore.kill;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillExecutor implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (args.length == 0) {

            //code for kill me
            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.kill"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player p = (Player) sender;
            p.setHealth(0);
            p.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            p.sendMessage("&9╚ &aВы успешно убили себя!&r".replace("&", "§"));
            return true;

        } else {

            //code for kill other

            if (!(sender.hasPermission("timacore.kill.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length > 1) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /kill <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);

            if (p == null || !p.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            p.setHealth(0);
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы успешно убили игрока &r".replace("&", "§") + p.getName());
            p.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            p.sendMessage("&9╚ &aВас убил игрок &r".replace("&", "§") + sender.getName());

        }

        return true;
    }
}
