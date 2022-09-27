package ovh.rootkovskiy.TimaCore.Systems.spawnsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class SpawnExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Main main = Main.getInstance();

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.spawn"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /spawn".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        String worldname = main.cfgspawn.getConfig().getString("spawn.world");

        if (worldname == null) {
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &cНа сервере нет спавна!&r".replace("&", "§"));
            return true;
        }

        World w = Bukkit.getWorld(main.cfgspawn.getConfig().getString("spawn.world"));
        int x = main.cfgspawn.getConfig().getInt("spawn.x");
        int y = main.cfgspawn.getConfig().getInt("spawn.y");
        int z = main.cfgspawn.getConfig().getInt("spawn.z");
        float yaw = main.cfgspawn.getConfig().getInt("spawn.yaw");
        float pitch = main.cfgspawn.getConfig().getInt("spawn.pitch");
        Location spawn = new Location(w, x, y, z, yaw, pitch);

        player.teleport(spawn);

        player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        player.sendMessage("&9╚ &aВы телепортированы на спавн!&r".replace("&", "§"));

        return true;
    }

}
