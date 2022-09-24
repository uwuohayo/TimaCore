package ovh.rootkovskiy.TimaCore.ptime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PtimeExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.ptime"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length == 1) {

            Player player = (Player) sender;

            Long time;

            try {
                time = Long.parseLong(args[0]);
            } catch (NumberFormatException nfe) {
                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &cМожно использовать только числа!&r".replace("&", "§"));
                return true;
            }
            player.setPlayerTime(time, true);
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &aВаше личное время &r".replace("&", "§") + time);

            return true;
        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /ptime <Время>".replace("&", "§"));
            return true;

        }

    }
}
