package ovh.rootkovskiy.TimaCore.Commands.ping;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Utils.ReflectionUtils;

public class PingExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.ping"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /ping <Ник игрока>".replace("&", "§"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
            return true;
        }


        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage(("&9╚ &aПинг игрока &r" + target.getName() + " &a- &r" + ReflectionUtils.getFieldContent(ReflectionUtils.getHandle(target), "ping") + " ms").replace("&", "§"));
        return true;
    }

}