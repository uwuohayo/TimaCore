package ovh.rootkovskiy.TimaCore.Commands.tppos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpposExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.tppos"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 3) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /tppos <X> <Y> <Z>".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;


        try {

            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            final Location toLoc = new Location(player.getWorld(), x, y, z);
            player.teleport(toLoc);
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &aВы телепортировались на следующие координаты &r".replace("&", "§"));
            player.sendMessage(("&2Мир: " + player.getWorld().getName() + "&2&l X: " + x + " Y: " + y + " Z: " + z).replace("&", "§"));
            return true;

        } catch (NumberFormatException nfe) {
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &cМожно использовать только числа!&r".replace("&", "§"));
            return true;
        }

    }
}
