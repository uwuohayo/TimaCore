package ovh.rootkovskiy.TimaCore.Systems.spawnsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class SetspawnExecutor implements CommandExecutor {

    Main main = Main.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.setspawn"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &a�?спользование команды:&r /setspawn".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;
        main.cfgspawn.getConfig().set("spawn.world", player.getLocation().getWorld().getName());
        main.cfgspawn.getConfig().set("spawn.x", player.getLocation().getBlockX());
        main.cfgspawn.getConfig().set("spawn.y", player.getLocation().getBlockY());
        main.cfgspawn.getConfig().set("spawn.z", player.getLocation().getBlockZ());
        main.cfgspawn.getConfig().set("spawn.yaw", player.getLocation().getYaw());
        main.cfgspawn.getConfig().set("spawn.pitch", player.getLocation().getPitch());
        main.cfgspawn.saveConfig();
        player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        player.sendMessage("&9╚ &aВы успешно установили точку спавна!".replace("&", "§"));
        return true;
    }
}
