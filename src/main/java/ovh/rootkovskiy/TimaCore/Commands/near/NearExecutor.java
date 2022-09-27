package ovh.rootkovskiy.TimaCore.Commands.near;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NearExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.near"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /near <Дистанция>".replace("&", "§"));
            return true;
        }

        Player p = (Player) sender;
        String distancestr = args[0];

        try {

            Double distance = Double.parseDouble(distancestr);

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9║ &aИгроки поблизости:&r".replace("&", "§"));

            for (Entity near : p.getNearbyEntities(distance, distance, distance)) {

                if (near instanceof Player) {
                    if (p != near) {


                        Double x, y, z;
                        if (near.getLocation().getX() > p.getLocation().getX()) {
                            x = Double.valueOf(near.getLocation().getX() - p.getLocation().getX());
                        } else {
                            x = Double.valueOf(p.getLocation().getX() - near.getLocation().getX());
                        }
                        if (near.getLocation().getZ() > p.getLocation().getZ()) {
                            z = Double.valueOf(near.getLocation().getZ() - p.getLocation().getZ());
                        } else {
                            z = Double.valueOf(p.getLocation().getZ() - near.getLocation().getZ());
                        }
                        if (near.getLocation().getY() > p.getLocation().getY()) {
                            y = Double.valueOf(near.getLocation().getY() - p.getLocation().getY());
                        } else {
                            y = Double.valueOf(p.getLocation().getY() - near.getLocation().getY());
                        }
                        distance = Double.valueOf(Math.sqrt(Math.pow(x.doubleValue(), 2.0D) + Math.pow(y.doubleValue(), 2.0D) + Math.pow(z.doubleValue(), 2.0D)));

                        if (p.getNearbyEntities(distance, distance, distance).isEmpty()) {

                            sender.sendMessage("&9╚ &aИгроков поблизости не найдено!&r".replace("&", "§"));
                            return true;

                        }

                        p.sendMessage(("&9║ &r" + near.getName() + " &r" + Math.round(distance.doubleValue()) + "&a m").replace("&", "§"));
                    }
                }
            }

        } catch (NumberFormatException nfe) {
            p.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            p.sendMessage("&9╚ &cМожно использовать только числа!&r".replace("&", "§"));
            return true;
        }

        sender.sendMessage("&9╚ &a↑↑↑&r".replace("&", "§"));

        return true;
    }
}
