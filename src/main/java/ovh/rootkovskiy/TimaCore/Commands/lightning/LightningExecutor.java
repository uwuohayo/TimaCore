package ovh.rootkovskiy.TimaCore.Commands.lightning;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LightningExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.lightning"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            final Player player = (Player) sender;
            final Block block = player.getTargetBlock(null, 75);

            if (block != null) {
                final Location location = block.getLocation();
                player.getWorld().strikeLightning(location);
                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aВы ударили молнией!&r".replace("&", "§"));
                return true;
            }
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &cВы не смогли ударить молнией, так как вы не смотрите на блок!&r".replace("&", "§"));
            return true;
        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /lightning".replace("&", "§"));
            return true;

        }
    }
}
