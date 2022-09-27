package ovh.rootkovskiy.TimaCore.Commands.sun;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SunExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.sun"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /sun".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;
        player.getWorld().setWeatherDuration(0);
        player.getWorld().setStorm(false);
        player.getWorld().setThundering(false);
        player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        player.sendMessage("&9╚ &aВы поставили Солнечную погоду в мире: &r".replace("&", "§") + player.getWorld().getName());
        Bukkit.broadcastMessage("&9╔ &bИгрок: &r".replace("&", "§") + player.getName());
        Bukkit.broadcastMessage("&9╚ &aУстановил Солнечную погоду в мире: &r".replace("&", "§") + player.getWorld().getName());

        return true;
    }

}
