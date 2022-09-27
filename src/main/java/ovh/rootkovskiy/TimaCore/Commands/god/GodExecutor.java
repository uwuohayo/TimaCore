package ovh.rootkovskiy.TimaCore.Commands.god;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GodExecutor implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (args.length <= 1) {

            //code for god me
            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.god"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;

            if (args.length != 1) {
                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aИспользование команды:&r /god <ON/OFF>".replace("&", "§"));
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "on":
                    player.setInvulnerable(true);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы включили себе режим Бога&r".replace("&", "§"));
                    return true;
                case "off":
                    player.setInvulnerable(false);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы выключили себе режим Бога&r".replace("&", "§"));
                    return true;
                default:
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aИспользование команды:&r /god <ON/OFF>".replace("&", "§"));
                    return true;
            }


        } else {

            //code for god other

            if (!(sender.hasPermission("timacore.god.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            if (args.length > 2) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /god <ON/OFF> <Ник игрока>".replace("&", "§"));
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
                    player.setInvulnerable(true);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам включил режим Бога игрок &r".replace("&", "§") + sender.getName());
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы включили режим Бога для игрока &r".replace("&", "§") + player.getName());
                    return true;
                case "off":
                    player.setInvulnerable(false);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам выключил режим Бога игрок &r".replace("&", "§") + sender.getName());
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы выключили режим Бога для игрока &r".replace("&", "§") + player.getName());
                    return true;
                default:
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aИспользование команды:&r /god <ON/OFF> <Ник игрока>".replace("&", "§"));
                    return true;
            }

        }


    }
}