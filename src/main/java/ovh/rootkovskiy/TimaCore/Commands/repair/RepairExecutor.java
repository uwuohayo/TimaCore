package ovh.rootkovskiy.TimaCore.Commands.repair;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RepairExecutor implements CommandExecutor {

    private boolean isEmpty(final Inventory inventory) {
        for (final ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null && itemStack.getType() != Material.AIR)
                return false;
        }
        return true;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.repair"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            player.sendMessage("&9╚ &aИспользование команды:&r /repair <HAND/ALL>".replace("&", "§"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "hand":

                if (!(sender.hasPermission("timacore.repair.hand"))) {
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                    return true;
                }

                //code repair hand
                final ItemStack itemhand = player.getInventory().getItemInMainHand();

                if (itemhand.getType() == Material.AIR) {
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &cУ вас в руке нет предмета!&r".replace("&", "§"));
                    return true;
                }

                itemhand.setDurability((short) (itemhand.getType().getMaxDurability() - itemhand.getType().getMaxDurability()));

                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aВы починили предмет в руке&r".replace("&", "§"));
                return true;
            case "all":

                if (!(sender.hasPermission("timacore.repair.all"))) {
                    sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
                    return true;
                }

                if (isEmpty(player.getInventory())) {
                    player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                    player.sendMessage("&9╚ &cВ вашем инвентаре нет предметов!&r".replace("&", "§"));
                    return true;
                }

                //code repair all
                for (ItemStack item : player.getInventory()
                        .getContents()) {
                    if (item != null) {

                        item.setDurability((short) 0);
                    }
                }
                for (ItemStack item : player.getInventory()
                        .getArmorContents()) {
                    if (item != null)
                        item.setDurability((short) 0);
                }

                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aВы починили все предметы в вашем инвентаре&r".replace("&", "§"));
                return true;


            default:
                player.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
                player.sendMessage("&9╚ &aИспользование команды:&r /repair <HAND/ALL>".replace("&", "§"));
                return true;
        }
    }
}
