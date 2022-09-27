package ovh.rootkovskiy.TimaCore.Commands.tnt;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TntExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
                return true;
            }

            if (!(sender.hasPermission("timacore.tnt"))) {
                sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                return true;
            }

            final Player player = (Player) sender;
            final Block block = player.getTargetBlock(null, 25);

            if (block != null) {

                final Entity entity = block.getWorld().spawnEntity(block.getLocation().add(0.0D, 1.0D, 0.0D), EntityType.PRIMED_TNT);
                entity.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 6);
                entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 7.0F, 1.0F);
                entity.remove();

                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aВы заспавнили ТНТ!&r".replace("&", "§"));
                return true;
            }
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &cВы не смогли поставить ТНТ, так как вы смотрите в пустоту!&r".replace("&", "§"));
            return true;
        } else {

            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /tnt".replace("&", "§"));
            return true;

        }
    }
}
