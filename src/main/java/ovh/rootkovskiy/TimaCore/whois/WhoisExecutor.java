package ovh.rootkovskiy.TimaCore.whois;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.rootkovskiy.TimaCore.Main;

public class WhoisExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.whois"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /whois <Никнейм>".replace("&", "§"));
            return true;
        }

        Player p = Bukkit.getPlayer(args[0]);

        if (p == null || !p.isOnline()) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
            return true;
        }

        Location l = p.getLocation();

        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();

        sender.sendMessage("&9⦑ &bИнформация об игроке &9⦒&r".replace("&", "§"));

        sender.sendMessage("&9╔ &bНикнейм: &r".replace("&", "§") + p.getName());
        sender.sendMessage("&9║ &bUUID: &r".replace("&", "§") + p.getUniqueId().toString());
        sender.sendMessage("&9╚ &bНаиграно времени: &r".replace("&", "§") + p.getStatistic(Statistic.PLAY_ONE_TICK) / 20 / 60 + " минут");

        sender.sendMessage("&9╔ &bУровень ХП: &r".replace("&", "§") + p.getHealth());
        sender.sendMessage("&9║ &bУровень Голода: &r".replace("&", "§") + p.getFoodLevel());
        sender.sendMessage("&9║ &bУровень Опыта: &r".replace("&", "§") + p.getExp());
        sender.sendMessage("&9║ &bРежим Игры: &r".replace("&", "§") + p.getGameMode());
        sender.sendMessage("&9║ &bРежим Полета: &r".replace("&", "§") + p.isFlying());
        sender.sendMessage("&9╚ &bРежим Бога: &r".replace("&", "§") + p.isInvulnerable());

        sender.sendMessage("&9╔ &bМир: &r".replace("&", "§") + p.getWorld().getName());
        sender.sendMessage("&9╚ &bКоординаты: &r".replace("&", "§") + "x: " + x + " y: " + y + " z: " + z);

        sender.sendMessage("&9╔ &bРежим Оператора: &r".replace("&", "§") + p.isOp());
        sender.sendMessage("&9║ &bВаниш: &r".replace("&", "§") + Main.vanished.contains(p));
        sender.sendMessage("&9╚ &bАйпи адрес: &r".replace("&", "§") + p.getAddress());

        return true;
    }

}
