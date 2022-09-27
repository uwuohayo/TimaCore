package ovh.rootkovskiy.TimaCore.Commands.gm;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmExecutor implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (args.length >= 2) {

            if (!(sender.hasPermission("timacore.gm.other"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = Bukkit.getPlayer(args[1]);

            if (args.length > 2) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &aИспользование команды:&r /gm <0/1/2/3> <Ник игрока>".replace("&", "§"));
                return true;
            }


            if (player == null || !player.isOnline()) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "0":
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам установили режим Выживания!&r".replace("&", "§"));
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы установили режим Выживания для игрока &r".replace("&", "§") + player.getName());
                    return true;
                case "1":
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам установили режим Креатив!&r".replace("&", "§"));
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы установили режим Креатив для игрока &r".replace("&", "§") + player.getName());
                    return true;
                case "2":
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам установили режим Приключений!&r".replace("&", "§"));
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы установили режим Приключений для игрока &r".replace("&", "§") + player.getName());
                    return true;
                case "3":
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВам установили режим Спектатор!&r".replace("&", "§"));
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы установили режим Спектатор для игрока &r".replace("&", "§") + player.getName());
                    return true;
                default:
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aИспользование команды:&r /gm <0/1/2/3> <Ник игрока>".replace("&", "§"));
                    return true;
            }

        } else {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }
            if (!(sender.hasPermission("timacore.gm"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            Player player = (Player) sender;

            if (args.length != 1) {

                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aИспользование команды:&r /gm <0/1/2/3>".replace("&", "§"));
                return true;

            }

            switch (args[0].toLowerCase()) {
                case "0":
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы установили себе режим Выживание!&r".replace("&", "§"));
                    return true;
                case "1":
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы установили себе режим Креатив!&r".replace("&", "§"));
                    return true;
                case "2":
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы установили себе режим Приключений!&r".replace("&", "§"));
                    return true;
                case "3":
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aВы установили себе режим Спектатор!&r".replace("&", "§"));
                    return true;
                default:
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &aИспользование команды:&r /gm <0/1/2/3>".replace("&", "§"));
                    return true;
            }
        }


    }

}
