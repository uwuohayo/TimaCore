package ovh.rootkovskiy.TimaCore.servertps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ovh.rootkovskiy.TimaCore.LagUtils;

public class ServertpsExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.tps"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /servertps".replace("&", "§"));
            return true;
        }

        String result = String.format("%.2f", LagUtils.getTPS());

        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9╚ &aТпс сервера &r".replace("&", "§") + result);
        return true;
    }

}
