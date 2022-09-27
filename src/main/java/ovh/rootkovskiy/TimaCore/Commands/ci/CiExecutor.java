package ovh.rootkovskiy.TimaCore.Commands.ci;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CiExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.ci"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;

            player.getInventory().clear();
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &aВы очистили себе инвентарь! &r".replace("&", "§"));
        } else {

            if (!(sender.hasPermission("timacore.ci.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /ci <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);

            if (player == null || !player.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            player.getInventory().clear();
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &aВам очистили инвентарь! &r".replace("&", "§"));
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы очистили инвентарь игроку &r".replace("&", "§") + player.getName());

        }
        return true;
    }
}
