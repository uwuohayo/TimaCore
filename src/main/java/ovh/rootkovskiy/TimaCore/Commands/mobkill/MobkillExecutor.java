package ovh.rootkovskiy.TimaCore.Commands.mobkill;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MobkillExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.mobkill"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /mobkill".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        final EntityType[] type = {EntityType.DROPPED_ITEM, EntityType.PLAYER, EntityType.ARROW, EntityType.PAINTING, EntityType.ENDER_DRAGON, EntityType.ENDER_PEARL, EntityType.ENDER_CRYSTAL, EntityType.ARMOR_STAND, EntityType.EGG, EntityType.FIREBALL, EntityType.MINECART_MOB_SPAWNER, EntityType.MINECART_CHEST, EntityType.MINECART_TNT, EntityType.MINECART_COMMAND, EntityType.MINECART_FURNACE, EntityType.MINECART_TNT, EntityType.SPLASH_POTION, EntityType.SPECTRAL_ARROW, EntityType.SMALL_FIREBALL};
        for (final Entity entity : player.getWorld().getEntities()) {
            if (Arrays.asList(type).contains(entity.getType())) {
                continue;
            }
            entity.remove();
        }
        player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        player.sendMessage(("&9╚ &aВы произвели очистку мира от мобов!&r").replace("&", "§"));
        Bukkit.broadcastMessage("&9╔ &bИгрок: &r".replace("&", "§") + player.getName());
        Bukkit.broadcastMessage("&9╚ &aПроизвел очистку мобов в мире: &r".replace("&", "§") + player.getWorld().getName());
        return true;
    }

}
