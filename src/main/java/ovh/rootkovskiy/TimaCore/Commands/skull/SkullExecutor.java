package ovh.rootkovskiy.TimaCore.Commands.skull;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullExecutor implements CommandExecutor {

    public static ItemStack getPlayerHead(OfflinePlayer owner) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setOwningPlayer(owner);
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cЭту команду нельзя исполнить через консоль или командный блок!&r".replace("&", "§"));
            return true;
        }

        if (!(sender.hasPermission("timacore.skull"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /skull <Ник игрока>".replace("&", "§"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cИгрока нет на сервере!&r".replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        ItemStack itemStack = getPlayerHead(target);

        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().setItem(player.getInventory().firstEmpty(), itemStack);
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aВы получили голову игрока &r".replace("&", "§") + target.getName());

            return true;
        }

        player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
        sender.sendMessage("&9╚ &aВы получили голову игрока &r".replace("&", "§") + target.getName());
        return true;
    }
}
