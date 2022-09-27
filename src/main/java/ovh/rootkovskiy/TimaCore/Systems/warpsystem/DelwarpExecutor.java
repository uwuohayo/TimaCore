package ovh.rootkovskiy.TimaCore.Systems.warpsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class DelwarpExecutor implements CommandExecutor {

    Main main = Main.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.delwarp"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /delwarp <Название>".replace("&", "§"));
            return true;
        }

        String warpname = args[0];
        Player player = (Player) sender;

        if (!(main.cfgwarps.getConfig().contains(warpname))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cТакого варпа не существует!".replace("&", "§"));
            return true;
        }

        if (main.cfgwarps.getConfig().getString(warpname + ".owner_UUID").equals(player.getUniqueId().toString()) || player.hasPermission("timacore.admin")) {

            main.cfgwarps.getConfig().set(warpname, null);
            main.cfgwarps.saveConfig();

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы удалили варп &r".replace("&", "§") + warpname);

            return true;

        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cВы не можете удалить чужой варп!&r".replace("&", "§"));
            return true;

        }

    }

}
