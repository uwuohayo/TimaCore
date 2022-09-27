package ovh.rootkovskiy.TimaCore.Systems.warpsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class WarpExecutor implements CommandExecutor {

    Main main = Main.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.warp"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /warp <Название>".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;
        String warpname = args[0];

        if (!(main.cfgwarps.getConfig().contains(warpname))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cТакого варпа не существует!".replace("&", "§"));
            return true;
        }

        World w = Bukkit.getWorld(main.cfgwarps.getConfig().getString(warpname + ".world"));
        int x = main.cfgwarps.getConfig().getInt(warpname + ".x");
        int y = main.cfgwarps.getConfig().getInt(warpname + ".y");
        int z = main.cfgwarps.getConfig().getInt(warpname + ".z");
        float yaw = main.cfgwarps.getConfig().getInt(warpname + ".yaw");
        float pitch = main.cfgwarps.getConfig().getInt(warpname + ".pitch");
        Location warp = new Location(w, x, y, z, yaw, pitch);

        player.teleport(warp);

        player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        player.sendMessage("&9╚ &aВы телепортировались на варп &r".replace("&", "§") + warpname);

        return true;
    }

}
