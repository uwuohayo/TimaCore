package ovh.rootkovskiy.TimaCore.spawnsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ovh.rootkovskiy.TimaCore.Main;

public class DelspawnExecutor implements CommandExecutor {

    Main main = Main.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.delspawn"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /delspawn".replace("&", "§"));
            return true;
        }

        if (main.cfgspawn.getConfig().getString("spawn") == null) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cНа сервере нет спавна!&r".replace("&", "§"));
            return true;
        }

        main.cfgspawn.getConfig().set("spawn", null);
        main.cfgspawn.saveConfig();

        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9╚ &aВы удалили Спавн!&r".replace("&", "§"));

        return true;
    }

}
