package ovh.rootkovskiy.TimaCore.Commands.gc;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ovh.rootkovskiy.TimaCore.Main;
import ovh.rootkovskiy.TimaCore.Utils.LagUtils;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class GcExecutor implements CommandExecutor {


    public static double getProcessCpuLoad() throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

        if (list.isEmpty()) return Double.NaN;

        Attribute att = (Attribute) list.get(0);
        Double value = (Double) att.getValue();

        if (value == -1.0) return Double.NaN;
        return ((int) (value * 1000) / 10.0);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("timacore.gc"))) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &cУ вас нет прав на выполнение данной команды!&r".replace("&", "§"));
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage("&9╔ &bСистема&r".replace("&", "§"));
            sender.sendMessage("&9╚ &aИспользование команды:&r /gc".replace("&", "§"));
            return true;
        }

        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long maxMemory = Runtime.getRuntime().maxMemory();
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final int plrsAmount = Bukkit.getOnlinePlayers().size();
        final int plrsMax = Bukkit.getServer().getMaxPlayers();

        final long startTime = Main.srvstart;
        final long stopTime = System.currentTimeMillis();
        final long diff = stopTime - startTime;
        final String msg = (int) (diff / 864000000L) + " Дней " + (int) (diff / 36000000L % 24L) + " Часов " + (int) (diff / 60000L % 60L) + " Минут " + (int) (diff / 1000L % 60L) + " Секунд ";

        double tps = LagUtils.getTPS();
        String result = String.format("%.2f", LagUtils.getTPS());
        double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);

        sender.sendMessage("&9⦑ &bИнформация о Сервере &9⦒&r".replace("&", "§"));

        sender.sendMessage("&9╔ &bАптайм: &r".replace("&", "§") + msg);
        sender.sendMessage("&9║ &bИгроков: &r".replace("&", "§") + plrsAmount + "/" + plrsMax);
        sender.sendMessage(("&9║ &bТпс: &r").replace("&", "§") + result);
        sender.sendMessage(("&9╚ &bПроцент задержки сервера: &r").replace("&", "§") + lag);

        try {
            sender.sendMessage("&9╔ &bНагрузка CPU: &r".replace("&", "§") + getProcessCpuLoad() + "%");
        } catch (Exception e) {

            e.printStackTrace();
        }
        sender.sendMessage("&9║ &bКол-во CPU: &r".replace("&", "§") + availableProcessors);
        sender.sendMessage("&9║ &bИспользование RAM: &r".replace("&", "§") + freeMemory / 1048576 + " МБ");
        sender.sendMessage("&9║ &bВыделено RAM: &r".replace("&", "§") + totalMemory / 1048576 + " МБ");
        sender.sendMessage("&9╚ &bВсего RAM: &r".replace("&", "§") + maxMemory / 1048576 + " МБ");

        for (World world : Bukkit.getServer().getWorlds()) {
            sender.sendMessage(("&9⦑  &bИнформация о мире &r" + world.getName() + " &9⦒&r").replace("&", "§"));
            sender.sendMessage("&9╔ &bЭнтити: &r".replace("&", "§") + world.getLivingEntities().size() + "/" + world.getEntities().size());
            sender.sendMessage("&9╚ &bПрогруженные чанки: &r".replace("&", "§") + world.getLoadedChunks().length);
        }

        sender.sendMessage("&9⦑  &bСписок плагинов &9⦒&r".replace("&", "§"));
        StringBuilder list = new StringBuilder();
        for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
            list.insert(0, p.getName() + " ");
        }
        sender.sendMessage(list.toString());
        return true;

    }

}
