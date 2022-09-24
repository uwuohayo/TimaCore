package ovh.rootkovskiy.TimaCore.list;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {

    public String getPlayers() {
        final StringBuilder sb = new StringBuilder();
        for (final Player target : Bukkit.getOnlinePlayers()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(target.getDisplayName());
        }
        return sb.toString();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.list"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /list".replace("&", "§"));
            return true;
        }

        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9║ &aИгроков на сервере: &r".replace("&", "§") + Bukkit.getOnlinePlayers().size());
        sender.sendMessage("&9╚ &aСписок игроков &b↓&r\n".replace("&", "§") + getPlayers());
        return true;
    }
}
