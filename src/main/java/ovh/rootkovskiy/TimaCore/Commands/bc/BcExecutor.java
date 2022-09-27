package ovh.rootkovskiy.TimaCore.Commands.bc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BcExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.bc"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /bc <Объявление>".replace("&", "§"));
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        Player p = (Player) sender;


        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9╚ &aВаше объявление успешно отправлено!".replace("&", "§"));


        Bukkit.broadcastMessage("&9╔ &bОбъявление игрока: &r".replace("&", "§") + p.getName());
        Bukkit.broadcastMessage(("&9╚ &r" + message).replace("&", "§"));


        return true;

    }

}