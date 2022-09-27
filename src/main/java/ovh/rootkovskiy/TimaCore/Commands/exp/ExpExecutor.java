package ovh.rootkovskiy.TimaCore.Commands.exp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExpExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.exp"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /exp <Ник игрока> <Опыт>".replace("&", "§"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
            return true;
        }
        try {
            int exp = Integer.parseInt(args[1]);
            target.giveExpLevels(exp);
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage(("&9╚ &aВы выдали &r" + exp + " &aуровней опыта игроку &r" + target.getName()).replace("&", "§"));
            target.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            target.sendMessage(("&9╚ &aИгрок &r" + sender.getName() + "&a выдал вам &r" + exp + " &aуровней опыта!").replace("&", "§"));

        } catch (NumberFormatException nfe) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cМожно использовать только числа!&r".replace("&", "§"));
            return true;
        }

        return true;
    }
}
