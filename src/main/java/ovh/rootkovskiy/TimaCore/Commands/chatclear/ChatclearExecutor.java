package ovh.rootkovskiy.TimaCore.Commands.chatclear;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatclearExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.chatclear"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /chatclear".replace("&", "§"));
            return true;
        }

        for (int i = 0; i < 200; i++) {
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage("&9╔ &bСистема&r".replace("&", "§"));
        Bukkit.broadcastMessage("&9╚ &aПроизошла очистка чата".replace("&", "§"));
        return true;
    }
}
