package ovh.rootkovskiy.TimaCore.Commands.fly;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length <= 1) {

            //code for fly me
            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.fly"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aИспользование команды:&r /fly <ON/OFF>".replace("&", "§"));
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "on":
                    player.setAllowFlight(true);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы включили себе режим Полета&r".replace("&", "§"));
                    return true;
                case "off":
                    player.setAllowFlight(false);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы выключили себе режим Полета&r".replace("&", "§"));
                    return true;
                default:
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aИспользование команды:&r /fly <ON/OFF>".replace("&", "§"));
                    return true;
            }


        } else {

            //code for fly other

            if (!(sender.hasPermission("timacore.fly.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length > 2) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /fly <ON/OFF> <Ник игрока>".replace("&", "§"));
                return true;
            }

            Player player = Bukkit.getPlayer(args[1]);

            if (player == null || !player.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "on":
                    player.setAllowFlight(true);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам включили режим Полета&r".replace("&", "§"));
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы включили режим Полета для игрока &r".replace("&", "§") + player.getName());
                    return true;
                case "off":
                    player.setAllowFlight(false);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам выключили режим Полета&r".replace("&", "§"));
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы выключили режим Полета для игрока &r".replace("&", "§") + player.getName());
                    return true;
                default:
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aИспользование команды:&r /fly <ON/OFF> <Ник игрока>".replace("&", "§"));
                    return true;
            }

        }


    }

}