package ovh.rootkovskiy.TimaCore.hat;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HatExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.hat"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /hat".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        PlayerInventory inventory = player.getInventory();

        ItemStack hand = inventory.getItemInMainHand();
        ItemStack helmet = inventory.getHelmet();

        if (hand == null || hand.getType() == Material.AIR) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас в руке ничего нет!&r".replace("&", "§"));
            return true;
        }

        if (hand.getAmount() != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cПредметов в руке не должно быть больше одного!&r".replace("&", "§"));
            return true;
        }

        inventory.setHelmet(hand);
        inventory.setItemInMainHand(helmet);

        player.updateInventory();

        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9╚ &aВы надели на голову &r".replace("&", "§") + hand.getType().toString());

        return true;
    }
}
