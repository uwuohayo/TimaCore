package ovh.rootkovskiy.TimaCore.Commands.fireball;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class FireballExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.fireball"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player p = (Player) sender;

            Location location = p.getPlayer().getLocation().add(0, 3, 0);

            Fireball f = p.getPlayer().getWorld().spawn(location, Fireball.class);
            f.setYield(4.0f);
            f.setVelocity(p.getPlayer().getLocation().getDirection().multiply(0.7));

            p.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            p.sendMessage("&9╚ &aВы выстрелили файрболом!&r".replace("&", "§"));
        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /fireball".replace("&", "§"));

        }
        return true;
    }
}

