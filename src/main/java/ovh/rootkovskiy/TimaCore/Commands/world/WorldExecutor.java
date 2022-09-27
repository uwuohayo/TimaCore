package ovh.rootkovskiy.TimaCore.Commands.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.world"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;
            World target = Bukkit.getWorld(args[0]);

            if (!(Bukkit.getServer().getWorlds().contains(target))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cТакого мира не существует!&r".replace("&", "§"));
                sender.sendMessage(("&9⦑ &bСписок миров: &9⦒&r").replace("&", "§"));
                for (World world : Bukkit.getServer().getWorlds()) {
                    sender.sendMessage(world.getName());
                }
                return true;
            }

            Location tp = new Location(target, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
            player.teleport(tp);

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы телепортированы в мир &r".replace("&", "§") + target.getName());

            return true;


        } else {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /world <Мир>".replace("&", "§"));
            return true;
        }
    }
}
