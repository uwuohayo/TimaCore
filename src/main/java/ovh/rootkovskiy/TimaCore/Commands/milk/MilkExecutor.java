package ovh.rootkovskiy.TimaCore.Commands.milk;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MilkExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.milk"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;

            player.getActivePotionEffects().forEach(e -> player.removePotionEffect(e.getType()));

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы сняли с себя все эффекты!".replace("&", "§"));

            return true;
        } else {

            if (!(sender.hasPermission("timacore.milk.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /milk <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            target.getActivePotionEffects().forEach(e -> target.removePotionEffect(e.getType()));

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы сняли все эффекты с игрока &r".replace("&", "§") + target.getName());

            target.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            target.sendMessage("&9╚ &aС вас снял все эффекты игрок &r".replace("&", "§") + sender.getName());

            return true;

        }
    }
}
