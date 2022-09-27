package ovh.rootkovskiy.TimaCore.Commands.getpos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetposExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.getpos"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /getpos <Ник игрока>".replace("&", "§"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
            return true;
        }

        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9║ &aКоординаты игрока &r".replace("&", "§") + target.getName());
        sender.sendMessage(("&9╚ &2Мир: " + target.getWorld().getName() + " &2&lX: " + target.getLocation().getBlockX() + " &2&lY: " + target.getLocation().getBlockY() + " &2&lZ: " + target.getLocation().getBlockZ()).replace("&", "§"));
        return true;
    }
}
