package ovh.rootkovskiy.TimaCore.warpsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class SetwarpExecutor implements CommandExecutor {

    Main main = Main.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.setwarp"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /setwarp <Название>".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;
        String warpname = args[0];

        if (main.cfgwarps.getConfig().contains(warpname)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cВарп с таким названием уже существует!".replace("&", "§"));
            return true;
        }

        main.cfgwarps.getConfig().set(warpname + ".world", player.getLocation().getWorld().getName());
        main.cfgwarps.getConfig().set(warpname + ".x", player.getLocation().getBlockX());
        main.cfgwarps.getConfig().set(warpname + ".y", player.getLocation().getBlockY());
        main.cfgwarps.getConfig().set(warpname + ".z", player.getLocation().getBlockZ());
        main.cfgwarps.getConfig().set(warpname + ".yaw", player.getLocation().getYaw());
        main.cfgwarps.getConfig().set(warpname + ".pitch", player.getLocation().getPitch());
        main.cfgwarps.getConfig().set(warpname + ".owner_UUID", player.getUniqueId().toString());
        main.cfgwarps.getConfig().set(warpname + ".owner_nick", player.getName());
        main.cfgwarps.saveConfig();

        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9╚ &aВы создали варп &r".replace("&", "§") + warpname);

        return true;
    }

}
