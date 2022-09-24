package ovh.rootkovskiy.TimaCore.dm;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class DmExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.dm"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length >= 2) {


            Player target = Bukkit.getPlayer(args[0]);
            StringBuilder message = new StringBuilder();

            if (target == sender) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cВы не можете отправлять сообщения самому себе!&r".replace("&", "§"));
                return true;
            }

            if (target == null || !target.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            for (int i = 1; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            if (message.length() == 0) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /dm <Ник игрока> <Сообщение>".replace("&", "§"));
                return true;
            }

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы отправили сообщение игроку &r".replace("&", "§") + target.getName());

            target.sendMessage("&9╔ &bВам пришло сообщение от игрока &r".replace("&", "§") + sender.getName());
            target.sendMessage(("&9╚ &r" + message + "&r").replace("&", "§"));

            for (Player spies : Main.socialspy) {
                spies.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                spies.sendMessage(("&9║ &aИгрок &r" + sender.getName() + " &aотправил сообщение игроку &r" + target.getName()).replace("&", "§"));
                spies.sendMessage(("&9╚ &r" + message).replace("&", "§"));
            }


        } else {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /dm <Ник игрока> <Сообщение>".replace("&", "§"));
        }
        return true;
    }
}
