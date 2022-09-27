package ovh.rootkovskiy.TimaCore.Commands.pweather;

import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PweatherExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.pweather"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length == 1) {

            Player player = (Player) sender;

            switch (args[0].toLowerCase()) {
                case "storm":
                    player.setPlayerWeather(WeatherType.DOWNFALL);
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы установили себе Дождливую личную погоду! &r".replace("&", "§"));
                    return true;
                case "sun":
                    player.setPlayerWeather(WeatherType.CLEAR);
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aВы установили себе Солнечную личную погоду! &r".replace("&", "§"));
                    return true;
                default:
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &aИспользование команды:&r /pweather <STORM/SUN>".replace("&", "§"));
                    return true;
            }

        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /pweather <STORM/SUN>".replace("&", "§"));
            return true;

        }

    }
}
