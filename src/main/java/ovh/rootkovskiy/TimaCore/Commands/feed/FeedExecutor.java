package ovh.rootkovskiy.TimaCore.Commands.feed;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            //code for feed me
            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.feed"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player p = (Player) sender;
            p.setFoodLevel(20);
            p.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            p.sendMessage("&9╚ &aВы успешно восстановили себе уровень голода!&r".replace("&", "§"));
            return true;

        } else {

            //code for feed other

            if (!(sender.hasPermission("timacore.feed.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length > 1) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /feed <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);

            if (p == null || !p.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            p.setFoodLevel(20);
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы успешно восстановили уровень голода игроку &r".replace("&", "§") + p.getName());
            p.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            p.sendMessage("&9╚ &aВам восстановили уровень голода!&r".replace("&", "§"));

        }

        return true;
    }

}
